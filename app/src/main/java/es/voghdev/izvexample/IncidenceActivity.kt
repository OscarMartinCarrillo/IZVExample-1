package es.voghdev.izvexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        viewModel.state.observe(this) { state ->
            when (state) {
                IncidenceViewModel.State.Loading -> {
                    binding.textView.text = "Loading"
                }
                is IncidenceViewModel.State.Loaded -> {
                    binding.textView.text = "${state.items.count()} items"
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
}