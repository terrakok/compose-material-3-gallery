import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.github.terrakok.App
import java.awt.Dimension

fun main() = application {
    Window(
        title = "Material 3 gallery",
        state = rememberWindowState(width = 500.dp, height = 700.dp),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(500, 600)
        App()
    }
}