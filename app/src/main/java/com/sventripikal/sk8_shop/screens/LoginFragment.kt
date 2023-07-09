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
    private lateinit var viewModel: LoginViewModel

    // onCreate
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        // inflate LoginFragment views
        binding = FragmentLoginBinding.inflate(inflater)

        // reference ApplicationViewModel
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

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

    // set UI observers fuction
    private fun setObserverUI() {

        // Fragment bindings block
        binding.apply {

            // observer for UserName editText view
            outlinedLayoutUserName.editText?.doOnTextChanged { userNameTextInput, _, _, _ ->

                // update viewmodel userNameText
                viewModel!!.updateUserName(userNameTextInput.toString())

                // update helper text if input not null or blank
                if (userNameTextInput.isNullOrBlank() != TRUE) {
                    removeUserNameHelperText()
                }

                // log
                timber(TAG, "USERNAME: ${viewModel!!.userName.value}", Priority.DEBUG)
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

                } else {    // remove end icon visibility
                    outlinedLayoutPassword.endIconMode = TextInputLayout.END_ICON_NONE
                }

                // log
                timber(TAG, "PASSWORD: ${viewModel!!.userPassword.value}", Priority.DEBUG)
            }

            // set navigation logic for newAccount button listener
            newAccountButton.setOnClickListener {
                buttonNavigationLogic()
            }

            // set navigation logic for existingAccount button listener
            existingAccountButton.setOnClickListener {
                buttonNavigationLogic()
            }
        }
    }

    // navigation logic for button listener
    private fun buttonNavigationLogic() {

        // viewmodel block
        viewModel.apply {

            // check if userName & password are filled
            if (this.editFieldsComplete()) {

                // clearEditTextFields
                clearEditTextFields()

                //navigate to next fragment
                val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
                binding.root.findNavController().navigate(action)

            } else {    // set helper text if not complete
                setHelperText()

                // log
                timber(TAG, HELPER_TEXT, Priority.ERROR)
            }
        }
    }

    // clear editTextFields
    private fun clearEditTextFields() {
        binding.outlinedLayoutUserName.editText!!.text.clear()
        binding.outlinedLayoutPassword.editText!!.text.clear()
    }


    /**
     * helper text functions to add / remove
     */
    //#1
    private fun setUserNameHelperText() {
        binding.outlinedLayoutUserName.helperText = HELPER_TEXT
    }
    //#2
    private fun removeUserNameHelperText() {
        binding.outlinedLayoutUserName.helperText = null
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
        if (viewModel.userName.value.isNullOrBlank()) {
            setUserNameHelperText()
        }

        if (viewModel.userPassword.value.isNullOrBlank()) {
            setPasswordHelperText()
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