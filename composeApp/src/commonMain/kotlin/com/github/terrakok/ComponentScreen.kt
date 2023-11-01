package com.github.terrakok

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ComponentScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
    ) {
        Actions()
        Spacer(Modifier.size(16.dp))
        TextInputs()
        Spacer(Modifier.size(16.dp))
        Communication()
        Spacer(Modifier.size(16.dp))
        Navigation()
        Spacer(Modifier.size(16.dp))
        Containment()
        Spacer(Modifier.size(16.dp))
        Selection()
    }
}