package es.voghdev.izvexample

class IncidenceViewModel : BaseViewModel() {
    sealed class State: BaseViewModel.State() {
        object Loading : State()
        data class Error(val error: Throwable): State()
        data class Loaded(val items: List<Item>): State()
    }

    data class Item(
        val title: String,
        val incidenceLast14Days: Float,
        val color: Color
    ) {
        enum class Color {
            GREEN, YELLOW, ORANGE, RED
        }
    }
}