package com.sventripikal.sk8_shop

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.sventripikal.sk8_shop.databinding.ActivityMainBinding


private const val MESSAGE_CREATE = "[MainActivity] ON-CREATE"
private const val MESSAGE_START = "[MainActivity] ON-START"
private const val MESSAGE_RESUME = "[MainActivity] ON-RESUME"
private const val MESSAGE_PAUSE = "[MainActivity] ON-PAUSE"
private const val MESSAGE_STOP = "[MainActivity] ON-STOP"
private const val MESSAGE_DESTROY = "[MainActivity] ON-DESTROY"

class MainActivity : AppCompatActivity() {

    // viewBinder
    private lateinit var binding: ActivityMainBinding

    // action bar config
    private lateinit var appBarConfiguration: AppBarConfiguration

    // navController
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflate MainActivity views
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        // setup action bar
        setSupportActionBar(binding.toolBar)

        // get navController from FragmentContainerView
        navController = NavHostFragment.findNavController(binding.navHostFragment.getFragment())

        // provide navGraph to appBarConfig
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // setup action bar with navController & config
        setupActionBarWithNavController(navController, appBarConfiguration)

        // setup navController destination listener
        setupActionBarDestinationListener()

        // log
        timber(TAG, MESSAGE_CREATE, Priority.VERBOSE)

        // set view to root layout
        setContentView(binding.root)
    }

    // update action bar icons by destination
    private fun setupActionBarDestinationListener() {

        // add destination listeners
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                // WELCOME:          hide back arrow | add overflow menu | add drawer
                R.id.welcomeFragment -> binding.toolBar.navigationIcon = null
            }
        }
    }


    // handle up navigation
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(binding.navHostFragment.id)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }


    /**
     * Lifecycle methods
     */
    override fun onStart() {
        super.onStart()
        timber(TAG, MESSAGE_START, Priority.INFO)
    }

    override fun onResume() {
        super.onResume()
        timber(TAG, MESSAGE_RESUME, Priority.DEBUG)
    }

    override fun onPause() {
        super.onPause()
        timber(TAG, MESSAGE_PAUSE, Priority.INFO)
    }

    override fun onStop() {
        super.onStop()
        timber(TAG, MESSAGE_STOP, Priority.VERBOSE)
    }

    override fun onDestroy() {
        super.onDestroy()
        timber(TAG, MESSAGE_DESTROY, Priority.ERROR)
    }
}

/**
 * MATERIAL DESIGN 3 minSDK = 32
 */