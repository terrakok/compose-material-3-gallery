package com.github.terrakok

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.TextSnippet
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailDefaults
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.github.terrakok.theme.AppTheme

const val narrowScreenWidthThreshold = 960

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App() = AppTheme {
    var screenWidth by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .onGloballyPositioned {
                screenWidth = it.size.width
            }
    ) {
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

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Material 3") }
                )
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
                                    onClick = { selectedScreen = screen }
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
                                onClick = { selectedScreen = screen }
                            )
                        }
                    }
                }
            }
        )
    }
}