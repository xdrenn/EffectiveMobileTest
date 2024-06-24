package com.example.effectivemobiletest.presentation.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.ActivityMainBinding
import com.example.effectivemobiletest.presentation.ui.search.SearchFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navView.setupWithNavController(navController)
    }

    fun showSearchDialog(bundle: Bundle) {
        SearchFragment().let { fragment ->
            fragment.arguments = bundle
            fragment.show(supportFragmentManager, SearchFragment.TAG)
        }
    }

    fun openSelectedCityFragment(bundle: Bundle) {
        navController.navigate(R.id.action_searchFragment_to_selectedCityFragment, bundle)
    }

    fun backToPreviousFragment() {
        navController.popBackStack()
    }
}