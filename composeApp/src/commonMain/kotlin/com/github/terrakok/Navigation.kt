package com.github.terrakok

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Navigation() {
    ParentSection(title = "Navigation") {
        ChildSection(
            title = "Bottom app bar",
            infoUrl = "https://developer.android.com/jetpack/compose/components/app-bars#bottom"
        ) {
            GalleryBottomAppBar()
        }
        ChildSection(
            title = "Navigation bar",
            infoUrl = "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#navigationbar"
        ) {
            GalleryNavigationBar()
        }
        ChildSection(
            title = "Navigation rail",
            infoUrl = "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#navigationrail"
        ) {
            GalleryNavigationRail()
        }
    }
}

@Composable
private fun GalleryBottomAppBar() {
    OutlinedCard {
        Box(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(16.dp)
        ) {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.Search, contentDescription = null)
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /* do something */ }
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = null)
                    }
                }
            )
        }
    }
}

@Composable
private fun GalleryNavigationBar() {
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
                        Icon(
                            imageVector = if (selected == 0) Icons.Default.Explore else Icons.Outlined.Explore,
                            contentDescription = null
                        )
                    },
                    label = { Text("Explore") }
                )
                NavigationBarItem(
                    selected = selected == 1,
                    onClick = { selected = 1 },
                    icon = {
                        Icon(
                            imageVector = if (selected == 1) Icons.Default.Pets else Icons.Outlined.Pets,
                            contentDescription = null
                        )
                    },
                    label = { Text("Pets") }
                )
                NavigationBarItem(
                    selected = selected == 2,
                    onClick = { selected = 2 },
                    icon = {
                        Icon(
                            imageVector = if (selected == 2) Icons.Default.AccountBox else Icons.Outlined.AccountBox,
                            contentDescription = null
                        )
                    },
                    label = { Text("Account") }
                )
            }
        }
    }
}

@Composable
private fun GalleryNavigationRail() {
    OutlinedCard {
        Box(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                shadowElevation = 8.dp
            ) {
                var selected by remember { mutableStateOf(0) }
                NavigationRail(
                    modifier = Modifier.padding(6.dp),
                    header = {
                        FloatingActionButton(
                            onClick = { /* do something */ }
                        ) {
                            Icon(Icons.Filled.Edit, contentDescription = null)
                        }
                    }
                ) {
                    Spacer(Modifier.size(40.dp))
                    NavigationRailItem(
                        selected = selected == 0,
                        onClick = { selected = 0 },
                        icon = {
                            Icon(
                                imageVector = if (selected == 0) Icons.Default.Inbox else Icons.Outlined.Inbox,
                                contentDescription = null
                            )
                        },
                        label = { Text("Inbox") }
                    )
                    NavigationRailItem(
                        selected = selected == 1,
                        onClick = { selected = 1 },
                        icon = {
                            Icon(
                                imageVector = if (selected == 1) Icons.Default.Send else Icons.Outlined.Send,
                                contentDescription = null
                            )
                        },
                        label = { Text("Outbox") }
                    )
                    NavigationRailItem(
                        selected = selected == 2,
                        onClick = { selected = 2 },
                        icon = {
                            Icon(
                                imageVector = if (selected == 2) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = null
                            )
                        },
                        label = { Text("Favorite") }
                    )
                    NavigationRailItem(
                        selected = selected == 3,
                        onClick = { selected = 3 },
                        icon = {
                            Icon(
                                imageVector = if (selected == 3) Icons.Default.Delete else Icons.Outlined.Delete,
                                contentDescription = null
                            )
                        },
                        label = { Text("Trash") }
                    )
                    Spacer(Modifier.size(40.dp))
                }
            }
        }
    }
}
