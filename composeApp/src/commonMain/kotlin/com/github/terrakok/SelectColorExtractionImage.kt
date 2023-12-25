package com.github.terrakok

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.github.terrakok.theme.AppColor
import com.github.terrakok.theme.ColorExtractionImage
import com.github.terrakok.theme.LocalAppColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.imageResource

@Composable
internal fun SelectColorExtractionImageButton() {
    val appColorState = LocalAppColor.current
    val appColor = appColorState.value
    val selectedImage = (appColor as? AppColor.Image)?.image

    val coroutineScope = rememberCoroutineScope()
    fun onSelected(image: ColorExtractionImage, imageBitmap: ImageBitmap?) {
        if (imageBitmap == null) {
            return
        }
        coroutineScope.launch {
            withContext(Dispatchers.Default) {
                // CPU intensive code runs on background
                val color = calculateAverageColor(imageBitmap)
                appColorState.value = AppColor.Image(image, color)
            }
        }
    }

    var isChooserOpen by remember { mutableStateOf(false) }
    IconButton(
        onClick = { isChooserOpen = !isChooserOpen }
    ) {
        Icon(
            Icons.Default.Image,
            contentDescription = "Select a seed color"
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
            ) {
                ColorExtractionImage.entries.forEach { image ->
                    val imageBitmap = imageResource(DrawableResource(image.imageResource))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable(enabled = selectedImage != image && imageBitmap != null) {
                                onSelected(image, imageBitmap)
                                isChooserOpen = false
                            }
                            .then(if (selectedImage == image) Modifier.alpha(0.6f) else Modifier)
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            bitmap = imageBitmap,
                            contentDescription = image.imageName,
                            Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Text(image.imageName, Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    }
}

private fun calculateAverageColor(bitmap: ImageBitmap): Color {
    val pixels = IntArray(bitmap.width * bitmap.height)
    bitmap.readPixels(pixels)
    var sumRed = 0f
    var sumGreen = 0f
    var sumBlue = 0f
    pixels.forEach {
        val r = (it ushr 16) and 0xFF
        val g = (it ushr 8) and 0xFF
        val b = (it ushr 0) and 0xFF
        sumRed += r.toFloat()
        sumGreen += g.toFloat()
        sumBlue += b.toFloat()
    }
    fun Float.averageColorComponent(): Int = (this / pixels.size).toInt().coerceIn(0..0xFF)
    return Color(
        red = sumRed.averageColorComponent(),
        green = sumGreen.averageColorComponent(),
        blue = sumBlue.averageColorComponent(),
    )
}
