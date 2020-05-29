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
import ru.somarov.marathon.backend.main.plugin.runner_card.worker.CardWorker
import ru.somarov.marathon.databinding.RunnerCardFragmentBinding
import ru.somarov.marathon.ui.main.plugin.runner_card.adapter.Adapter
import ru.somarov.marathon.ui.main.plugin.runner_card.viewmodel.CardViewModel

class CardFragment : Fragment() {

    companion object {
        fun newInstance() =
            CardFragment()
    }

    private lateinit var viewModel: CardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this)[CardViewModel::class.java]

        val binding: RunnerCardFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.runner_card_fragment, container, false)

        viewModel.runners.observe(viewLifecycleOwner, Observer { runners ->
            binding.recyclerView.also {
                it.adapter = Adapter(runners)
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = Adapter(ArrayList())
        binding.runnerCardViewModel = viewModel
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
