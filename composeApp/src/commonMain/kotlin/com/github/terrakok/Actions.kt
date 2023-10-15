package com.github.terrakok

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Actions() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Actions",
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Common buttons")
            IconButton(
                modifier = Modifier.padding(4.dp).size(16.dp),
                onClick = { openUrl("https://developer.android.com/jetpack/compose/components/button") }
            ) {
                Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
            }
        }
        CommonButtons()
        Row(
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Floating action buttons")
            IconButton(
                modifier = Modifier.padding(4.dp).size(16.dp),
                onClick = { openUrl("https://developer.android.com/jetpack/compose/components/fab") }
            ) {
                Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
            }
        }
        FloatingActionButtons()
        Row(
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Icon buttons")
            IconButton(
                modifier = Modifier.padding(4.dp).size(16.dp),
                onClick = { openUrl("https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#IconButton(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.material3.IconButtonColors,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function0)") }
            ) {
                Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
            }
        }
        IconButtons()
    }
}

@Composable
fun CommonButtons() {
    OutlinedCard {
        Column(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .widthIn(400.dp, 600.dp)
                .padding(8.dp)
        ) {
            Row {
                ElevatedButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(1f),
                    onClick = {},
                    content = { Text("Elevated") }
                )
                ElevatedButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(1f),
                    onClick = {},
                    content = {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Icon")
                    }
                )
                ElevatedButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(1f),
                    enabled = false,
                    onClick = {},
                    content = { Text("Elevated") }
                )
            }
            Row {
                Button(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(1f),
                    onClick = {},
                    content = { Text("Filled") }
                )
                Button(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(1f),
                    onClick = {},
                    content = {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Icon")
                    }
                )
                Button(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(1f),
                    enabled = false,
                    onClick = {},
                    content = { Text("Filled") }
                )
            }
            Row {
                FilledTonalButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(1f),
                    onClick = {},
                    content = { Text("Tonal") }
                )
                FilledTonalButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(1f),
                    onClick = {},
                    content = {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Icon")
                    }
                )
                FilledTonalButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(1f),
                    enabled = false,
                    onClick = {},
                    content = { Text("Tonal") }
                )
            }
            Row {
                OutlinedButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(1f),
                    onClick = {},
                    content = { Text("Outlined") }
                )
                OutlinedButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(1f),
                    onClick = {},
                    content = {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Icon")
                    }
                )
                OutlinedButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(1f),
                    enabled = false,
                    onClick = {},
                    content = { Text("Outlined") }
                )
            }
            Row {
                TextButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(1f),
                    onClick = {},
                    content = { Text("Text") }
                )
                TextButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(1f),
                    onClick = {},
                    content = {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Icon")
                    }
                )
                TextButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(1f),
                    enabled = false,
                    onClick = {},
                    content = { Text("Text") }
                )
            }
        }
    }
}

@Composable
fun FloatingActionButtons() {
    OutlinedCard {
        Row(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SmallFloatingActionButton(
                modifier = Modifier.padding(8.dp),
                onClick = {}
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
            ExtendedFloatingActionButton(
                modifier = Modifier.padding(8.dp),
                onClick = {},
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = { Text("Create") }
            )
            FloatingActionButton(
                modifier = Modifier.padding(8.dp),
                onClick = {}
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
            LargeFloatingActionButton(
                modifier = Modifier.padding(8.dp),
                onClick = {}
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    }
}

@Composable
fun IconButtons() {
    OutlinedCard {
        Row(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = {},
                enabled = true,
                content = {
                    Icon(Icons.Default.Settings, contentDescription = null)
                }
            )
            IconButton(
                onClick = {},
                enabled = false,
                content = {
                    Icon(Icons.Default.Settings, contentDescription = null)
                }
            )
        }
    }
}