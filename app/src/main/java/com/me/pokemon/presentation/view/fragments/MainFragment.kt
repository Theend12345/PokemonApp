package com.me.pokemon.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.me.pokemon.databinding.FragmentMainBinding
import com.me.pokemon.presentation.util.makeToast
import com.me.pokemon.presentation.util.setVisible
import com.me.pokemon.presentation.view.rv.ItemPagingAdapter
import com.me.pokemon.presentation.view.state.ViewState
import com.me.pokemon.presentation.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val adapter = ItemPagingAdapter()

    private val binding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            recycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recycler.adapter = adapter

            adapter.addLoadStateListener { state ->
                val refreshState = state.refresh
                if (refreshState is LoadState.Error) {
                    viewModel.loadError(refreshState.error)
                }
            }

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
                                adapter.submitData(it.data)
                                progress.setVisible(false)
                            }
                        }
                    }
                }
            }
        }
    }
}