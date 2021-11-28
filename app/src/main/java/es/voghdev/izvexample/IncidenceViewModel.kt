package es.voghdev.izvexample

class IncidenceViewModel(
    val getIncidence: GetIncidenceUseCase
) : BaseViewModel() {
    sealed class State : BaseViewModel.State() {
        object Loading : State()
        data class Error(val error: Throwable) : State()
        data class Loaded(val items: List<Item>) : State()
    }

    data class Item(
        val title: String,
        val incidenceLast14Days: Float,
        val color: Color
    ) {
        enum class Color {
            GREEN, YELLOW, ORANGE, RED
        }

        companion object {
            fun fromEntry(entry: IncidenceEntry) = Item(
                title = entry.title,
                incidenceLast14Days = entry.incidenceLast14Days,
                color = when {
                    entry.incidenceLast14Days < 100 -> Color.GREEN
                    entry.incidenceLast14Days < 250 -> Color.YELLOW
                    entry.incidenceLast14Days < 350 -> Color.ORANGE
                    else -> Color.RED
                }
            )
        }
    }

    suspend fun initialState() {
        viewState.value = State.Loading
        val entries = getIncidence()
        viewState.value = State.Loaded(entries.map { Item.fromEntry(it) })
    }
}