package com.github.terrakok.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.materialkolor.dynamicColorScheme
import material_3_gallery.composeapp.generated.resources.Res
import material_3_gallery.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(2.dp),
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

private val AppTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )
)

internal enum class SeedColor(val colorName: String, val value: Color) {
    BASELINE("M3 Baseline", Color(0xFF6750A4)),
    INDIGO("Indigo", Color(0xFF_3F51B5)),
    BLUE("Blue", Color(0xFF_0061A4)),
    TEAL("Teal", Color(0xFF_009688)),
    GREEN("Green", Color(0xFF_4CAF50)),
    YELLOW("Yellow", Color(0xFF_FFEB3B)),
    ORANGE("Orange", Color(0xFF_FF9800)),
    DEEP_ORANGE("Deep orange", Color(0xFF_FF5722)),
    PINK("Pink", Color(0xFF_E91E63)),
    REVIVLE_ORANGE("Revivle Orange", Color(0xFF_D79542)),
    REVIVLE_SALMON("Revivle salmon", Color(0xFF_FB8E82))
}

internal enum class ColorPalette(
    val paletteName: String,
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val neutral: Color,
    val neutralVariant: Color,
    val error: Color
) {
    PALETTE_1(
        "Palette 1",
        primary = Color(0xFF_FF5722), // deep orange
        secondary = Color(0xFF_03A9F4), // light blue
        tertiary = Color(0xFF_4CAF50), // green
        neutral = Color(0xFF_9E9E9E), // grey
        neutralVariant = Color(0xFF_795548), // brown
        error = Color(0xFF_F44336)  // red
    ),
    PALETTE_2(
        "Palette 2",
        primary = Color(0xFF_E91E63), // pink
        secondary = Color(0xFF_2196F3), // blue
        tertiary = Color(0xFF_FFC107), // amber
        neutral = Color(0xFF_607D8B), // blue grey
        neutralVariant = Color(0xFF_8BC34A), // light green
        error = Color(0xFF_D32F2F)  // red 700
    ),
    PALETTE_3(
        "Palette 3",
        primary = Color(0xFF_9C27B0), // purple
        secondary = Color(0xFF_00BCD4), // cyan
        tertiary = Color(0xFF_FF9800), // orange
        neutral = Color(0xFF_795548), // brown
        neutralVariant = Color(0xFF_BDBDBD), // grey 400
        error = Color(0xFF_C62828)  // red 800
    )
}

internal enum class ColorExtractionImage(val imageName: String, val imageResource: DrawableResource) {
    LEAVES("Leaves", Res.drawable.content_based_color_scheme_1),
    PEONIES("Peonies", Res.drawable.content_based_color_scheme_2),
    BUBBLES("Bubbles", Res.drawable.content_based_color_scheme_3),
    SEAWEED("Seaweed", Res.drawable.content_based_color_scheme_4),
    SEA_GRAPES("Sea Grapes", Res.drawable.content_based_color_scheme_5),
    PETALS("Petals", Res.drawable.content_based_color_scheme_6),
}

internal sealed class AppColor {
    data class Seed(val seedColor: SeedColor) : AppColor()
    data class Image(val image: ColorExtractionImage, val color: Color) : AppColor()
    data class Palette(val colorPalette: ColorPalette) : AppColor()
}

internal val LocalThemeIsDark = compositionLocalOf { mutableStateOf(true) }
internal val LocalAppColor = compositionLocalOf {
    mutableStateOf<AppColor>(AppColor.Seed(SeedColor.BASELINE))
}

@Composable
internal fun AppTheme(
    content: @Composable() () -> Unit
) {
    val systemIsDark = isSystemInDarkTheme()
    val isDarkState = remember { mutableStateOf(systemIsDark) }
    val appColorState = remember {
        mutableStateOf<AppColor>(AppColor.Seed(SeedColor.BASELINE))
    }
    val appColor = appColorState.value
    CompositionLocalProvider(
        LocalThemeIsDark provides isDarkState,
        LocalAppColor provides appColorState,
    ) {
        val isDark by isDarkState
        SystemAppearance(!isDark)
        val colorScheme = remember(appColor, isDark) {
            when (appColor) {
                is AppColor.Seed -> dynamicColorScheme(
                    seedColor = appColor.seedColor.value,
                    isDark = isDark,
                    isAmoled = false
                )
                is AppColor.Image -> dynamicColorScheme(
                    seedColor = appColor.color,
                    isDark = isDark,
                    isAmoled = false
                )
                is AppColor.Palette -> {
                    val palette = appColor.colorPalette
                    dynamicColorScheme(
                        primary = palette.primary,
                        secondary = palette.secondary,
                        tertiary = palette.tertiary,
                        neutral = palette.neutral,
                        neutralVariant = palette.neutralVariant,
                        error = palette.error,
                        isDark = isDark,
                        isAmoled = false
                    )
                }
            }
        }
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            shapes = AppShapes,
            content = {
                Surface(content = content)
            }
        )
    }
}

@Composable
internal expect fun SystemAppearance(isDark: Boolean)
