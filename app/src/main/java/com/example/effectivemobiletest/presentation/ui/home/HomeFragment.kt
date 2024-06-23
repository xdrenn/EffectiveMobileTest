package com.example.effectivemobiletest.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobiletest.data.model.OffersDTO
import com.example.effectivemobiletest.databinding.FragmentHomeBinding
import com.example.effectivemobiletest.presentation.ui.adapters.offersAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private var adapter: ListDelegationAdapter<List<OffersDTO.Offer>>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    private fun initAdapter() {

        adapter = ListDelegationAdapter(offersAdapter())

        viewLifecycleOwner.lifecycleScope.launch {
                viewModel.offers.collect { list ->
                    adapter?.items = list
                    binding.offersRc.adapter = adapter
                    binding.offersRc.layoutManager = LinearLayoutManager(
                        context, LinearLayoutManager.HORIZONTAL, false
                    )
            }
        }
    }
}