package com.example.codial_app

import DB.MyDBHelper
import adapters.MyadapterList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_kurslar_home.view.*
import kotlinx.android.synthetic.main.fragment_mentor_home.view.*
import models.Kurs

class mentor_home : Fragment() {
    lateinit var root: View
    lateinit var myadapterList: MyadapterList
    lateinit var myDBHelper: MyDBHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_mentor_home, container, false)
        onResume()




        return root

    }



}