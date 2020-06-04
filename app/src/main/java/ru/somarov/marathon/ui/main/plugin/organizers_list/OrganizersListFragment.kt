package ru.somarov.marathon.ui.main.plugin.organizers_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.organizers_list.*
import kotlinx.android.synthetic.main.organizers_list.view.*
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.core.api.entity.Organizer
import ru.somarov.marathon.backend.main.plugin.runner_card.CardWorker
import ru.somarov.marathon.databinding.RunnerCardFragmentBinding
import ru.somarov.marathon.databinding.RunnerListFragmentBinding

class OrganizersListFragment : Fragment() {

    companion object {
        fun newInstance() =
            OrganizersListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.organizers_list, container, false)
        view.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        view.recyclerView.adapter = Adapter(generateOrganizers())
        view.recyclerView.setHasFixedSize(true)
        return view
    }

    fun launchWorkingManager() {
        val request = OneTimeWorkRequest.Builder(CardWorker::class.java).build()
        WorkManager.getInstance(requireContext()).enqueue(request)
        WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(request.id).observe(
            viewLifecycleOwner, Observer { workInfo ->
            // TODO
        })
    }

    private fun generateOrganizers() : List<Organizer> {
        val list = ArrayList<Organizer>()
        for (i in 0 until 10) {
            list.add(Organizer(name = "Frodo Baggins", number = i.toString(), position = "Organizer"))
        }
        return list
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
