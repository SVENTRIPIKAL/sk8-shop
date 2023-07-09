package com.sventripikal.sk8_shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import com.sventripikal.sk8_shop.databinding.FragmentWelcomeBinding
import com.sventripikal.sk8_shop.screens.ApplicationViewModel

private const val MESSAGE_CREATE = "[InstructionsFragment] ON-CREATE"
private const val MESSAGE_START = "[InstructionsFragment] ON-START"
private const val MESSAGE_RESUME = "[InstructionsFragment] ON-RESUME"
private const val MESSAGE_PAUSE = "[InstructionsFragment] ON-PAUSE"
private const val MESSAGE_STOP = "[InstructionsFragment] ON-STOP"
private const val MESSAGE_DESTROY = "[InstructionsFragment] ON-DESTROY"


class InstructionsFragment : Fragment() {

    // viewBinder
    private lateinit var binding: InstructionsFragment

    // image Gif
    private lateinit var imageView: ImageView

    // sharedViewModel
    private val viewModel: ApplicationViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_instructions, container, false)
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