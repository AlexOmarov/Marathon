package ru.somarov.marathon.ui.main.plugin.organizers_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.organizers_item.view.*
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.core.api.entity.Organizer

class Adapter(
    private val organizers: List<Organizer>
) : RecyclerView.Adapter<Adapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.marathon_item,
           parent,
           false)
        return Holder(itemView)
    }

    override fun getItemCount() = organizers.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentItem = organizers[position]
        val str = "${currentItem.name}, ${currentItem.position}"
        holder.view1.text = str
        holder.view2.text = currentItem.number
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view1: TextView = itemView.mtrl_list_item_text
        val view2: TextView = itemView.mtrl_list_item_secondary_text

    }
}