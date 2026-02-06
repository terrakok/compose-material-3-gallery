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
import com.github.terrakok.theme.LocalAppColor
import com.github.terrakok.theme.SeedColor

@Composable
internal fun SelectSeedColor(
    closeChooser: () -> Unit,
    selectedSeedColor: SeedColor?,
    onSeedColorSelected: (SeedColor) -> Unit
) {
    Text(
        text = "Seed Color",
        style = MaterialTheme.typography.labelLarge,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
    SeedColor.entries.forEach { color ->
        val isSelected = selectedSeedColor == color
        Row(
            Modifier
                .fillMaxWidth()
                .clickable(enabled = !isSelected) {
                    onSeedColorSelected(color)
                    closeChooser()
                }
                .then(if (isSelected) Modifier.alpha(0.6f) else Modifier)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = if (isSelected) Icons.Filled.Palette else Icons.Outlined.Palette,
                contentDescription = "Select a seed color",
                tint = color.value
            )
            Text(color.colorName, Modifier.padding(horizontal = 16.dp))
        }
    }
}