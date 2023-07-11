package com.sventripikal.sk8_shop

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.google.android.material.navigation.NavigationView
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

    // drawer layout
    private lateinit var drawerLayout: DrawerLayout

    // drawer nav items
    private lateinit var drawerItems: NavigationView

    // app toolbar
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    // action bar config
    private lateinit var appBarConfiguration: AppBarConfiguration

    // drawer toggle
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    // navController
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflate MainActivity views
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        // assign references
        setBindings()

        // setup navController destination listener
        setupActionBarDestinationListener()

        // log
        timber(TAG, MESSAGE_CREATE, Priority.VERBOSE)

        // set view to root layout
        setContentView(binding.root)
    }

    // assign main bindings
    private fun setBindings() {
        // drawer layout
        drawerLayout = binding.drawerLayout

        // drawer nav items
        drawerItems = binding.drawerNavView

        // drawer toggle
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, R.string.drawer_open, R.string.drawer_closed
        )

        // assign toolbar
        toolbar = binding.appToolBar

        // get navController from FragmentContainerView
        navController = NavHostFragment.findNavController(binding.navHostFragment.getFragment())

        // get appBarConfig - login & listing as TopLevelIds
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.loginFragment, R.id.listingsFragment), drawerLayout
        )

        // setup toolbar
        setSupportActionBar(toolbar)

        // setup action bar with navController, config, & drawer
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        // setup action bar controller with drawer menu items
        NavigationUI.setupWithNavController(drawerItems, navController)
    }

    // update action bar by destination
    private fun setupActionBarDestinationListener() {

        // add destination listeners
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                // FOR SCREENS:
                R.id.loginFragment,
                R.id.welcomeFragment,
                R.id.instructionsFragment ->  {
                    // hide & lock drawer
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    // hide [hamburger] icon
                    supportActionBar?.setDisplayHomeAsUpEnabled(FALSE)
                }
                R.id.listingsFragment -> {
                    // set drawer toggle listener
                    setDrawerToggleListener()
                    // unlock & enable drawer
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                    // unhide [hamburger] icon
                    supportActionBar?.setDisplayHomeAsUpEnabled(TRUE)
                }
            }
        }
    }

    // drawer onclick listener
    private fun setDrawerToggleListener() {
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
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