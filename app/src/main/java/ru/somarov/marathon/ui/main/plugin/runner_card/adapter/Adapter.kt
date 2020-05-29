package ru.somarov.marathon.ui.main.plugin.runner_card.adapter

import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.core.db.entity.Runner
import ru.somarov.marathon.databinding.TwoComponentImageItemBinding
import ru.somarov.marathon.ui.main.core.adapter.BaseAdapter

class Adapter(private val runners: List<Runner>):
    BaseAdapter<Runner,TwoComponentImageItemBinding>(runners,R.layout.two_component_image_item) {

    override fun onBindViewHolder(holder: BaseHolder<TwoComponentImageItemBinding>, position: Int) {
        holder.baseBinding.runner = runners[position]
    }
}