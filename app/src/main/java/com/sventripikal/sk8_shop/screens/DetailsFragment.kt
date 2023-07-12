package com.sventripikal.sk8_shop.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sventripikal.sk8_shop.DARKSTAR
import com.sventripikal.sk8_shop.DECKS
import com.sventripikal.sk8_shop.Priority
import com.sventripikal.sk8_shop.TAG
import com.sventripikal.sk8_shop.databinding.FragmentDetailsBinding
import com.sventripikal.sk8_shop.models.SkateBoardItem
import com.sventripikal.sk8_shop.timber


private const val MESSAGE_INIT = "[DetailsFragment] INIT"
private const val MESSAGE_CREATE = "[DetailsFragment] ON-CREATE"
private const val MESSAGE_START = "[DetailsFragment] ON-START"
private const val MESSAGE_RESUME = "[DetailsFragment] ON-RESUME"
private const val MESSAGE_PAUSE = "[DetailsFragment] ON-PAUSE"
private const val MESSAGE_STOP = "[DetailsFragment] ON-STOP"
private const val MESSAGE_DESTROY = "[DetailsFragment] ON-DESTROY"

private const val ZERO = 0
private const val ONE_HUNDRED = 100
private const val ONE_THOUSAND = 1000
private const val NINE_THOUSAND = 9000

class DetailsFragment : Fragment() {

    // sharedViewModel
    private val viewModel: ApplicationViewModel by activityViewModels()

    // view Binder
    private lateinit var binding: FragmentDetailsBinding

    // default skateboard
    private val defaultSkateBoard: SkateBoardItem


    // create default values for data class on startup
    init {

        // log
        timber(TAG, MESSAGE_INIT, Priority.DEBUG)

        val name: String = kotlin.random.Random.nextInt(ONE_THOUSAND, NINE_THOUSAND).toString()
        val brand: String = DARKSTAR
        val category: String = DECKS
        val quantity: String = kotlin.random.Random.nextInt(ZERO, ONE_HUNDRED).toString()

        defaultSkateBoard = SkateBoardItem(name, brand, category, quantity)
    }

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

        // assign data class default item
        // observers of EditText fields in .xml create copy when casted
        binding.skateboardItem = defaultSkateBoard
    }

    // set ui listeners
    private fun setUIObservers() {

        // listener for Cancel button
        binding.cancelButton.setOnClickListener {

            // navigate back to Listings page
            val action = DetailsFragmentDirections.actionDetailsFragmentToListingsFragment()
            findNavController().navigate(action)
        }

        // listener for SaveItem button
        binding.saveButton.setOnClickListener {

            // create new item & add to list
            createNewItem()

            // navigate back to listings page
            val action = DetailsFragmentDirections.actionDetailsFragmentToListingsFragment()
            findNavController().navigate(action)
        }
    }

    // create new item
    private fun createNewItem() {

        // cast Data class object with EditTextValues & add to list
        viewModel.addItemToList( binding.skateboardItem as SkateBoardItem )
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

