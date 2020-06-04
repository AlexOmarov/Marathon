package ru.somarov.marathon.ui.main.plugin.sponsor_card

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
import ru.somarov.marathon.databinding.SponsorCardFragmentBinding
import ru.somarov.marathon.ui.main.plugin.runner_card.CardFragment

class SponsorCardFragment : Fragment() {

    companion object {
        fun newInstance() =
            CardFragment()
    }

    private lateinit var viewModelSponsor: SponsorCardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModelSponsor = ViewModelProvider(this)[SponsorCardViewModel::class.java]

        arguments?.let {
            val safeArgs = SponsorCardFragmentArgs.fromBundle(it)
            viewModelSponsor.setSponsor(safeArgs.id)
        }

        val binding: SponsorCardFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.sponsor_card_fragment, container, false)

        /*binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = Adapter(ArrayList())*/

        viewModelSponsor.sponsor.observe(viewLifecycleOwner, Observer {
            binding.sponsor = it
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
