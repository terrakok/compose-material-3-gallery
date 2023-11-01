package com.github.terrakok

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp

private val checkboxesInfoUrl =
    "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Checkbox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.material3.CheckboxColors,androidx.compose.foundation.interaction.MutableInteractionSource)"

private val chipsInfoUrl = "https://developer.android.com/jetpack/compose/components/chip"

private val datePickerInfoUrl =
    "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#DatePicker(androidx.compose.material3.DatePickerState,androidx.compose.ui.Modifier,androidx.compose.material3.DatePickerFormatter,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.material3.DatePickerColors)"

private val menusInfoUrl =
    "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#DropdownMenu(kotlin.Boolean,kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.ui.unit.DpOffset,androidx.compose.foundation.ScrollState,androidx.compose.ui.window.PopupProperties,kotlin.Function1)"

private val radioButtonInfoUrl =
    "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#RadioButton(kotlin.Boolean,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.material3.RadioButtonColors,androidx.compose.foundation.interaction.MutableInteractionSource)"

private val sliderInfoUrl =
    "https://developer.android.com/jetpack/compose/components/slider"

private val switchInfoUrl =
    "https://developer.android.com/jetpack/compose/components/switch"

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

        ChildSection(
            title = "Date picker",
            infoUrl = datePickerInfoUrl
        ) {
            DatePickerDemo()
        }

        ChildSection(
            title = "Menus (TODO)",
            infoUrl = menusInfoUrl
        ) {
            // TODO
        }

        ChildSection(
            title = "Radio buttons",
            infoUrl = radioButtonInfoUrl
        ) {
            RadioButtonsDemo()
        }

        ChildSection(
            title = "Sliders",
            infoUrl = sliderInfoUrl
        ) {
            SlidersDemo()
        }

        ChildSection(
            title = "Switches",
            infoUrl = switchInfoUrl
        ) {
            SwtichesDemo()
        }
    }
}

@Composable
private fun SwtichesDemo() {
    OutlinedCard {
        Column(
            modifier = Modifier
                .selectableGroup()
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(32.dp),
        ) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                var checked by remember { mutableStateOf(true) }
                Switch(
                    checked = checked,
                    onCheckedChange = { checked = it }
                )

                var checked2 by remember { mutableStateOf(true) }
                Switch(
                    checked = checked2,
                    onCheckedChange = { checked2 = it },
                    thumbContent = if (checked2) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                modifier = Modifier.size(SwitchDefaults.IconSize),
                            )
                        }
                    } else {
                        {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = null,
                                modifier = Modifier.size(SwitchDefaults.IconSize),
                            )
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                Switch(
                    enabled = false,
                    checked = false,
                    onCheckedChange = null
                )
                Switch(
                    enabled = false,
                    checked = true,
                    onCheckedChange = null,
                    thumbContent = {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun SlidersDemo() {
    OutlinedCard {
        Column(
            modifier = Modifier
                .selectableGroup()
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(32.dp),
        ) {
            var sliderPosition by remember { mutableFloatStateOf(0f) }
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it }
            )
            Spacer(modifier = Modifier.height(32.dp))
            var sliderPosition2 by remember { mutableFloatStateOf(0f) }
            Slider(
                value = sliderPosition2,
                onValueChange = { sliderPosition2 = it },
                steps = 5,
                valueRange = 0f..100f
            )
            Spacer(modifier = Modifier.height(32.dp))
            var sliderPosition3 by remember { mutableStateOf(0f..100f) }
            RangeSlider(
                value = sliderPosition3,
                steps = 5,
                onValueChange = { range -> sliderPosition3 = range },
                valueRange = 0f..100f,
            )
        }
    }
}


private val radioOptions = listOf("Option 1", "Option 2", "Option 3")

@Composable
private fun RadioButtonsDemo() {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    OutlinedCard {
        Column(
            modifier = Modifier
                .selectableGroup()
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(16.dp),
        ) {

            radioOptions.forEachIndexed { ix, text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            enabled = ix < 2,
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        enabled = ix < 2,
                        selected = (text == selectedOption),
                        onClick = null // null recommended for accessibility with screenreaders
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = if (ix < 2) 1f else 0.5f)
                    )
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerDemo() {
    var openDialog by remember { mutableStateOf(false) }

    OutlinedCard {
        Row(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            TextButton(
                onClick = { openDialog = true },
            ) {
                Text("Show date picker")
            }
        }
    }


    if (openDialog) {
        val state = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { openDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    openDialog = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    openDialog = false
                }) {
                    Text("Cancel")
                }
            },
            content = {
                DatePicker(state)
            }
        )
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

            val state1 = remember { mutableStateOf(true) }
            Row(modifier = Modifier.fillMaxWidth()
                .height(56.dp)
                .clickable { state1.value = !state1.value }
                .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Option 1")
                Checkbox(
                    checked = state1.value,
                    onCheckedChange = null
                )
            }

            val state2 = remember { mutableStateOf(ToggleableState.Indeterminate) }
            Row(modifier = Modifier.fillMaxWidth()
                .height(56.dp)
                .clickable { state2.value = state2.value.nextState() }
                .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Option 2")
                TriStateCheckbox(
                    state = state2.value,
                    onClick = null
                )
            }

            val state3 = remember { mutableStateOf(ToggleableState.Off) }
            Row(modifier = Modifier.fillMaxWidth()
                .height(56.dp)
                .clickable { state3.value = state3.value.nextState() }
                .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Option 3")
                TriStateCheckbox(
                    state = state3.value,
                    onClick = null
                )
            }

            Row(modifier = Modifier.fillMaxWidth()
                .height(56.dp)
                .clickable(enabled = false) { }
                .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Option 4")
                Checkbox(
                    checked = true,
                    enabled = false,
                    onCheckedChange = null
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