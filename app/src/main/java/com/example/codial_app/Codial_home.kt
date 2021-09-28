package com.example.codial_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_codial_home.view.*

class Codial_home : Fragment() {
    lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_codial_home, container, false)

        root.kurslar_click.setOnClickListener {
            findNavController().navigate(R.id.action_codial_home_to_kurslar_home)

        }
        root.guruhlar_click.setOnClickListener {
            findNavController().navigate(R.id.action_codial_home_to_kurslar_home)

        }
        root.mentor_click.setOnClickListener {
            findNavController().navigate(R.id.action_codial_home_to_kurslar_home)

        }

        return root
    }
}