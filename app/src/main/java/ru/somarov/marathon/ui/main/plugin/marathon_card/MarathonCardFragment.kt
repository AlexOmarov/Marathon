package ru.somarov.marathon.ui.main.plugin.marathon_card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.plugin.login.LoginRepository
import ru.somarov.marathon.backend.main.plugin.runner_card.CardWorker
import ru.somarov.marathon.databinding.MarathonCardFragmentBinding
import ru.somarov.marathon.ui.main.core.viewmodel.AuthenticationViewModel
import ru.somarov.marathon.ui.main.core.viewmodel.AuthenticationViewModelFactory
import ru.somarov.marathon.ui.main.plugin.marathon_card.Adapter
import ru.somarov.marathon.ui.main.plugin.sponsor_card.SponsorCardFragment
import java.lang.Exception

class MarathonCardFragment : Fragment() {

    companion object {
        fun newInstance() =
            SponsorCardFragment()
    }

    private lateinit var viewModel: MarathonCardViewModel
    private lateinit var authViewModel: AuthenticationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this)[MarathonCardViewModel::class.java]

        authViewModel = activity?.run {
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

        println("Marathon card init RUNNER: ${authViewModel.runner.value}")


        arguments?.let {
            val safeArgs = MarathonCardFragmentArgs.fromBundle(it)
            viewModel.setupMarathonCard(safeArgs.id)
        }

        val binding: MarathonCardFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.marathon_card_fragment, container, false)

        /*binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = Adapter(ArrayList())*/

        viewModel.marathon.observe(viewLifecycleOwner, Observer {
            binding.marathon = it
        })

        binding.lifecycleOwner = this

        binding.signup.setOnClickListener {
            val idRunner = authViewModel.runner.value?.id
            val idMarathon = viewModel.marathon.value?.id
            val navaction: NavDirections = if(idRunner != null && idMarathon != null) {
                viewModel.signup(idRunner, idMarathon)
                println("$idRunner $idMarathon")
                MarathonCardFragmentDirections.marathonCardPerson(0)
            }  else {
                MarathonCardFragmentDirections.marathonCardLogin(0)
            }
            Navigation.findNavController(it).navigate(navaction)
        }

        viewModel.runners.observe(viewLifecycleOwner, Observer { runners ->
            binding.runners.also {
                it.adapter = Adapter(runners)
            }
        })

        binding.runners.layoutManager = LinearLayoutManager(requireContext())
        binding.runners.adapter = Adapter(ArrayList())

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
