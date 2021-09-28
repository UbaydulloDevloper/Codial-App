package com.example.codial_app

import adapters.MyadapterList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_guruhlar_home.view.*

class Guruhlar_home : Fragment() {
    lateinit var root: View
    lateinit var myadapterList: MyadapterList
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_guruhlar_home, container, false)
/*
        val arrayList = ArrayList<String>()
        arrayList.add("Android\nDeveloper")
        arrayList.add("Backend\nDeveloper")
        arrayList.add("UI UX")
        arrayList.add("SMM\nDesign")
        arrayList.add("Frontend\nDeveloper")
        arrayList.add("FullStack\nDeveloper")
        arrayList.add("Kampyuter\nSavodhonligi")
        myadapterList = MyadapterList(arrayList, object : MyadapterList.Click {
            override fun clickItem(position: Int) {
                Toast.makeText(root.context, "click$position", Toast.LENGTH_SHORT).show()
            }
        })
        root.recycle_view_guruhlar.adapter = myadapterList*/

        return root
    }
}