package com.sventripikal.sk8_shop.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sventripikal.sk8_shop.Priority
import com.sventripikal.sk8_shop.TAG
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


    private fun setObserverUI() {

        // observer for Email editText view
        binding.outlinedLayoutEmail.editText?.doOnTextChanged { emailTextInput, _, _, _ ->
            viewModel.updateUserEmail(emailTextInput.toString())
            timber(TAG,"EMAIL: ${viewModel.userEmail.value}", Priority.DEBUG)
        }

        // observer for Password editText view
        binding.outlinedLayoutPassword.editText?.doOnTextChanged { passwordTextInput, _, _, _ ->
            viewModel.updateUserPassword(passwordTextInput.toString())
            timber(TAG,"PASSWORD: ${viewModel.userPassword.value}", Priority.DEBUG)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        timber(TAG, MESSAGE_DESTROYED, Priority.ERROR)
    }
}