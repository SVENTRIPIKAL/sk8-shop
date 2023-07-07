package com.sventripikal.sk8_shop.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.sventripikal.sk8_shop.Priority
import com.sventripikal.sk8_shop.TAG
import com.sventripikal.sk8_shop.TRUE
import com.sventripikal.sk8_shop.databinding.FragmentLoginBinding
import com.sventripikal.sk8_shop.timber


private const val MESSAGE_CREATE = "[LoginFragment] ON-CREATE"
private const val MESSAGE_START = "[LoginFragment] ON-START"
private const val MESSAGE_RESUME = "[LoginFragment] ON-RESUME"
private const val MESSAGE_PAUSE = "[LoginFragment] ON-PAUSE"
private const val MESSAGE_STOP = "[LoginFragment] ON-STOP"
private const val MESSAGE_DESTROY = "[LoginFragment] ON-DESTROY"

private const val HELPER_TEXT = "*COMPLETE TEXT FIELD*"

class LoginFragment : Fragment() {

    // viewBinder
    private lateinit var binding: FragmentLoginBinding

    // viewModel
    private lateinit var viewModel: ApplicationViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // inflate LoginFragment views
        binding = FragmentLoginBinding.inflate(inflater)

        // reference ApplicationViewModel
        viewModel = ViewModelProvider(this)[ApplicationViewModel::class.java]

        // create lifecyle bindings
        setLifeCycleBindings()

        // set UI observers
        setObserverUI()

        // log
        timber(TAG, MESSAGE_CREATE, Priority.VERBOSE)

        // return root layout
        return binding.root
    }


    // bind viewmodel to this fragment
    private fun setLifeCycleBindings() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setObserverUI() {
        // Fragment bindings block
        binding.apply {

            // observer for Email editText view
            outlinedLayoutEmail.editText?.doOnTextChanged { emailTextInput, _, _, _ ->

                // update viewmodel emailText
                viewModel!!.updateUserEmail(emailTextInput.toString())

                // update helper text if input not null or blank
                if (emailTextInput.isNullOrBlank() != TRUE) {
                    removeEmailHelperText()
                }

                // log
                timber(TAG, "EMAIL: ${viewModel!!.userEmail.value}", Priority.DEBUG)
            }

            // observer for Password editText view
            outlinedLayoutPassword.editText?.doOnTextChanged { passwordTextInput, _, _, _ ->
                // update viewmodel passwordText
                viewModel!!.updateUserPassword(passwordTextInput.toString())

                // check if input not null or blank
                if (passwordTextInput.isNullOrBlank() != TRUE) {
                    // make end icon visible
                    outlinedLayoutPassword.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
                    // remove helper text
                    removePasswordHelperText()
                } else {// remove end icon visibility
                    outlinedLayoutPassword.endIconMode = TextInputLayout.END_ICON_NONE
                }

                // log
                timber(TAG, "PASSWORD: ${viewModel!!.userPassword.value}", Priority.DEBUG)
            }

            // set button navigation listener
            newAccountButton.setOnClickListener {
                viewModel.apply {
                    // check if email & password are filled
                    if (this!!.editFieldsComplete()) {
                        //navigate to next fragment
                        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
                        binding.root.findNavController().navigate(action)
                    } else {
                        // set helper text if not complete
                        setHelperText()
                        // log
                        timber(TAG, HELPER_TEXT, Priority.ERROR)
                    }
                }
            }

            // set button navigation listener
            existingAccountButton.setOnClickListener {
                viewModel.apply {
                    // check if email & password are filled
                    if (this!!.editFieldsComplete()) {
                        //navigate to next fragment
                        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
                        binding.root.findNavController().navigate(action)
                    } else {
                        // set helper text if not complete
                        setHelperText()
                        // log
                        timber(TAG, HELPER_TEXT, Priority.ERROR)
                    }
                }
            }
        }
    }


    /**
     * helper text functions to add / remove
     */
    //#1
    private fun setEmailHelperText() {
        binding.outlinedLayoutEmail.helperText = HELPER_TEXT
    }
    //#2
    private fun removeEmailHelperText() {
        binding.outlinedLayoutEmail.helperText = null
    }
    //#3
    private fun setPasswordHelperText() {
        binding.outlinedLayoutPassword.helperText = HELPER_TEXT
    }
    //#4
    private fun removePasswordHelperText() {
        binding.outlinedLayoutPassword.helperText = null
    }
    //#5
    private fun setHelperText() {
        if (viewModel.userEmail.value.isNullOrBlank()) {
            setEmailHelperText()
        }

        if (viewModel.userPassword.value.isNullOrBlank()) {
            setPasswordHelperText()
        }
    }



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