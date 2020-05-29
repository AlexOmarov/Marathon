package ru.somarov.marathon.ui.main.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.core.db.entity.Runner
import ru.somarov.marathon.databinding.TwoComponentImageItemBinding

abstract class BaseAdapter<T, V : ViewDataBinding>(
    private val list: List<T>,
    private val layout: Int
) :
    RecyclerView.Adapter<BaseAdapter.BaseHolder<V>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<V> =
        BaseHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), layout, parent, false
            )
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: BaseHolder<V>, position: Int) {
        TODO("Not yet implemented")
    }

    class BaseHolder<V : ViewDataBinding>(val baseBinding: V) :
        RecyclerView.ViewHolder(baseBinding.root)
}