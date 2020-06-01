package ru.somarov.marathon.ui.main.core.adapter

import android.view.View

abstract class RecyclerViewItemOnClickListener<T>() {


    abstract fun onClick(v: View, position: Int)
}