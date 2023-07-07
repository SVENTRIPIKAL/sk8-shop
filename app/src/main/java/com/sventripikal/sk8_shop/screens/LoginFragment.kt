package com.sventripikal.sk8_shop.screens

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.graphics.ColorSpace.Rgb
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.sventripikal.sk8_shop.Priority
import com.sventripikal.sk8_shop.R
import com.sventripikal.sk8_shop.TAG
import com.sventripikal.sk8_shop.TRUE
import com.sventripikal.sk8_shop.databinding.FragmentLoginBinding
import com.sventripikal.sk8_shop.timber


private const val MESSAGE_CREATED = "LOGIN-FRAG CREATED"
private const val MESSAGE_DESTROYED = "LOGIN-FRAG DESTROYED"

class LoginFragment : Fragment() {

    // viewBinder
    private lateinit var binding: FragmentLoginBinding

    // viewModel
    private lateinit var viewModel: ApplicationViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // inflate views
        binding = FragmentLoginBinding.inflate(inflater)

        // reference ApplicationViewModel
        viewModel = ViewModelProvider(this)[ApplicationViewModel::class.java]

        // create lifecyle bindings
        setLifeCycleBindings()

        // set UI observers
        setObserverUI()

        timber(TAG, MESSAGE_CREATED, Priority.VERBOSE)

        // return root layout
        return binding.root
    }


    private fun setLifeCycleBindings() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }


    @SuppressLint("ResourceAsColor")
    private fun setObserverUI() {
        // Fragment bindings block
        binding.apply {

            // observer for Email editText view
            outlinedLayoutEmail.editText?.doOnTextChanged { emailTextInput, _, _, _ ->
                if (emailTextInput?.isNotBlank() == TRUE) {
                    viewModel!!.updateUserEmail(emailTextInput.toString())
                    timber(TAG,"EMAIL: ${viewModel!!.userEmail.value}", Priority.DEBUG)
                }
            }

            // observer for Password editText view
            outlinedLayoutPassword.editText?.doOnTextChanged { passwordTextInput, _, _, _ ->
                // check if view contains text
                when (passwordTextInput?.isNotBlank()) {
                    TRUE -> { // update viewmodel passwordtext
                        viewModel!!.updateUserPassword(passwordTextInput.toString())

                        // make end icon visible
                        outlinedLayoutPassword.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE

                        // log
                        timber(TAG,"PASSWORD: ${viewModel!!.userPassword.value}", Priority.DEBUG)
                    }
                    else -> { // remove end icon visibility
                        outlinedLayoutPassword.endIconMode = TextInputLayout.END_ICON_NONE
                    }
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        timber(TAG, MESSAGE_DESTROYED, Priority.ERROR)
    }
}