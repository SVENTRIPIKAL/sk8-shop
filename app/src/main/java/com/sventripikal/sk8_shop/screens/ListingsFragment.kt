package com.sventripikal.sk8_shop.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sventripikal.sk8_shop.Priority
import com.sventripikal.sk8_shop.R
import com.sventripikal.sk8_shop.TAG
import com.sventripikal.sk8_shop.databinding.FragmentListingsBinding
import com.sventripikal.sk8_shop.models.SkateBoardItem
import com.sventripikal.sk8_shop.timber


private const val MESSAGE_CREATE = "[ListingsFragment] ON-CREATE"
private const val MESSAGE_START = "[ListingsFragment] ON-START"
private const val MESSAGE_RESUME = "[ListingsFragment] ON-RESUME"
private const val MESSAGE_PAUSE = "[ListingsFragment] ON-PAUSE"
private const val MESSAGE_STOP = "[ListingsFragment] ON-STOP"
private const val MESSAGE_DESTROY = "[ListingsFragment] ON-DESTROY"

class ListingsFragment : Fragment() {

    // sharedViewModel
    private val viewModel: ApplicationViewModel by activityViewModels()

    // view Binder
    private lateinit var binding: FragmentListingsBinding

    // list of views LinearLayout
    private lateinit var itemsLinearLayout: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // log
        timber(TAG, MESSAGE_CREATE, Priority.VERBOSE)

        // inflate layout & views
        binding = FragmentListingsBinding.inflate(inflater)

        // assign bindings
        setBindings()

        // create observers
        setUIObservers()

        // return root layout
        return binding.root
    }

    private fun setBindings() {
        // viewModel
        binding.viewModel = viewModel

        // lifecycle owner
        binding.lifecycleOwner = this

        // linear layout view list
        itemsLinearLayout = binding.itemListView

        // bind viewModel list to Layout
        bindItemsToLayout()
    }

    // add Item Names to Layout as ButtonViews
    @SuppressLint("ResourceAsColor")
    private fun bindItemsToLayout() {

        // temp value
        var index = 0

        // viewmodel item list
        viewModel.itemsList.value!!.forEach {

            //update index
            index++

            // create ButtonView
            val button = Button(requireContext())

            // buttonView block
            button.apply {

                // assign text to view
                text = setText(index, it)

                // assign view style
                setTextAppearance(R.style.itemListViewStyle)

                // add view to linearLayout
                itemsLinearLayout.addView(this)
            }

            // log
            timber(TAG, "${it.itemName} added to $itemsLinearLayout", Priority.DEBUG)
        }
    }

    // sets text to Button
    private fun setText(index: Int, item: SkateBoardItem): String {
        return "${index}.)  ${item.itemName}"
    }

    // set ui listeners
    private fun setUIObservers() {

        // set listener for FAB
        binding.floatingActionButton.setOnClickListener {

            // get navigation action & navigate to destination
            val action = ListingsFragmentDirections.actionListingsFragmentToDetailsFragment()
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