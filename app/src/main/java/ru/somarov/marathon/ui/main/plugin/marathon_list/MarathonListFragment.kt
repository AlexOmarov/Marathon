package ru.somarov.marathon.ui.main.plugin.marathon_list

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
import ru.somarov.marathon.databinding.MarathonListFragmentBinding
import ru.somarov.marathon.databinding.RunnerCardFragmentBinding
import ru.somarov.marathon.databinding.RunnerListFragmentBinding

class MarathonListFragment : Fragment() {

    companion object {
        fun newInstance() =
            MarathonListFragment()
    }

    private lateinit var viewModel: MarathonListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this)[MarathonListViewModel::class.java]

        val binding: MarathonListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.marathon_list_fragment, container, false)

        binding.recyclerView.adapter = Adapter(ArrayList())

        viewModel.marathons.observe(viewLifecycleOwner, Observer { marathons ->
            binding.recyclerView.also {
                it.adapter = Adapter(marathons)
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.model = viewModel
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
