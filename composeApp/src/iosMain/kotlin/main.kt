import androidx.compose.ui.window.ComposeUIViewController
import com.github.terrakok.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
