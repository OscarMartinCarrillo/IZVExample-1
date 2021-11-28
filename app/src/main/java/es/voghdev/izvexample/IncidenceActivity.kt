package es.voghdev.izvexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class IncidenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: IncidenceViewModel = IncidenceViewModel()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incidence)

        viewModel.state.observe(this) { state ->
            when (state) {
                IncidenceViewModel.State.Loading -> {

                }
                is IncidenceViewModel.State.Loaded -> {

                }
                is IncidenceViewModel.State.Error -> {

                }
            }
        }
    }
}