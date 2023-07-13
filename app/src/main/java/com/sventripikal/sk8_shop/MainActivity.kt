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
import com.sventripikal.sk8_shop.screens.ListingsFragmentDirections


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

        // log
        timber(TAG, MESSAGE_CREATE, Priority.VERBOSE)

        // inflate MainActivity views
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        // assign references
        setBindings()

        // setup navController destination listener
        setupActionBarDestinationListener()

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
                    // set drawer listeners
                    setDrawerListeners()
                    // unlock & enable drawer
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                    // unhide [hamburger] icon
                    supportActionBar?.setDisplayHomeAsUpEnabled(TRUE)
                }
            }
        }
    }

    // drawer listeners
    private fun setDrawerListeners() {
        // drawer open/close toggle listener
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // drawer menu items onclick listener
        drawerItems.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.logOut_drawer -> {     // return to login screen
                    val action = ListingsFragmentDirections.actionListingsFragmentToLoginFragment()
                    navController.navigate(action)
                }
            }
            true // return true to indicate successful click
        }
    }

    // handle up navigation
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(binding.navHostFragment.id)
        return navController.navigateUp(appBarConfiguration)
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