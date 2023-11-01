package com.github.terrakok

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentScreen() {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            BottomSheetContent()
        }
    ) { _ ->
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
            Containment(
                toggleBottomSheet = {
                    scope.launch { scaffoldState.bottomSheetState.expand() }
                }
            )
        }
    }
}