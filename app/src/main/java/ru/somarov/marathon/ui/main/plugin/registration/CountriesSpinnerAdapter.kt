package ru.somarov.marathon.ui.main.plugin.registration

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ru.somarov.marathon.backend.main.core.db.entity.Country

class CountriesSpinnerAdapter(context: Context, private val resource: Int, private val list: List<Country>): ArrayAdapter<Country>(context, resource, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view =  convertView as TextView? ?: LayoutInflater.from(context).inflate(resource, parent, false) as TextView
        view.text = list[position].name
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view =  convertView as TextView? ?: LayoutInflater.from(context).inflate(resource, parent, false) as TextView
        view.text = list[position].name
        return view
    }


}