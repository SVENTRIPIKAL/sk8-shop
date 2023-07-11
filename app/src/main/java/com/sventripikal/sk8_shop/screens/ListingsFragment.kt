package com.sventripikal.sk8_shop.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sventripikal.sk8_shop.Priority
import com.sventripikal.sk8_shop.R
import com.sventripikal.sk8_shop.TAG
import com.sventripikal.sk8_shop.databinding.FragmentListingsBinding
import com.sventripikal.sk8_shop.timber


private const val MESSAGE_CREATE = "[ListingsFragment] ON-CREATE"
private const val MESSAGE_START = "[ListingsFragment] ON-START"
private const val MESSAGE_RESUME = "[ListingsFragment] ON-RESUME"
private const val MESSAGE_PAUSE = "[ListingsFragment] ON-PAUSE"
private const val MESSAGE_STOP = "[ListingsFragment] ON-STOP"
private const val MESSAGE_DESTROY = "[ListingsFragment] ON-DESTROY"

/**
 *  TO-DO:
 *  +   ADD OVERFLOW MENU
 *  +   ADD DRAWER LAYOUT
 */

class ListingsFragment : Fragment() {

    // sharedViewModel
    private val viewModel: ApplicationViewModel by activityViewModels()

    // view Binder
    private lateinit var binding: FragmentListingsBinding

//    // drawer layout
//    private lateinit var drawerLayout: DrawerLayout
//
//    // action bar config
//    private lateinit var appBarConfiguration: AppBarConfiguration
//
//    // drawer toggle
//    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // inflate layout & views
        binding = FragmentListingsBinding.inflate(inflater)

        // assign bindings
        setBindings()

        // create observers
        setUIObservers()

        // log
        timber(TAG, MESSAGE_CREATE, Priority.VERBOSE)

        // return root layout
        return binding.root
    }

    private fun setBindings() {
        // viewModel
        binding.viewModel = viewModel

        // lifecycle owner
        binding.lifecycleOwner = this

//        // drawer layout
//        drawerLayout = binding.listingsDrawerLayout
//
//        // drawer toggle
//        actionBarDrawerToggle = ActionBarDrawerToggle(
//            requireActivity(), drawerLayout, R.string.drawer_open, R.string.drawer_closed
//        )
//
//        // reset appbar config to Set this as New Home Layout with DrawerLayout
//        appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.loginFragment, R.id.listingsFragment),
//            drawerLayout
//        )
//
//        // get compat Activity from main Activity
//        val appCompatActivity = (requireActivity() as AppCompatActivity)
//
//        // setup new Action bar
//        NavigationUI.setupActionBarWithNavController(
//            appCompatActivity, this.findNavController(), appBarConfiguration
//        )
    }

    // set ui listeners
    private fun setUIObservers() {

//        // nav drawer block
//        apply {
//            // drawer onclick listener
//            drawerLayout.addDrawerListener(actionBarDrawerToggle)
//            actionBarDrawerToggle.syncState()
//        }
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