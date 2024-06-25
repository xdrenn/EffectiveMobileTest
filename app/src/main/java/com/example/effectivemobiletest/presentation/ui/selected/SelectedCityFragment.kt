package com.example.effectivemobiletest.presentation.ui.selected

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.effectivemobiletest.databinding.FragmentSelectedCityBinding
import android.text.format.DateFormat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobiletest.data.model.TicketsOffersDTO
import com.example.effectivemobiletest.presentation.ui.MainActivity
import com.example.effectivemobiletest.presentation.ui.adapters.ticketsOffersAdapter
import com.google.android.material.datepicker.MaterialDatePicker
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class SelectedCityFragment : Fragment() {


    private var _binding: FragmentSelectedCityBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SelectedCityViewModel by viewModels()

    private var adapter: ListDelegationAdapter<List<TicketsOffersDTO.TicketsOffer>>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectedCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.allTicketsButton.setOnClickListener {
          openTicketsFragment()
        }

        initCitiesFields()
        initFilterChips()
        initAdapter()
    }

    private fun initCitiesFields() {
        val city = viewModel.cityFrom.value
        viewModel.setCityFrom(arguments?.getString("selectedCityFrom") ?: "")
        viewModel.setCityTo(arguments?.getString("selectedCityTo") ?: "")

        binding.changeCities.setOnClickListener {
            viewModel.setCityFrom(viewModel.cityTo.value)
            viewModel.setCityTo(city)
        }

        binding.backBtn.setOnClickListener {
            (requireActivity() as MainActivity).backToPreviousFragment()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cityTo.collect { value ->
                binding.cityTo.text = value
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cityFrom.collect { value ->
                binding.cityFrom.text = value
            }
        }
    }

    private fun initFilterChips() {
        binding.backChip.setOnClickListener {
            showCalendar(childFragmentManager){}
        }

        binding.dateChip.setOnClickListener {
            showCalendar(
                childFragmentManager,
            ) { dateInMillis ->
                viewModel.setFlightDate(dateInMillis)
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.flightDate.collect { dateInMillis ->
                    val dateFormatter = SimpleDateFormat("d LLL, E", Locale("ru"))
                    val dateFormat = dateFormatter.format(dateInMillis)
                    binding.dateChip.text = dateFormat
                }
            }
        }
    }

    private fun showCalendar(
        fragmentManager: FragmentManager,
        onDateTimeSet: (date: Long) -> Unit
    ) {
        MaterialDatePicker
            .Builder
            .datePicker()
            .setTitleText("Select date of flight")
            .build()
            .show(fragmentManager, "DATE_PICKER")
    }

    private fun initAdapter() {

        adapter = ListDelegationAdapter(ticketsOffersAdapter())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.ticketsOffers.collect { list ->
                adapter?.items = list
                binding.ticketsOffersRv.adapter = adapter
                binding.ticketsOffersRv.layoutManager = LinearLayoutManager(
                    context, LinearLayoutManager.VERTICAL, false
                )
            }
        }
    }

    private fun openTicketsFragment() {
        val bundle = Bundle().apply {
            val flightRoute = "${viewModel.cityFrom.value}-${viewModel.cityTo.value}"
            putString("flight", flightRoute)

            val dateFormatter = SimpleDateFormat("d LLL", Locale("ru"))
            val flightDate = dateFormatter.format(viewModel.flightDate.value)
            putString("flightDate", flightDate.toString())
        }

        (requireActivity() as MainActivity).openTicketsFragment(bundle = bundle)
    }
}