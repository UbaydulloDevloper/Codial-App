package com.example.codial_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.codial_app.databinding.FragmentKursHaqidaBinding
import models.Kurs

class KursHaqida : Fragment() {
    lateinit var binding: FragmentKursHaqidaBinding
    lateinit var kurs: Kurs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKursHaqidaBinding.inflate(LayoutInflater.from(context))

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        kurs = Kurs()
        kurs = arguments?.getSerializable("keyKurs") as Kurs
        binding.textAboutKurs.text = kurs.about
        binding.titleKursHaqida.text = kurs.name
        binding.addStudentClick.setOnClickListener {
            var bundle = bundleOf("keyKurs1" to kurs, "keyhas" to 0)

        }
    }


}