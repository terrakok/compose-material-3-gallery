package com.github.terrakok

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp

private val checkboxesInfoUrl =
    "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Checkbox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.material3.CheckboxColors,androidx.compose.foundation.interaction.MutableInteractionSource)"

private val chipsInfoUrl =
    "https://developer.android.com/jetpack/compose/components/chip"

@Composable
fun Selection() {
    ParentSection("Selection") {
        ChildSection(
            title = "Checkboxes",
            infoUrl = checkboxesInfoUrl
        ) {
            CheckboxesDemo()
        }

        ChildSection(
            title = "Chips",
            infoUrl = chipsInfoUrl
        ) {
            ChipsDemo()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChipsDemo() {

    var filterChipSelected by remember { mutableStateOf(true) }

    @Composable
    fun ChipsRow(enabled: Boolean = true) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            AssistChip(
                enabled = enabled,
                onClick = {},
                label = { Text("Assist") },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Event,
                        contentDescription = null,
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            )

            FilterChip(
                enabled = enabled,
                onClick = { filterChipSelected = !filterChipSelected },
                label = { Text("Filter") },
                selected = filterChipSelected,
                leadingIcon = if (filterChipSelected) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                },
            )

            InputChip(
                onClick = {},
                label = { Text("Input") },
                selected = true,
                enabled = enabled,
                avatar = null,
                trailingIcon = {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = null,
                        Modifier.size(InputChipDefaults.IconSize)
                    )
                },
            )

            SuggestionChip(
                enabled = enabled,
                onClick = {},
                label = { Text("Suggestion") }
            )
        }
    }

    OutlinedCard {
        Column(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(16.dp)
        ) {
            ChipsRow(enabled = true)
            ChipsRow(enabled = false)
        }
    }
}

@Composable
private fun CheckboxesDemo() {
    OutlinedCard {
        Column(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(16.dp)
        ) {

            Row(modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val state = remember { mutableStateOf(true) }
                Text("Option 1")
                Checkbox(
                    checked = state.value,
                    onCheckedChange = { state.value = it }
                )
            }

            Row(modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val state = remember { mutableStateOf(ToggleableState.Indeterminate) }
                Text("Option 2")
                TriStateCheckbox(
                    state = state.value,
                    onClick = {
                        state.value = state.value.nextState()
                    }
                )
            }

            Row(modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val state = remember { mutableStateOf(ToggleableState.Off) }
                Text("Option 3")
                TriStateCheckbox(
                    state = state.value,
                    onClick = {
                        state.value = state.value.nextState()
                    }
                )
            }

            Row(modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Option 4")
                Checkbox(
                    checked = true,
                    enabled = false,
                    onCheckedChange = {}
                )
            }
        }
    }
}

private fun ToggleableState.nextState(): ToggleableState {
    return when (this) {
        ToggleableState.Indeterminate -> ToggleableState.Off
        ToggleableState.On -> ToggleableState.Indeterminate
        ToggleableState.Off -> ToggleableState.On
    }
}