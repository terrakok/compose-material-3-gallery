package com.github.terrakok

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.github.terrakok.theme.AppColor
import com.github.terrakok.theme.ColorPalette
import com.github.terrakok.theme.LocalAppColor

@Composable
internal fun SelectColorPaletteButton() {
    val appColorState = LocalAppColor.current
    val appColor = appColorState.value
    val selectedPalette = (appColor as? AppColor.Palette)?.colorPalette

    fun onSelected(palette: ColorPalette) {
        appColorState.value = AppColor.Palette(palette)
    }

    var isPaletteChooserOpen by remember { mutableStateOf(false) }
    IconButton(
        onClick = { isPaletteChooserOpen = !isPaletteChooserOpen }
    ) {
        Icon(
            Icons.Default.Palette,
            contentDescription = "Select a color palette"
        )
    }
    if (isPaletteChooserOpen) {
        val shape = RoundedCornerShape(16.dp)
        Popup(onDismissRequest = { isPaletteChooserOpen = false }) {
            Column(
                Modifier
                    .shadow(16.dp, shape)
                    .clip(shape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(vertical = 8.dp)
                    .width(IntrinsicSize.Max)
            ) {
                ColorPalette.entries.forEach { palette ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable(enabled = selectedPalette != palette) {
                                onSelected(palette)
                                isPaletteChooserOpen = false
                            }
                            .then(if (selectedPalette == palette) Modifier.alpha(0.6f) else Modifier)
                            .padding(16.dp)
                    ) {
                        Icon(
                            imageVector = if (selectedPalette == palette) {
                                Icons.Filled.Palette
                            } else {
                                Icons.Outlined.Palette
                            },
                            contentDescription = "Select a color palette",
                            tint = palette.primary
                        )
                        Text(palette.paletteName, Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    }
}