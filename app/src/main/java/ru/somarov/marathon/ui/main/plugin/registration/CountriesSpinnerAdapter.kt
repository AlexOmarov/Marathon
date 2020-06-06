package ru.somarov.marathon.ui.main.plugin.registration

import android.content.Context
import android.widget.ArrayAdapter
import ru.somarov.marathon.backend.main.core.db.entity.Country

class CountriesSpinnerAdapter(context: Context, private val resource: Int, private val list: ArrayList<Country>): ArrayAdapter<Country>(context, resource, list) {

   /* override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var holder: ViewHolder

        if(convertView == null){
            convertView = vi.inflate(resource, null) //error in this line
            holder = ViewHolder()

            holder.image = convertView.findViewById(R.id.myImage) as ImageView?

            convertView.tag(holder) //error in this line

        } else {
            holder = convertView.tag as ViewHolder
        }

        return convertView
    }

    internal class ViewHolder {
        var image: ImageView? = null
    }*/
}