package es.voghdev.izvexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.voghdev.izvexample.databinding.ActivityIncidenceBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class IncidenceActivity : AppCompatActivity() {
    private val coroutineScope = MainScope()
    private val viewModel: IncidenceViewModel = IncidenceViewModel(
        GetIncidenceUseCase(Dispatchers.IO)
    )
    private val binding: ActivityIncidenceBinding by lazy {
        ActivityIncidenceBinding.inflate(layoutInflater)
    }
    private lateinit var incidenceAdapter: IncidenceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        viewModel.state.observe(this) { state ->
            when (state) {
                IncidenceViewModel.State.Loading -> {
                    binding.textView.text = "Loading"
                }
                is IncidenceViewModel.State.Loaded -> {
                    binding.textView.text = ""
                    setupIncidenceList(state.items)
                }
                is IncidenceViewModel.State.Error -> {
                    binding.textView.text = "Error: ${state.error.message}"
                }
            }
        }

        coroutineScope.launch {
            viewModel.initialState()
        }
    }

    private fun setupIncidenceList(entries: List<IncidenceViewModel.Item>) {
        incidenceAdapter = IncidenceAdapter(entries)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@IncidenceActivity, RecyclerView.VERTICAL, false)
            adapter = incidenceAdapter
            isNestedScrollingEnabled = false
        }
    }
}
