package com.github.terrakok

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.github.terrakok.theme.AppColor
import com.github.terrakok.theme.ColorPalette
import com.github.terrakok.theme.LocalAppColor
import com.github.terrakok.theme.SeedColor

@Composable
internal fun SelectColorSourceButton() {
    val appColorState = LocalAppColor.current
    val appColor = appColorState.value
    val selectedSeedColor = (appColor as? AppColor.Seed)?.seedColor
    val selectedPalette = (appColor as? AppColor.Palette)?.colorPalette

    fun onSeedColorSelected(color: SeedColor) {
        appColorState.value = AppColor.Seed(color)
    }

    fun onPaletteSelected(palette: ColorPalette) {
        appColorState.value = AppColor.Palette(palette)
    }

    var isChooserOpen by remember { mutableStateOf(false) }
    IconButton(
        onClick = { isChooserOpen = !isChooserOpen }
    ) {
        Icon(
            Icons.Default.Palette,
            contentDescription = "Select a color source"
        )
    }
    if (isChooserOpen) {
        val shape = RoundedCornerShape(16.dp)
        Popup(onDismissRequest = { isChooserOpen = false }) {
            Column(
                Modifier
                    .shadow(16.dp, shape)
                    .clip(shape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(vertical = 8.dp)
                    .width(IntrinsicSize.Max)
                    .verticalScroll(rememberScrollState())
            ) {
                SelectSeedColor(
                    closeChooser = { isChooserOpen = false },
                    selectedSeedColor = selectedSeedColor,
                    onSeedColorSelected = {
                        onSeedColorSelected(it)
                    })

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                SelectColorPalette(
                    closeChooser = { isChooserOpen = false },
                    selectedPalette = selectedPalette,
                    onPaletteSelected = {
                        onPaletteSelected(it)
                    })
            }
        }
    }
}