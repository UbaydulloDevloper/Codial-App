package com.example.codial_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.codial_app.databinding.FragmentCodialHomeBinding

class Codial_home : Fragment() {
    lateinit var binding: FragmentCodialHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCodialHomeBinding.inflate(LayoutInflater.from(context))


        binding.kurslarClick.setOnClickListener {
            val bundle = bundleOf("key" to 1)
            findNavController().navigate(R.id.action_codial_home_to_kurslar_home, bundle)

        }
        binding.guruhlarClick.setOnClickListener {
            val bundle = bundleOf("key" to 2)
            findNavController().navigate(R.id.action_codial_home_to_kurslar_home, bundle)

        }
        binding.mentorClick.setOnClickListener {
            val bundle = bundleOf("key" to 3)
            findNavController().navigate(R.id.action_codial_home_to_kurslar_home, bundle)
        }

        return binding.root
    }
}