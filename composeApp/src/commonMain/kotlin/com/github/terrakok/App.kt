package com.github.terrakok

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TextSnippet
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.github.terrakok.theme.AppTheme
import com.github.terrakok.theme.LocalThemeIsDark
import org.testkotlin2.libB.LibBClass
import org.testkotlin2.libB.LibBClass2

const val narrowScreenWidthThreshold = 1300

val LocalSnackbarHostState =
    compositionLocalOf<SnackbarHostState> { error("SnackbarHostState is not found") }

val LocalDrawerState =
    compositionLocalOf<DrawerState> { error("DrawerState is not found") }

@OptIn(ExperimentalMaterial3Api::class)
val LocalBottomSheetScaffoldState =
    compositionLocalOf<BottomSheetScaffoldState> { error("BottomSheetScaffoldState is not found") }

data class Screen(
    val title: String,
    val icon: ImageVector,
    val content: @Composable () -> Unit
)

val screens = listOf(
    Screen("Components", Icons.Filled.Widgets) { ComponentScreen() },
    Screen("Color", Icons.Filled.FormatPaint) { ColorScreen() },
    Screen("Typography", Icons.AutoMirrored.Filled.TextSnippet) { TypographyScreen() },
    Screen("Elevation", Icons.Filled.Opacity) { ElevationScreen() },
)

class TC {
    val l = LibBClass()
    val b = LibBClass2()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App() = AppTheme {
    var screenWidth by remember { mutableStateOf(0) }

    val snackbarHostState = remember { SnackbarHostState() }
    var selectedScreen by remember { mutableStateOf(screens[0]) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val scaffoldState = rememberBottomSheetScaffoldState(
        rememberStandardBottomSheetState(SheetValue.Hidden, skipHiddenState = false)
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet { NavigationDrawerContent() }
        },
    ) {
        Scaffold(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .onGloballyPositioned {
                    screenWidth = it.size.width
                },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            topBar = {
                @Composable
                fun RowScope.actions() {
                    IconButton(
                        onClick = { openUrl("https://github.com/terrakok/compose-material-3-gallery") }
                    ) {
                        Icon(
                            Icons.Default.DocumentScanner,
                            contentDescription = null
                        )
                    }
                    var isDark by LocalThemeIsDark.current
                    IconButton(
                        onClick = { isDark = !isDark }
                    ) {
                        Icon(
                            if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = "Toggle brightness"
                        )
                    }
                    SelectSeedColorButton()
                    SelectColorExtractionImageButton()
                }

                if (screenWidth <= narrowScreenWidthThreshold) {
                    TopAppBar(
                        title = { Text(text = "Material 3") },
                        scrollBehavior = scrollBehavior,
                        actions = { actions() }
                    )
                } else {
                    CenterAlignedTopAppBar(
                        title = { Text(text = "Material 3") },
                        scrollBehavior = scrollBehavior,
                        actions = { actions() }
                    )
                }
            },
            content = {
                Row(
                    modifier = Modifier.padding(it).consumeWindowInsets(WindowInsets.systemBars)
                ) {
                    if (screenWidth > narrowScreenWidthThreshold) {
                        NavigationRail(
                            modifier = Modifier.padding(6.dp)
                        ) {
                            screens.forEach { screen ->
                                NavigationRailItem(
                                    icon = { Icon(screen.icon, contentDescription = screen.title) },
                                    label = { Text(screen.title) },
                                    selected = selectedScreen == screen,
                                    onClick = {
                                        scrollBehavior.state.contentOffset = 0f
                                        selectedScreen = screen
                                    }
                                )
                            }
                        }
                    }
                    BottomSheetScaffold(
                        scaffoldState = scaffoldState,
                        sheetContent = { BottomSheetContent() }
                    ) {
                        CompositionLocalProvider(
                            LocalSnackbarHostState provides snackbarHostState,
                            LocalBottomSheetScaffoldState provides scaffoldState,
                            LocalDrawerState provides drawerState
                        ) {
                            selectedScreen.content()
                        }
                    }
                }
            },
            bottomBar = {
                if (screenWidth <= narrowScreenWidthThreshold) {
                    NavigationBar {
                        screens.forEach { screen ->
                            NavigationBarItem(
                                icon = { Icon(screen.icon, contentDescription = screen.title) },
                                label = { Text(screen.title) },
                                selected = selectedScreen == screen,
                                onClick = {
                                    scrollBehavior.state.contentOffset = 0f
                                    selectedScreen = screen
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}

internal expect fun openUrl(url: String?)