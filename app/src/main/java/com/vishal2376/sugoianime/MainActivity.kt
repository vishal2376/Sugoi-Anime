package com.vishal2376.sugoianime

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.vishal2376.sugoianime.fragments.AnimeDetailFragment
import com.vishal2376.sugoianime.fragments.nav.ExploreFragment
import com.vishal2376.sugoianime.fragments.nav.HomeFragment
import com.vishal2376.sugoianime.fragments.nav.LibraryFragment
import com.vishal2376.sugoianime.fragments.nav.MovieFragment
import com.vishal2376.sugoianime.viewmodels.AnimeViewModalFactory
import com.vishal2376.sugoianime.viewmodels.AnimeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //nav controller setup
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        //bottom navigation setup
        val bottomNavView = findViewById<ChipNavigationBar>(R.id.bottomNavBar)
        bottomNavView.setItemSelected(R.id.itemHome)
        bottomNavView.setOnItemSelectedListener {
            when (it) {
                R.id.itemHome -> loadFragment(HomeFragment())
                R.id.itemExplore -> loadFragment(ExploreFragment())
                R.id.itemMovies -> loadFragment(MovieFragment())
                R.id.itemLibrary -> loadFragment(LibraryFragment())
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.commit()
    }
}