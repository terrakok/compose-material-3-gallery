import androidx.compose.ui.window.ComposeUIViewController
import androidx.compose.ui.uikit.OnFocusBehavior
import com.github.terrakok.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController(
    configure = {
        //Required for WindowInsets behaviour.
        //Analog of Android Manifest activity.windowSoftInputMode="adjustNothing"
        onFocusBehavior = OnFocusBehavior.DoNothing
    }
) { App() }
