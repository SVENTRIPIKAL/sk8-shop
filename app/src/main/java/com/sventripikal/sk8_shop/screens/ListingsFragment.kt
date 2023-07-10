package com.sventripikal.sk8_shop.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sventripikal.sk8_shop.Priority
import com.sventripikal.sk8_shop.TAG
import com.sventripikal.sk8_shop.databinding.FragmentListingsBinding
import com.sventripikal.sk8_shop.timber


private const val MESSAGE_CREATE = "[ListingsFragment] ON-CREATE"
private const val MESSAGE_START = "[ListingsFragment] ON-START"
private const val MESSAGE_RESUME = "[ListingsFragment] ON-RESUME"
private const val MESSAGE_PAUSE = "[ListingsFragment] ON-PAUSE"
private const val MESSAGE_STOP = "[ListingsFragment] ON-STOP"
private const val MESSAGE_DESTROY = "[ListingsFragment] ON-DESTROY"

class ListingsFragment : Fragment() {

    // viewBinder
    private lateinit var binding: FragmentListingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // inflate layout & views
        binding = FragmentListingsBinding.inflate(inflater)

        // log
        timber(TAG, MESSAGE_CREATE, Priority.VERBOSE)

        // return root layout
        return binding.root
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