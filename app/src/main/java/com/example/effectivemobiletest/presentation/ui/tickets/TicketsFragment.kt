package com.example.effectivemobiletest.presentation.ui.tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.data.model.TicketsDTO
import com.example.effectivemobiletest.data.model.TicketsOffersDTO
import com.example.effectivemobiletest.databinding.FragmentTicketsBinding
import com.example.effectivemobiletest.presentation.ui.adapters.ticketsAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TicketsViewModel by viewModels()

    private var adapter: ListDelegationAdapter<List<TicketsDTO.Ticket>>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setFlightRoute(arguments?.getString("flight") ?: "")
        viewModel.setFlightDate(arguments?.getString("flightDate") ?: "")

        binding.ticketsBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_ticketsFragment_to_selectedCityFragment)
        }

        initAdapter()
        initFlightInfo()
    }

    private fun initFlightInfo() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flightRoute.collect { value ->
                binding.flightRoute.text = value
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flightDate.collect { value ->
                binding.flightDate.text = value
            }
        }
    }

    private fun initAdapter() {
        adapter = ListDelegationAdapter(ticketsAdapter())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tickets.collect { list ->
                adapter?.items = list
                binding.ticketsRv.adapter = adapter
                binding.ticketsRv.layoutManager = LinearLayoutManager(
                    context, LinearLayoutManager.VERTICAL, false
                )
            }
        }
    }
}