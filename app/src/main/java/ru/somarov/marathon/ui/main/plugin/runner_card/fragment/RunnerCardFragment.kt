package ru.somarov.marathon.ui.main.plugin.runner_card.fragment

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
import ru.somarov.marathon.backend.main.plugin.runner_card.work_manager.RunnerCardWorker
import ru.somarov.marathon.databinding.RunnerCardFragmentBinding
import ru.somarov.marathon.databinding.TwoComponentImageItemBinding
import ru.somarov.marathon.ui.main.plugin.runner_card.adapter.RunnerCardFragmentRecyclerViewAdapter
import ru.somarov.marathon.ui.main.plugin.runner_card.viewmodel.RunnerCardViewModel

class RunnerCardFragment : Fragment() {

    companion object {
        fun newInstance() =
            RunnerCardFragment()
    }

    private lateinit var viewModel: RunnerCardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this)[RunnerCardViewModel::class.java]

        val binding: RunnerCardFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.runner_card_fragment, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.runners.observe(viewLifecycleOwner, Observer { runners ->
            binding.recyclerView.also {
                it.adapter = RunnerCardFragmentRecyclerViewAdapter(runners)
            }
        })

        binding.runnerCardViewModel = viewModel
        binding.lifecycleOwner = this

        val request = OneTimeWorkRequest.Builder(RunnerCardWorker::class.java).build()
        WorkManager.getInstance(requireContext()).enqueue(request)
        WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(request.id).observe(viewLifecycleOwner, Observer { workInfo ->
            binding.changeName.also {
                it.text = workInfo.state.name
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
