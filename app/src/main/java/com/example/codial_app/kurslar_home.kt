package com.example.codial_app

import DB.MyDBHelper
import adapters.MyadapterList
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.codial_app.databinding.FragmentCodialHomeBinding
import com.example.codial_app.databinding.FragmentKurslarHomeBinding
import kotlinx.android.synthetic.main.custom_item_dialog.view.*
import kotlinx.android.synthetic.main.fragment_kurslar_home.view.*
import models.Kurs

class kurslar_home : Fragment() {
    lateinit var binding: FragmentKurslarHomeBinding
    lateinit var myadapterList: MyadapterList
    lateinit var myDBHelper: MyDBHelper
    lateinit var kursList: ArrayList<Kurs>
    var keyKurs = 0
    override fun onStart() {
        super.onStart()
        kursList = ArrayList()
        keyKurs = arguments?.getInt("key")!!
        myDBHelper = MyDBHelper(binding.root.context)
        kursList.addAll(myDBHelper.getAllKurs())
        when (keyKurs) {
            1 -> {
                binding.addClick.visibility = View.VISIBLE
            }
            else -> {
                binding.addClick.visibility = View.INVISIBLE
            }
        }



        myadapterList = MyadapterList(kursList, object : MyadapterList.Click {
            override fun clickItem(kurs: Kurs, position: Int) {
                val bundle = bundleOf("keyKurs" to kurs)
                when (keyKurs) {
                    1 -> {
                        findNavController().navigate(R.id.action_kurslar_home_to_kursHaqida, bundle)
                    }
                    2 -> {
                        findNavController().navigate(
                            R.id.action_kurslar_home_to_guruhlar_home,
                            bundle
                        )
                    }
                    3 -> {
                        findNavController().navigate(
                            R.id.action_kurslar_home_to_mentor_home,
                            bundle
                        )
                    }

                }
            }
        })

        binding.recycleViewKurslar.adapter = myadapterList
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKurslarHomeBinding.inflate(LayoutInflater.from(context))
        myDBHelper = MyDBHelper(binding.root.context)

        binding.addClick.setOnClickListener {
            val dialog = AlertDialog.Builder(binding.root.context).create()
            val dialogView = layoutInflater.inflate(R.layout.custom_item_dialog, null, false)
            dialog.setView(dialogView)

            dialogView.click_back.setOnClickListener {
                dialog.dismiss()
            }
            dialogView.click_next.setOnClickListener {
                val name = dialogView.edit_kurs_name.text.toString()
                val about = dialogView.edit_kurs_about.text.toString()
                myDBHelper.addKurs(Kurs(name, about))
                Toast.makeText(binding.root.context, "SAVE", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                onStart()
            }

            dialog.show()
        }


        return binding.root
    }
}