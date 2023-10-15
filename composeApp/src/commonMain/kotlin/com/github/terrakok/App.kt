package com.github.terrakok

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.TextSnippet
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import com.github.terrakok.theme.AppTheme
import com.github.terrakok.theme.LocalThemeIsDark

const val narrowScreenWidthThreshold = 1300

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App() = AppTheme {
    var screenWidth by remember { mutableStateOf(0) }

    data class Screen(
        val title: String,
        val icon: ImageVector,
        val content: @Composable () -> Unit
    )

    val screens = listOf(
        Screen("Components", Icons.Filled.Widgets) { ComponentScreen() },
        Screen("Color", Icons.Filled.FormatPaint) { ColorScreen() },
        Screen("Typography", Icons.Filled.TextSnippet) { TypographyScreen() },
        Screen("Elevation", Icons.Filled.Opacity) { ElevationScreen() },
    )
    var selectedScreen by remember { mutableStateOf(screens[0]) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .onGloballyPositioned {
                screenWidth = it.size.width
            },
        topBar = {
            @Composable
            fun RowScope.actions() {
                var isDark by LocalThemeIsDark.current
                IconButton(
                    onClick = { isDark = !isDark }
                ) {
                    Icon(
                        if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                        contentDescription = null
                    )
                }
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
                modifier = Modifier.padding(it)
            ) {
                if (screenWidth > narrowScreenWidthThreshold) {
                    NavigationRail {
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
                selectedScreen.content()
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

internal expect fun openUrl(url: String?)