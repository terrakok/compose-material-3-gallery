package com.github.terrakok

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun Containment(toggleBottomSheet: (Boolean) -> Unit) {
    val modalBottomSheetInfoUrl =
        "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary?hl=en#ModalBottomSheet(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.material3.SheetState,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Color,kotlin.Function0,androidx.compose.foundation.layout.WindowInsets,androidx.compose.ui.window.SecureFlagPolicy,kotlin.Function1)"

    ParentSection("Containment") {
        ChildSection(
            title = "Bottom sheet",
            infoUrl = modalBottomSheetInfoUrl
        ) {
            BottomSheetDemo(toggleBottomSheet)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheetDemo(toggleBottomSheet: (Boolean) -> Unit) {
    var isModalSheetOpen by rememberSaveable { mutableStateOf(false) }

    OutlinedCard {
        Row(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(16.dp)
        ) {
            TextButton(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .weight(1f),
                enabled = true,
                onClick = {
                    isModalSheetOpen = true
                },
                content = { Text("Show modal bottom sheet") }
            )

            var bottomSheetShown by remember { mutableStateOf(false) }

            TextButton(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .weight(1f),
                enabled = true,
                onClick = {
                    bottomSheetShown = !bottomSheetShown
                    toggleBottomSheet(bottomSheetShown)
                },
                content = {
                    Text("${if(bottomSheetShown) "Hide" else "Show"} bottom sheet")
                }
            )
        }
    }

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (isModalSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = { isModalSheetOpen = false },
            sheetState = bottomSheetState,
        ) {
            BottomSheetContent()
        }
    }
}

@Composable
internal fun ColumnScope.BottomSheetContent() {
    Row(modifier = Modifier.padding(vertical = 20.dp).align(Alignment.CenterHorizontally)) {
        BottomSheetButton(
            title = "Share",
            icon = Icons.Outlined.Share
        )
        BottomSheetButton(
            title = "Add to",
            icon = Icons.Outlined.Add
        )
        BottomSheetButton(
            title = "Trash",
            icon = Icons.Outlined.Delete
        )
        BottomSheetButton(
            title = "Archive",
            icon = Icons.Outlined.Archive
        )
        BottomSheetButton(
            title = "Settings",
            icon = Icons.Outlined.Settings
        )
        BottomSheetButton(
            title = "Favorite",
            icon = Icons.Outlined.Favorite
        )
    }
}

@Composable
private fun BottomSheetButton(
    icon: ImageVector,
    title: String
)  {
    NavigationRailItem(
        selected = false,
        onClick = { },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        label = { Text(title) }
    )
}