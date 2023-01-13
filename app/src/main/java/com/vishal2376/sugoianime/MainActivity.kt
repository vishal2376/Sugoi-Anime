package com.vishal2376.sugoianime

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        bottomNavView.setupWithNavController(navController)
        bottomNavView.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
             when (menuItem.itemId) {
                R.id.itemHome -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.itemExplore -> {
                    navController.navigate(R.id.exploreFragment)
                    true
                }
                R.id.itemMovies -> {
                    navController.navigate(R.id.moviesFragment)
                    true
                }
                R.id.itemLibrary -> {
                    navController.navigate(R.id.libraryFragment)
                    true
                }
                else -> false
            }
        }

    }

}