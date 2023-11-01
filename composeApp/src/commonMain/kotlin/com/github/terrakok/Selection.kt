package com.github.terrakok

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp

private val checkboxesInfoUrl =
    "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Checkbox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.material3.CheckboxColors,androidx.compose.foundation.interaction.MutableInteractionSource)"

@Composable
fun Selection() {
    ParentSection("Selection") {
        ChildSection(
            title = "Checkboxes",
            infoUrl = checkboxesInfoUrl
        ) {
            CheckboxesDemo()
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