package ru.somarov.marathon.ui.main.plugin.runner_list

import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.two_component_image_item.view.*
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.core.db.entity.Runner
import ru.somarov.marathon.backend.main.core.resource.handle
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
        holder.baseBinding.root.mtrl_list_item_icon.setImageResource(handle(runners[position].id_country))
        holder.baseBinding.root.setOnClickListener { view ->
            runners[position].id?.let {
                Navigation.findNavController(view).navigate(
                    ListFragmentDirections.runnerListRunner(it)
                )
            }
        }
    }
}