package com.example.effectivemobiletest.presentation.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.FragmentSearchBinding
import com.example.effectivemobiletest.presentation.ui.MainActivity
import com.example.effectivemobiletest.presentation.ui.selected.SelectedCityFragment
import com.example.effectivemobiletest.utils.CyrillicFilter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchFragment : BottomSheetDialogFragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    private var bundle = Bundle()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (view.parent as View).layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED

        viewModel.setCityFrom(arguments?.getString("cityFrom") ?: "")
        Log.d("bundle", arguments?.getString("cityFrom") ?: "error")

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
        binding.searchEdTo.filters = arrayOf(CyrillicFilter)
        binding.searchEdFrom.filters = arrayOf(CyrillicFilter)

        binding.searchEdTo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setCityTo(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}

        })

        binding.searchEdTo.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                openSelectedCountryScreen()
                true
            } else {
                false
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cityTo.collect { value ->
                binding.searchEdTo.setText(value)
                binding.searchEdTo.setSelection(value.length)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cityFrom.collect { value ->
                binding.searchEdFrom.text = value
            }
        }
    }

    private fun openSelectedCountryScreen() {
        val bundle = Bundle().apply {
            putString("selectedCityFrom", viewModel.cityFrom.value)
            putString("selectedCityTo", viewModel.cityTo.value)
        }

        (requireActivity() as MainActivity).openSelectedCityFragment(bundle = bundle)

    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}

