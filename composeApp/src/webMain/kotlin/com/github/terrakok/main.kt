package com.github.terrakok

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    println("Debug: Hello from Material 3 gallery")
    ComposeViewport("composeApplication") {
        App()
    }
}
