package com.sventripikal.sk8_shop.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sventripikal.sk8_shop.Priority
import com.sventripikal.sk8_shop.R
import com.sventripikal.sk8_shop.TAG
import com.sventripikal.sk8_shop.databinding.FragmentInstructionsBinding
import com.sventripikal.sk8_shop.timber


private const val MESSAGE_CREATE = "[InstructionsFragment] ON-CREATE"
private const val MESSAGE_START = "[InstructionsFragment] ON-START"
private const val MESSAGE_RESUME = "[InstructionsFragment] ON-RESUME"
private const val MESSAGE_PAUSE = "[InstructionsFragment] ON-PAUSE"
private const val MESSAGE_STOP = "[InstructionsFragment] ON-STOP"
private const val MESSAGE_DESTROY = "[InstructionsFragment] ON-DESTROY"

class InstructionsFragment : Fragment() {

    // viewBinder
    private lateinit var binding: FragmentInstructionsBinding

    // help animation Gif
    private lateinit var imageView: ImageView

    // sharedViewModel
    private val viewModel: ApplicationViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // log
        timber(TAG, MESSAGE_CREATE, Priority.VERBOSE)

        // inflate InstructionsFragment views
        binding = FragmentInstructionsBinding.inflate(inflater)

        // add gif into view with Glide
        imageView = binding.helpAnimationGif
        Glide.with(requireActivity())
            .load(R.drawable.help_animation)
            .into(imageView)

        // set bindings
        setLifeCycleBindings()

        // set UI observers
        setUIObservers()

        // return root layout
        return binding.root
    }

    // lifecycle bindings
    private fun setLifeCycleBindings() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    // UI observers
    private fun setUIObservers() {

        // binding block
        binding.apply {

            // set FAB back navigation - WelcomeFragment
            cardFloatingActionButton.setOnClickListener {
                val action = InstructionsFragmentDirections.actionInstructionsFragmentToWelcomeFragment()
                findNavController().navigate(action)
            }

            // set FAB forward navigation - ListingsFragment
            letsGoFloatingActionButton.setOnClickListener {
                val action = InstructionsFragmentDirections.actionInstructionsFragmentToListingsFragment()
                findNavController().navigate(action)
            }
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