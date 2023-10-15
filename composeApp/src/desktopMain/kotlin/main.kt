import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dimension
import com.github.terrakok.App

fun main() = application {
    Window(
        title = "Material 3 gallery",
        state = rememberWindowState(width = 500.dp, height = 800.dp),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(500, 800)
        App()
    }
}