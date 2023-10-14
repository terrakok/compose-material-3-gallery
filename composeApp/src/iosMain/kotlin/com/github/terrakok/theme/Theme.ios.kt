package com.github.terrakok.theme

import androidx.compose.runtime.Composable
import platform.UIKit.UIApplication
import platform.UIKit.UIStatusBarStyleDarkContent
import platform.UIKit.UIStatusBarStyleLightContent
import platform.UIKit.setStatusBarStyle

@Composable
internal actual fun SystemAppearance(isDark: Boolean) {
    UIApplication.sharedApplication.setStatusBarStyle(
        if (isDark) UIStatusBarStyleDarkContent else UIStatusBarStyleLightContent
    )
}