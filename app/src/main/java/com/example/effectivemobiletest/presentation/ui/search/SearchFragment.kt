package com.example.effectivemobiletest.presentation.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.FragmentSearchBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchFragment : BottomSheetDialogFragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchSetDirectionTo()
        setSearchOptionsClick()
        initSearch()
    }

    private fun searchSetDirectionTo() {
        binding.Istanbul.setOnClickListener {
            binding.searchEdTo.setText(binding.popularCity1.text)
        }

        binding.Sochi.setOnClickListener {
            binding.searchEdTo.setText(binding.popularCity2.text)
        }

        binding.Phuket.setOnClickListener {
            binding.searchEdTo.setText(binding.popularCity1.text)
        }
    }

    private fun setSearchOptionsClick() {
        binding.anywhereIcon.setOnClickListener {
            binding.searchEdTo.setText(R.string.anywhere)
        }
        binding.deleteSearch.setOnClickListener {
            binding.searchEdTo.text.clear()
        }
    }

    private fun initSearch() {
        binding.searchEdTo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setCityTo(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

        })

        binding.searchEdFrom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setCityFrom(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cityTo.collect { value ->
                binding.searchEdTo.setText(value)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cityFrom.collect { value ->
                binding.searchEdFrom.setText(value)

            }
        }
    }
}