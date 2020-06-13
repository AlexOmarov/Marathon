package ru.somarov.marathon.ui.main.plugin.sponsor_list

import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.two_component_image_item.view.*
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.core.db.entity.Runner
import ru.somarov.marathon.backend.main.core.db.entity.Sponsor
import ru.somarov.marathon.backend.main.core.resource.handle
import ru.somarov.marathon.databinding.SponsorItemBinding
import ru.somarov.marathon.databinding.TwoComponentImageItemBinding
import ru.somarov.marathon.ui.main.core.adapter.BaseAdapter

class Adapter(
    private val sponsors: List<Sponsor>
) : BaseAdapter<Sponsor, SponsorItemBinding>(
    sponsors,
    R.layout.sponsor_item
) {

    override fun onBindViewHolder(holder: BaseHolder<SponsorItemBinding>, position: Int) {
        holder.baseBinding.sponsor = sponsors[position]
        holder.baseBinding.root.mtrl_list_item_icon.setImageResource(handle(sponsors[position].id_country))
        holder.baseBinding.root.setOnClickListener { view ->
            sponsors[position].id?.let {
                Navigation.findNavController(view).navigate(
                    SponsorListFragmentDirections.sponsorListSponsor(it)
                )
            }
        }
    }
}