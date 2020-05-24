package ru.somarov.marathon.ui.main.plugin.runner_card.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.plugin.runner_card.entity.Runner
import ru.somarov.marathon.databinding.TwoComponentImageItemBinding

class RunnerCardFragmentRecyclerViewAdapter(private val runners: List<Runner>):
    RecyclerView.Adapter<RunnerCardFragmentRecyclerViewAdapter.RunnerCardFragmentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunnerCardFragmentHolder =
        RunnerCardFragmentHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.two_component_image_item,
            parent,
            false))

    override fun getItemCount() = runners.size

    override fun onBindViewHolder(holder: RunnerCardFragmentHolder, position: Int) {
        holder.twoComponentImageItem.runner = runners[position]
    }

    class RunnerCardFragmentHolder(val twoComponentImageItem: TwoComponentImageItemBinding):
        RecyclerView.ViewHolder(twoComponentImageItem.root)

}