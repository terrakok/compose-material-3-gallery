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
internal fun SelectSeedColorButton() {
    val appColorState = LocalAppColor.current
    val appColor = appColorState.value
    val selectedSeedColor = (appColor as? AppColor.Seed)?.seedColor

    fun onSelected(color: SeedColor) {
        appColorState.value = AppColor.Seed(color)
    }

    var isSeedChooserOpen by remember { mutableStateOf(false) }
    IconButton(
        onClick = { isSeedChooserOpen = !isSeedChooserOpen }
    ) {
        Icon(
            Icons.Default.Palette,
            contentDescription = "Select a seed color"
        )
    }
    if (isSeedChooserOpen) {
        val shape = RoundedCornerShape(16.dp)
        Popup(onDismissRequest = { isSeedChooserOpen = false }) {
            Column(
                Modifier
                    .shadow(16.dp, shape)
                    .clip(shape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(vertical = 8.dp)
                    .width(IntrinsicSize.Max)
            ) {
                SeedColor.entries.forEach { color ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable(enabled = selectedSeedColor != color) {
                                onSelected(color)
                                isSeedChooserOpen = false
                            }
                            .then(if (selectedSeedColor == color) Modifier.alpha(0.6f) else Modifier)
                            .padding(16.dp)
                    ) {
                        Icon(
                            imageVector = if (selectedSeedColor == color) {
                                Icons.Filled.Palette
                            } else {
                                Icons.Outlined.Palette
                            },
                            contentDescription = "Select a seed color",
                            tint = color.value
                        )
                        Text(color.colorName, Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    }
}
