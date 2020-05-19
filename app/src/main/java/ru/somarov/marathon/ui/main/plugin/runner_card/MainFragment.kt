package ru.somarov.marathon.ui.main.plugin.runner_card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.somarov.marathon.R
import ru.somarov.marathon.databinding.MainFragmentBinding
import ru.somarov.marathon.ui.main.plugin.runner_card.model.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() =
            MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val binding: MainFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false)
        binding.userViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
