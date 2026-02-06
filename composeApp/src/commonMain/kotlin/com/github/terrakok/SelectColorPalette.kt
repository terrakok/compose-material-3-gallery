package com.github.terrakok

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.github.terrakok.theme.ColorPalette

@Composable
internal fun SelectColorPalette(closeChooser: () -> Unit, selectedPalette: ColorPalette?, onPaletteSelected: (ColorPalette) -> Unit) {
    Text(
        text = "Palette",
        style = MaterialTheme.typography.labelLarge,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
    ColorPalette.entries.forEach { palette ->
        val isSelected = selectedPalette == palette
        Row(
            Modifier
                .fillMaxWidth()
                .clickable(enabled = !isSelected) {
                    onPaletteSelected(palette)
                    closeChooser()
                }
                .then(if (isSelected) Modifier.alpha(0.6f) else Modifier)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = if (isSelected) Icons.Filled.Palette else Icons.Outlined.Palette,
                contentDescription = "Select a color palette",
                tint = palette.primary
            )
            Text(palette.paletteName, Modifier.padding(horizontal = 16.dp))
        }
    }
}
