package com.example.effectivemobiletest.presentation.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.effectivemobiletest.EmptyFragment
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.ActivityMainBinding
import com.example.effectivemobiletest.presentation.ui.home.HomeFragment
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
        navController = navHostFragment.navController

        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> replaceFragment(HomeFragment())
                R.id.navigation_hotels -> replaceFragment(EmptyFragment())
                R.id.navigation_destination -> replaceFragment(EmptyFragment())
                R.id.navigation_subscriptions -> replaceFragment(EmptyFragment())
                R.id.navigation_profile -> replaceFragment(EmptyFragment())
            }
            true
        }
    }

    fun showSearchDialog(bundle: Bundle) {
        navController.navigate(R.id.action_navigation_home_to_searchFragment, bundle)
    }

    fun openSelectedCityFragment(bundle: Bundle) {
        navController.navigate(R.id.action_searchFragment_to_selectedCityFragment, bundle)
    }

    fun openTicketsFragment(bundle: Bundle) {
        navController.navigate(R.id.action_selectedCityFragment_to_ticketsFragment, bundle)
    }

    fun backToPreviousFragment() {
        navController.navigate(R.id.action_selectedCityFragment_to_navigation_home)
    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()
    }
}