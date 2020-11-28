package com.example.headwaytest.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.headwaytest.R

class MainActivity : AppCompatActivity() {
    private lateinit var navHost: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHost = Navigation.findNavController(this, R.id.nav_host_fragment_main)
    }

    override fun onBackPressed() {
        if (navHost.currentDestination?.id == R.id.repoListFragment) {
            finishAffinity()
        } else {
            navHost.navigateUp()
        }
    }
}