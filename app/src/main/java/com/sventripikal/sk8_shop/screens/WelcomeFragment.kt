package com.sventripikal.sk8_shop.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sventripikal.sk8_shop.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    // viewBinder
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // inflate WelcomeFragment views
        binding = FragmentWelcomeBinding.inflate(inflater)

        // return root layout
        return binding.root
    }
}