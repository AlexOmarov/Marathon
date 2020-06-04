package ru.somarov.marathon.ui.main.plugin.sponsor_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.plugin.runner_card.CardWorker
import ru.somarov.marathon.databinding.RunnerCardFragmentBinding
import ru.somarov.marathon.databinding.RunnerListFragmentBinding
import ru.somarov.marathon.databinding.SponsorListFragmentBinding
import ru.somarov.marathon.ui.main.plugin.runner_list.Adapter
import ru.somarov.marathon.ui.main.plugin.runner_list.ListViewModel

class SponsorListFragment : Fragment() {

    companion object {
        fun newInstance() =
            SponsorListFragment()
    }

    private lateinit var viewModel: SponsorListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this)[SponsorListViewModel::class.java]

        val binding: SponsorListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.sponsor_list_fragment, container, false)

        viewModel.sponsors.observe(viewLifecycleOwner, Observer { sponsors ->
            binding.recyclerView.also {
                it.adapter = Adapter(sponsors)
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = Adapter(ArrayList())
            // binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    fun launchWorkingManager() {
        val request = OneTimeWorkRequest.Builder(CardWorker::class.java).build()
        WorkManager.getInstance(requireContext()).enqueue(request)
        WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(request.id).observe(
            viewLifecycleOwner, Observer { workInfo ->
            // TODO
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
