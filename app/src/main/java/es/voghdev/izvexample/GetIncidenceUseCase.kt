package es.voghdev.izvexample

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetIncidenceUseCase(
    val dispatcher: CoroutineDispatcher
) {
    private val sampleEntries = listOf(
        IncidenceEntry("Granada", 0f),
        IncidenceEntry("Armilla", 10f),
        IncidenceEntry("Albolote", 580f),
        IncidenceEntry("Las Gabias", 20f),
        IncidenceEntry("Cullar Vega", 15f)
    )

    operator suspend  fun invoke(): List<IncidenceEntry> {
        withContext(Dispatchers.IO) {
            Thread.sleep(3000)
        }
        return sampleEntries
    }
}