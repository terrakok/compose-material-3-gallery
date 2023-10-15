package com.github.terrakok

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun TypographyScreen() {
    data class TextItem(val name: String, val style: TextStyle)
    val items = listOf(
        TextItem("Display Large", MaterialTheme.typography.displayLarge),
        TextItem("Display Medium", MaterialTheme.typography.displayMedium),
        TextItem("Display Small", MaterialTheme.typography.displaySmall),
        TextItem("Headline Large", MaterialTheme.typography.headlineLarge),
        TextItem("Headline Medium", MaterialTheme.typography.headlineMedium),
        TextItem("Headline Small", MaterialTheme.typography.headlineSmall),
        TextItem("Title Large", MaterialTheme.typography.titleLarge),
        TextItem("Title Medium", MaterialTheme.typography.titleMedium),
        TextItem("Title Small", MaterialTheme.typography.titleSmall),
        TextItem("Label Large", MaterialTheme.typography.labelLarge),
        TextItem("Label Medium", MaterialTheme.typography.labelMedium),
        TextItem("Label Small", MaterialTheme.typography.labelSmall),
        TextItem("Body Large", MaterialTheme.typography.bodyLarge),
        TextItem("Body Medium", MaterialTheme.typography.bodyMedium),
        TextItem("Body Small", MaterialTheme.typography.bodySmall),
    )
    LazyColumn {
        items(items) {
            Text(text = it.name, style = it.style)
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}