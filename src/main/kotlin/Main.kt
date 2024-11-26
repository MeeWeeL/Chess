import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import presentation.Screen
import presentation.StateService

fun main() = application {
    val service = StateService()
    Window(onCloseRequest = ::exitApplication) {
        MaterialTheme {
            val state by service.state.collectAsState()
            Screen(state, service)
        }
    }
}
