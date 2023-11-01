package com.github.terrakok

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PeopleOutline
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun Communication() {
    ParentSection(title = "Communication") {
        ChildSection(
            title = "Badges",
            infoUrl = "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Badge(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,kotlin.Function1)"
        ) {
            Badges()
        }
        ChildSection(
            title = "Progress indicators",
            infoUrl = "https://developer.android.com/jetpack/compose/components/progress"
        ) {
            ProgressIndicators()
        }
        ChildSection(
            title = "Snackbar",
            infoUrl = "https://developer.android.com/jetpack/compose/components/snackbar"
        ) {
            SnackbarButton()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Badges() {
    OutlinedCard {
        Box(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(16.dp)
        ) {
            var selected by remember { mutableStateOf(0) }
            NavigationBar {
                NavigationBarItem(
                    selected = selected == 0,
                    onClick = { selected = 0 },
                    icon = {
                        BadgedBox(
                            badge = {
                                Badge { Text("999+") }
                            }) {
                            Icon(
                                imageVector = if (selected == 0) Icons.Default.Mail else Icons.Default.MailOutline,
                                contentDescription = null
                            )
                        }
                    },
                    label = { Text("Mail") }
                )
                NavigationBarItem(
                    selected = selected == 1,
                    onClick = { selected = 1 },
                    icon = {
                        BadgedBox(
                            badge = {
                                Badge { Text("10") }
                            }) {
                            Icon(
                                imageVector = if (selected == 1) Icons.Default.ChatBubble else Icons.Default.ChatBubbleOutline,
                                contentDescription = null
                            )
                        }
                    },
                    label = { Text("Chat") }
                )
                NavigationBarItem(
                    selected = selected == 2,
                    onClick = { selected = 2 },
                    icon = {
                        BadgedBox(
                            badge = {
                                Badge()
                            }) {
                            Icon(
                                imageVector = if (selected == 2) Icons.Default.People else Icons.Default.PeopleOutline,
                                contentDescription = null
                            )
                        }
                    },
                    label = { Text("Room") }
                )
                NavigationBarItem(
                    selected = selected == 3,
                    onClick = { selected = 3 },
                    icon = {
                        BadgedBox(
                            badge = {
                                Badge { Text("3") }
                            }) {
                            Icon(
                                imageVector = if (selected == 3) Icons.Default.Videocam else Icons.Outlined.Videocam,
                                contentDescription = null
                            )
                        }
                    },
                    label = { Text("Meet") }
                )
            }
        }
    }
}

@Composable
fun ProgressIndicators() {
    OutlinedCard {
        Row(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var isProgress by remember { mutableStateOf(false) }
            IconButton(
                onClick = { isProgress = !isProgress }
            ) {
                Icon(
                    imageVector = if (isProgress) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            if (isProgress) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.size(16.dp))
                LinearProgressIndicator(modifier = Modifier.weight(1f))
            } else {
                CircularProgressIndicator(0.7f)
                Spacer(modifier = Modifier.size(16.dp))
                LinearProgressIndicator(modifier = Modifier.weight(1f), progress = 0.7f)
            }
        }
    }
}

@Composable
fun SnackbarButton() {
    OutlinedCard {
        Box(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            val coroutineScope = rememberCoroutineScope()
            val snackbarHostState = LocalSnackbarHostState.current
            TextButton(
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "This is a snackbar",
                            actionLabel = "Close"
                        )
                    }
                }
            ) {
                Text("Show snackbar")
            }
        }
    }
}