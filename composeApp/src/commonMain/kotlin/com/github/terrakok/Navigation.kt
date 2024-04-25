package com.github.terrakok

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

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
            title = "Navigation drawer",
            infoUrl = "https://developer.android.com/jetpack/compose/components/drawer"
        ) {
            GalleryNavigationDrawer()
        }
        ChildSection(
            title = "Navigation rail",
            infoUrl = "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#navigationrail"
        ) {
            GalleryNavigationRail()
        }
        ChildSection(
            title = "Tabs",
            infoUrl = "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Tab(kotlin.Boolean,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.Function0,kotlin.Function0,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.foundation.interaction.MutableInteractionSource)"
        ) {
            GalleryTabs()
        }
        ChildSection(
            title = "Top app bars",
            infoUrl = "https://developer.android.com/jetpack/compose/components/app-bars"
        ) {
            GalleryTopAppBars()
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
                        Icon(Icons.Filled.MoreVert, contentDescription = null)
                    }
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
private fun GalleryNavigationDrawer() {
    OutlinedCard {
        Column(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                border = BorderStroke(DividerDefaults.Thickness.value.dp, DividerDefaults.color)
            ) {
                PermanentNavigationDrawer(
                    modifier = Modifier.width(300.dp),
                    drawerContent = {
                        PermanentDrawerSheet { NavigationDrawerContent() }
                    }
                ) {}
            }
            val drawerState = LocalDrawerState.current
            val coroutineScope = rememberCoroutineScope()
            TextButton(
                onClick = {
                    coroutineScope.launch {
                        if (drawerState.isClosed) {
                            drawerState.open()
                        } else {
                            drawerState.close()
                        }
                    }
                },
                content = { Text("Show modal navigation drawer") }
            )
        }
    }
}

@Composable
fun NavigationDrawerContent() {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        var selected by remember { mutableStateOf(0) }
        Text("Mail", modifier = Modifier.padding(16.dp))
        NavigationDrawerItem(
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
        NavigationDrawerItem(
            selected = selected == 1,
            onClick = { selected = 1 },
            icon = {
                Icon(
                    imageVector = if (selected == 1) Icons.AutoMirrored.Default.Send else Icons.AutoMirrored.Outlined.Send,
                    contentDescription = null
                )
            },
            label = { Text("Outbox") }
        )
        NavigationDrawerItem(
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
        NavigationDrawerItem(
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
        HorizontalDivider(Modifier.padding(8.dp))
        Text("Labels", modifier = Modifier.padding(16.dp))
        NavigationDrawerItem(
            selected = selected == 4,
            onClick = { selected = 4 },
            icon = {
                Icon(
                    imageVector = if (selected == 4) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = null
                )
            },
            label = { Text("Family") }
        )
        NavigationDrawerItem(
            selected = selected == 5,
            onClick = { selected = 5 },
            icon = {
                Icon(
                    imageVector = if (selected == 5) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = null
                )
            },
            label = { Text("School") }
        )
        NavigationDrawerItem(
            selected = selected == 6,
            onClick = { selected = 6 },
            icon = {
                Icon(
                    imageVector = if (selected == 6) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = null
                )
            },
            label = { Text("Work") }
        )
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
                                imageVector = if (selected == 1) Icons.AutoMirrored.Filled.Send else Icons.AutoMirrored.Outlined.Send,
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

@Composable
private fun GalleryTabs() {
    OutlinedCard {
        Column(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(16.dp)
        ) {
            var selected by remember { mutableStateOf(0) }
            TabRow(
                selectedTabIndex = selected
            ) {
                Tab(
                    selected = selected == 0,
                    onClick = { selected = 0 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Videocam,
                            contentDescription = null
                        )
                    },
                    text = { Text("Video") }
                )
                Tab(
                    selected = selected == 1,
                    onClick = { selected = 1 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = null
                        )
                    },
                    text = { Text("Photo") }
                )
                Tab(
                    selected = selected == 2,
                    onClick = { selected = 2 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.MusicNote,
                            contentDescription = null
                        )
                    },
                    text = { Text("Audio") }
                )
            }
            Spacer(Modifier.size(16.dp))

            var selected2 by remember { mutableStateOf(0) }
            val items = List(10) { "Tab #$it" }
            ScrollableTabRow(
                selectedTabIndex = selected2
            ) {
                items.forEachIndexed { i, item ->
                    Tab(
                        selected = selected2 == i,
                        onClick = { selected2 = i },
                        text = { Text(item) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GalleryTopAppBars() {
    OutlinedCard {
        Column(
            modifier = Modifier
                .requiredWidthIn(400.dp)
                .width(600.dp)
                .padding(16.dp)
        ) {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = { Text("CenterAligned") },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null
                        )
                    }
                }
            )
            Spacer(Modifier.size(16.dp))

            @Composable
            fun RowScope.Actions() {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.AttachFile,
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Event,
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null
                    )
                }
            }
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = { Text("Small") },
                actions = { Actions() }
            )
            Spacer(Modifier.size(16.dp))
            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = { Text("Medium") },
                actions = { Actions() }
            )
            Spacer(Modifier.size(16.dp))
            LargeTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = { Text("Large") },
                actions = { Actions() }
            )
        }
    }
}
