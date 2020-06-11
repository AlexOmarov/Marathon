package ru.somarov.marathon.ui.main.plugin.marathon_card

import androidx.navigation.Navigation
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.core.db.entity.Marathon
import ru.somarov.marathon.backend.main.core.db.entity.Runner
import ru.somarov.marathon.databinding.MarathonCardFragmentBinding
import ru.somarov.marathon.databinding.MarathonItemBinding
import ru.somarov.marathon.databinding.TwoComponentImageItemBinding
import ru.somarov.marathon.ui.main.core.adapter.BaseAdapter

class Adapter(
    private val runners: List<Runner>
) : BaseAdapter<Runner, TwoComponentImageItemBinding>(
    runners,
    R.layout.two_component_image_item
) {

    override fun onBindViewHolder(holder: BaseHolder<TwoComponentImageItemBinding>, position: Int) {
        holder.baseBinding.runner = runners[position]
        holder.baseBinding.root.setOnClickListener { view ->
            runners[position].id?.let {
                Navigation.findNavController(view).navigate(
                    MarathonCardFragmentDirections.marathonCardRunnerCard(it)
                )
            }
        }
    }
}