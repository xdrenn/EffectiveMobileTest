package com.example.effectivemobiletest.presentation.ui.home

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.data.model.OffersDTO
import com.example.effectivemobiletest.databinding.FragmentHomeBinding
import com.example.effectivemobiletest.presentation.ui.MainActivity
import com.example.effectivemobiletest.presentation.ui.adapters.offersAdapter
import com.example.effectivemobiletest.presentation.ui.search.SearchFragment
import com.example.effectivemobiletest.presentation.ui.selected.SelectedCityFragment
import com.example.effectivemobiletest.utils.Constants
import com.example.effectivemobiletest.utils.CyrillicFilter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.regex.Pattern

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
        initSearchCityFrom()
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

    private fun initSearchCityFrom() {

        binding.editTextFrom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val newInput = s.toString()
                if (newInput != viewModel.cityFrom.value) {
                    viewModel.setCityFrom(newInput)
                }
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cityFrom.collect { value ->
                binding.editTextFrom.setText(CyrillicFilter.isCyrillic(value))
                binding.editTextFrom.setSelection(value.length)
            }
        }
        binding.tvTo.setOnClickListener {
            showSearchInfo()
        }
    }

    private fun showSearchInfo() {

        val bundle = Bundle().apply {
            putString("cityFrom",  viewModel.cityFrom.value)
        }
            (requireActivity() as MainActivity).showSearchDialog(bundle = bundle)
    }

}