package es.voghdev.izvexample

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.voghdev.izvexample.databinding.IncidenceRowBinding
import kotlinx.android.extensions.LayoutContainer

class IncidenceAdapter(private val entries: List<IncidenceViewModel.Item>) :
    RecyclerView.Adapter<IncidenceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidenceViewHolder {
        val binding =
            IncidenceRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IncidenceViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: IncidenceViewHolder, position: Int) =
        holder.bind(entries[position])

    override fun getItemCount() = entries.size
}

class IncidenceViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val binding = IncidenceRowBinding.bind(containerView)

    @SuppressLint("SetTextI18n")
    fun bind(entry: IncidenceViewModel.Item) = with(binding) {
        incidenceTitle.text = entry.title
        incidenceSubtitle.text = entry.incidenceLast14Days.toString()
        image.setImageResource(
            when (entry.color) {
                IncidenceViewModel.Item.Color.GREEN -> R.drawable.oval_green
                IncidenceViewModel.Item.Color.YELLOW -> R.drawable.oval_yellow
                IncidenceViewModel.Item.Color.ORANGE -> R.drawable.oval_orange
                IncidenceViewModel.Item.Color.RED -> R.drawable.oval_red
            }
        )
    }
}
