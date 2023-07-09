package com.sventripikal.sk8_shop.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sventripikal.sk8_shop.Priority
import com.sventripikal.sk8_shop.R
import com.sventripikal.sk8_shop.TAG
import com.sventripikal.sk8_shop.databinding.FragmentWelcomeBinding
import com.sventripikal.sk8_shop.timber


private const val MESSAGE_CREATE = "[WelcomeFragment] ON-CREATE"
private const val MESSAGE_START = "[WelcomeFragment] ON-START"
private const val MESSAGE_RESUME = "[WelcomeFragment] ON-RESUME"
private const val MESSAGE_PAUSE = "[WelcomeFragment] ON-PAUSE"
private const val MESSAGE_STOP = "[WelcomeFragment] ON-STOP"
private const val MESSAGE_DESTROY = "[WelcomeFragment] ON-DESTROY"

class WelcomeFragment : Fragment() {

    // viewBinder
    private lateinit var binding: FragmentWelcomeBinding

    // image Gif
    private lateinit var imageView: ImageView

    // onCreate
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // inflate WelcomeFragment views
        binding = FragmentWelcomeBinding.inflate(inflater)

        // add gif into view with Glide
        imageView = binding.alienGif
        Glide.with(requireActivity()).load(
            R.drawable.cool_alien_on_skateboard_transparent
        ).into(imageView)

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
/**
6. Create a new Welcome screen destination that includes:   [-]

 * A new layout [-]
 * At least 2 textviews [-]
 * A navigation button with actions to navigate to the instructions screen [-]

7. Create a new Instruction destination that includes:  [-]

 * A new layout [-]
 * At least 2 textviews [-]
 * A navigation button with actions to navigate to the shoe list screen [-]
 */