package com.me.pokemon.presentation.view.fragments

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.me.domain.model.ItemModel
import com.me.pokemon.R
import com.me.pokemon.databinding.FragmentDescriptionBinding
import com.me.pokemon.presentation.util.makeToast
import com.me.pokemon.presentation.util.setVisible
import com.me.pokemon.presentation.view.state.ViewState
import com.me.pokemon.presentation.view.viewmodel.DescriptionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

const val ITEM_NAME_KEY = "name-key"

@AndroidEntryPoint
class DescriptionFragment : Fragment() {

    private val binding: FragmentDescriptionBinding by lazy {
        FragmentDescriptionBinding.inflate(layoutInflater)
    }

    private val viewModel: DescriptionViewModel by lazy {
        ViewModelProvider(this)[DescriptionViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.loadData(arguments?.getString(ITEM_NAME_KEY, "") ?: "")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.data.collect {
                        when (it) {
                            is ViewState.Error -> {
                                progress.setVisible(false)
                                it.e.message?.makeToast(requireContext())
                            }

                            ViewState.Loading -> progress.setVisible(true)

                            is ViewState.Success -> {
                                progress.setVisible(false)
                                bind(it.data)
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bind(itemModel: ItemModel) {
        with(binding) {
            name.text = itemModel.name
            cost.text = getString(R.string.cost) + itemModel.cost.toString()
            power.text = getString(R.string.power) + itemModel.flingPower
            Glide.with(requireContext()).load(itemModel.sprites).into(icon)
        }
    }
}