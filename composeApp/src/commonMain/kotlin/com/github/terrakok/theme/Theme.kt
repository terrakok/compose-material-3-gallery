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
//import com.materialkolor.dynamicColorScheme
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
    abstract val color: Color
    data class Seed(val seedColor: SeedColor) : AppColor() {
        override val color: Color = seedColor.value
    }
    data class Image(val image: ColorExtractionImage, override val color: Color) : AppColor()
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
        MaterialTheme(
//            colorScheme = dynamicColorScheme(
//                seedColor = remember(appColor) {
//                    when(appColor) {
//                        is AppColor.Seed -> {
//                            appColor.seedColor.value
//                        }
//                        is AppColor.Image -> {
//
//                            appColor.color
//                        }
//                    }
//                },
//                isDark = isDark
//            ),
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
