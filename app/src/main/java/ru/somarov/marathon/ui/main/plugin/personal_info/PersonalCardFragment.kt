package ru.somarov.marathon.ui.main.plugin.personal_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.personal_card_fragment.view.*
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.core.resource.handle
import ru.somarov.marathon.backend.main.plugin.login.LoginRepository
import ru.somarov.marathon.databinding.PersonalCardFragmentBinding
import ru.somarov.marathon.ui.main.core.viewmodel.AuthenticationViewModel
import ru.somarov.marathon.ui.main.core.viewmodel.AuthenticationViewModelFactory
import ru.somarov.marathon.ui.main.plugin.sponsor_card.SponsorCardFragment

class PersonalCardFragment : Fragment() {

    companion object {
        fun newInstance() =
            SponsorCardFragment()
    }

    private lateinit var viewModel: AuthenticationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = activity?.run {
            ViewModelProvider(
                this, AuthenticationViewModelFactory(
                    LoginRepository(
                        runnerDao = MarathonDatabase.getDatabase(requireContext()).runnerDao,
                        genderDao = MarathonDatabase.getDatabase(requireContext()).genderDao,
                        countryDao = MarathonDatabase.getDatabase(requireContext()).countryDao,
                        dataSource = RemoteDataSource(ServiceBuilder.buildService(RemoteService::class.java))
                    )
                )
            )[AuthenticationViewModel::class.java]
        } ?: throw Exception()


        println("Personal card init RUNNER:  ${viewModel.runner.value}")

        val binding: PersonalCardFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.personal_card_fragment, container, false
        )


        viewModel.runner.observe(viewLifecycleOwner) {
            binding.runner = it
        }
        val flag = viewModel.runner.value?.id_country?.let { handle(it) }
        println(flag)
        binding.root.icon.setImageResource(flag ?: 0)
        binding.lifecycleOwner = this

        return binding.root
    }
}
