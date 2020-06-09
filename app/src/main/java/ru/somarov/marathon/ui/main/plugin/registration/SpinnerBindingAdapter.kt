package ru.somarov.marathon.ui.main.plugin.registration

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

/**
 * fill the Spinner with all available items.
 * Set the Spinner selection to selectedProject.
 * If the selection changes, call the InverseBindingAdapter
 */
/*
@BindingAdapter(value = ["items", "selectedItem", "selectedItemAttrChanged"], requireAll = false)
fun <T> setItems(spinner: Spinner, items: List<T>?, selectedItem: T, listener: InverseBindingListener, adapter: Class<ArrayAdapter<T>>) {
    if (items == null) return
    spinner.adapter = adapter.getConstructor().newInstance(spinner.context, android.R.layout.simple_spinner_dropdown_item, items)
    setCurrentSelection(spinner, selectedItem)
    setSpinnerListener(spinner, listener)
}
*/

@InverseBindingAdapter(attribute = "selectedItem")
fun <T> getSelectedItem(spinner: Spinner): T {
    return spinner.selectedItem as T
}

/*
private fun setSpinnerListener(spinner: Spinner, listener: InverseBindingListener) {
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) = listener.onChange()
        override fun onNothingSelected(adapterView: AdapterView<*>) = listener.onChange()
    }
}

private fun <T> setCurrentSelection(spinner: Spinner, selectedItem: T): Boolean {
    for (index in 0 until spinner.adapter.count) {
        if (spinner.getItemAtPosition(index) == selectedItem) {
            spinner.setSelection(index)
            return true
        }
    }
    return false
}*/
