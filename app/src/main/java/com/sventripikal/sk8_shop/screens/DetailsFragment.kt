package com.sventripikal.sk8_shop.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sventripikal.sk8_shop.Priority
import com.sventripikal.sk8_shop.TAG
import com.sventripikal.sk8_shop.databinding.FragmentDetailsBinding
import com.sventripikal.sk8_shop.timber


private const val MESSAGE_CREATE = "[DetailsFragment] ON-CREATE"
private const val MESSAGE_START = "[DetailsFragment] ON-START"
private const val MESSAGE_RESUME = "[DetailsFragment] ON-RESUME"
private const val MESSAGE_PAUSE = "[DetailsFragment] ON-PAUSE"
private const val MESSAGE_STOP = "[DetailsFragment] ON-STOP"
private const val MESSAGE_DESTROY = "[DetailsFragment] ON-DESTROY"

class DetailsFragment : Fragment() {

    // sharedViewModel
    private val viewModel: ApplicationViewModel by activityViewModels()

    // view Binder
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // log
        timber(TAG, MESSAGE_CREATE, Priority.VERBOSE)

        // inflate views & layout
        binding = FragmentDetailsBinding.inflate(inflater)

        // set bindings
        setBindings()

        // set ui observers
        setUIObservers()

        // return layout root
        return binding.root
    }

    // assign bindings
    private fun setBindings() {
        // reference viewmodel
        binding.viewModel = viewModel

        // reference lifecycle owner
        binding.lifecycleOwner = this
    }

    // set ui listeners
    private fun setUIObservers() {

        // listener for Cancel button
        binding.cancelButton.setOnClickListener {

            // navigate back to Listings page
            val action = DetailsFragmentDirections.actionDetailsFragmentToListingsFragment()
            findNavController().navigate(action)
        }
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

