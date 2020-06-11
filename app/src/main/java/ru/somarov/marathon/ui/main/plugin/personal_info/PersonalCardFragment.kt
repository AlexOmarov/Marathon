package ru.somarov.marathon.ui.main.plugin.personal_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.plugin.login.LoginRepository
import ru.somarov.marathon.backend.main.plugin.runner_card.CardWorker
import ru.somarov.marathon.databinding.RunnerCardFragmentBinding
import ru.somarov.marathon.ui.main.core.viewmodel.AuthenticationViewModel
import ru.somarov.marathon.ui.main.core.viewmodel.AuthenticationViewModelFactory
import ru.somarov.marathon.ui.main.plugin.sponsor_card.SponsorCardFragment
import ru.somarov.marathon.ui.main.plugin.sponsor_card.SponsorCardViewModel
import java.lang.Exception

class PersonalCardFragment : Fragment() {

    companion object {
        fun newInstance() =
            SponsorCardFragment()
    }

    private lateinit var viewModel: AuthenticationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModel = activity?.run {
            ViewModelProviders.of(
                this, AuthenticationViewModelFactory(
                    LoginRepository(
                        runnerDao = MarathonDatabase.getDatabase(requireContext()).runnerDao,
                        genderDao = MarathonDatabase.getDatabase(requireContext()).genderDao,
                        countryDao = MarathonDatabase.getDatabase(requireContext()).countryDao,
                        dataSource = RemoteDataSource(ServiceBuilder.buildService(RemoteService::class.java))
                    )
                )
            ).get(AuthenticationViewModel::class.java)
        } ?: throw Exception()


        println("Personal card init RUNNER:  ${viewModel.runner.value}")

            val binding: RunnerCardFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.runner_card_fragment, container, false)


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
