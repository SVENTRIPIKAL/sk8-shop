package com.sventripikal.sk8_shop.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sventripikal.sk8_shop.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    // viewBinder
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // inflate views
        binding = FragmentLoginBinding.inflate(inflater)

        // return root layout
        return binding.root
    }
}