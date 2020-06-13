package ru.somarov.marathon.ui.main.plugin.marathon_list

import android.support.v4.media.MediaBrowserCompat
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.marathon_item.view.*
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.core.db.entity.Marathon
import ru.somarov.marathon.backend.main.core.db.entity.Runner
import ru.somarov.marathon.backend.main.core.resource.handle
import ru.somarov.marathon.databinding.MarathonCardFragmentBinding
import ru.somarov.marathon.databinding.MarathonItemBinding
import ru.somarov.marathon.databinding.TwoComponentImageItemBinding
import ru.somarov.marathon.ui.main.core.adapter.BaseAdapter

class Adapter(
    private val marathons: List<Marathon>
) : BaseAdapter<Marathon, MarathonItemBinding>(
    marathons,
    R.layout.marathon_item
) {

    override fun onBindViewHolder(holder: BaseHolder<MarathonItemBinding>, position: Int) {
        holder.baseBinding.marathon = marathons[position]
        holder.baseBinding.root.mtrl_list_item_icon.setImageResource(handle(marathons[position].id_country))
        holder.baseBinding.root.setOnClickListener { view ->
            marathons[position].id?.let {
                Navigation.findNavController(view).navigate(
                    MarathonListFragmentDirections.marathonListMarathon(it)
                )
            }
        }
    }
}