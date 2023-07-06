package com.sventripikal.sk8_shop.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout.END_ICON_NONE
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE
import com.google.android.material.textfield.TextInputLayout.EndIconMode
import com.sventripikal.sk8_shop.Priority
import com.sventripikal.sk8_shop.TAG
import com.sventripikal.sk8_shop.TRUE
import com.sventripikal.sk8_shop.databinding.FragmentLoginBinding
import com.sventripikal.sk8_shop.timber
import timber.log.Timber


private const val MESSAGE_CREATED = "LOGIN-FRAG CREATED"
private const val MESSAGE_DESTROYED = "LOGIN-FRAG DESTROYED"

class LoginFragment : Fragment() {

    // viewBinder
    private lateinit var binding: FragmentLoginBinding

    // viewModel
    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // inflate views
        binding = FragmentLoginBinding.inflate(inflater)

        // reference viewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]



        timber(TAG, MESSAGE_CREATED, Priority.VERBOSE)
        // return root layout
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        timber(TAG, MESSAGE_DESTROYED, Priority.ERROR)
    }
}