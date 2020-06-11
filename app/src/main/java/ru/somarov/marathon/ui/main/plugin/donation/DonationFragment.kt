package ru.somarov.marathon.ui.main.plugin.donation

import androidx.lifecycle.ViewModelProviders
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.donation_fragment.*
import kotlinx.android.synthetic.main.registration_fragment.*
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.core.db.entity.Gender
import ru.somarov.marathon.backend.main.core.db.entity.Runner
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.plugin.login.LoginRepository
import ru.somarov.marathon.databinding.BmiFragmentBinding
import ru.somarov.marathon.databinding.DonationFragmentBinding

import ru.somarov.marathon.databinding.LoginFragmentBinding
import ru.somarov.marathon.ui.main.core.viewmodel.AuthenticationViewModel
import ru.somarov.marathon.ui.main.core.viewmodel.AuthenticationViewModelFactory
import ru.somarov.marathon.ui.main.plugin.registration.GendersSpinnerAdapter
import ru.somarov.marathon.ui.main.plugin.registration.RegistrationViewModel
import ru.somarov.marathon.ui.main.plugin.runner_card.CardViewModel
import java.lang.Exception

class DonationFragment : Fragment() {

    companion object {
        fun newInstance() =
            DonationFragment()
    }

    private lateinit var viewModel: DonationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this)[DonationViewModel::class.java]

        val binding: DonationFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.donation_fragment, container, false)

        binding.runners.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.runner = MutableLiveData()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.runner.value = runners.selectedItem as Runner
            }

        }

        viewModel.runners.observe(viewLifecycleOwner, Observer {
            binding.runners.adapter = RunnersSpinnerAdapter(requireContext(),
                android.R.layout.simple_spinner_item, it)
        })

        binding.model = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
