package ru.somarov.marathon.ui.main.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, V : ViewDataBinding> (
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

    class BaseHolder<V : ViewDataBinding>(val baseBinding: V) :
        RecyclerView.ViewHolder(baseBinding.root) {

    }
}