package ru.somarov.marathon.ui.main.plugin.runner_card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.plugin.runner_card.CardWorker
import ru.somarov.marathon.databinding.RunnerCardFragmentBinding
import ru.somarov.marathon.ui.main.plugin.sponsor_card.SponsorCardFragment
import ru.somarov.marathon.ui.main.plugin.sponsor_card.SponsorCardViewModel

class CardFragment : Fragment() {

    companion object {
        fun newInstance() =
            SponsorCardFragment()
    }

    private lateinit var viewModel: CardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this)[CardViewModel::class.java]

        arguments?.let {
            val safeArgs = CardFragmentArgs.fromBundle(it)
            viewModel.setRunner(safeArgs.id)
        }

        val binding: RunnerCardFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.runner_card_fragment, container, false)

        /*binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = Adapter(ArrayList())*/

        viewModel.runner.observe(viewLifecycleOwner, Observer {
            binding.runner = it
        })

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
