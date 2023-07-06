package com.sventripikal.sk8_shop

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.sventripikal.sk8_shop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // viewBinder
    private lateinit var binding: ActivityMainBinding

    // action bar config
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflate MainActivity views
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        // setup action bar
        setSupportActionBar(binding.toolBar)

        // get navController from FragmentContainerView
        val navHostFragmentController = NavHostFragment.findNavController(binding.navHostFragment.getFragment())

        // provide navGraph to appBarConfig
        appBarConfiguration = AppBarConfiguration(navHostFragmentController.graph)

        // setup action bar with navController & config
        setupActionBarWithNavController(navHostFragmentController, appBarConfiguration)

        // set view to root layout
        setContentView(binding.root)
    }


    // handle up navigation
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(binding.navHostFragment.id)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}

/**
 * MATERIAL DESIGN 3 minSDK = 32
 */