package com.github.terrakok

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun TextInputs() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Text inputs",
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Text fields")
            IconButton(
                modifier = Modifier.padding(4.dp).size(16.dp),
                onClick = { openUrl("https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#textfield") }
            ) {
                Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
            }
        }
        TextFields()
    }
}

@Composable
fun TextFields() {
    OutlinedCard {
        Column(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(16.dp)
        ) {
            var txt by remember { mutableStateOf("") }
            TextField(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                singleLine = true,
                value = txt,
                onValueChange = { txt = it },
                label = { Text("Filled") },
                supportingText = { Text("supporting text") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                trailingIcon = {
                    IconButton(
                        onClick = { txt = "" },
                        content = { Icon(Icons.Default.Close, null) }
                    )
                },
                placeholder = { Text("placeholder") },
            )
            Row {
                TextField(
                    modifier = Modifier.weight(1f).padding(8.dp),
                    singleLine = true,
                    value = txt,
                    onValueChange = { txt = it },
                    label = { Text("Filled", overflow = TextOverflow.Ellipsis, maxLines = 1) },
                    supportingText = { Text("error text") },
                    leadingIcon = { Icon(Icons.Default.Search, null) },
                    trailingIcon = {
                        IconButton(
                            onClick = { txt = "" },
                            content = { Icon(Icons.Default.Close, null) }
                        )
                    },
                    placeholder = { Text("placeholder", overflow = TextOverflow.Ellipsis, maxLines = 1) },
                    isError = true
                )
                TextField(
                    modifier = Modifier.weight(1f).padding(8.dp),
                    singleLine = true,
                    value = txt,
                    onValueChange = { txt = it },
                    label = { Text("Disabled", overflow = TextOverflow.Ellipsis, maxLines = 1) },
                    supportingText = { Text("supporting text") },
                    leadingIcon = { Icon(Icons.Default.Search, null) },
                    trailingIcon = {
                        IconButton(
                            onClick = { txt = "" },
                            content = { Icon(Icons.Default.Close, null) }
                        )
                    },
                    placeholder = { Text("placeholder", overflow = TextOverflow.Ellipsis, maxLines = 1) },
                    enabled = false
                )
            }
            var txt2 by remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                singleLine = true,
                value = txt2,
                onValueChange = { txt2 = it },
                label = { Text("Outlined") },
                supportingText = { Text("supporting text") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                trailingIcon = {
                    IconButton(
                        onClick = { txt2 = "" },
                        content = { Icon(Icons.Default.Close, null) }
                    )
                },
                placeholder = { Text("placeholder") },
            )
            Row {
                OutlinedTextField(
                    modifier = Modifier.weight(1f).padding(8.dp),
                    singleLine = true,
                    value = txt2,
                    onValueChange = { txt2 = it },
                    label = { Text("Outlined", overflow = TextOverflow.Ellipsis, maxLines = 1) },
                    supportingText = { Text("error text") },
                    leadingIcon = { Icon(Icons.Default.Search, null) },
                    trailingIcon = {
                        IconButton(
                            onClick = { txt2 = "" },
                            content = { Icon(Icons.Default.Close, null) }
                        )
                    },
                    placeholder = { Text("placeholder", overflow = TextOverflow.Ellipsis, maxLines = 1) },
                    isError = true
                )
                OutlinedTextField(
                    modifier = Modifier.weight(1f).padding(8.dp),
                    singleLine = true,
                    value = txt2,
                    onValueChange = { txt2 = it },
                    label = { Text("Disabled", overflow = TextOverflow.Ellipsis, maxLines = 1) },
                    supportingText = { Text("supporting text") },
                    leadingIcon = { Icon(Icons.Default.Search, null) },
                    trailingIcon = {
                        IconButton(
                            onClick = { txt2 = "" },
                            content = { Icon(Icons.Default.Close, null) }
                        )
                    },
                    placeholder = { Text("placeholder", overflow = TextOverflow.Ellipsis, maxLines = 1) },
                    enabled = false
                )
            }
        }
    }
}