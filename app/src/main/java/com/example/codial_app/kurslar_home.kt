package com.example.codial_app

import DB.MyDBHelper
import adapters.MyadapterList
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.custom_item_dialog.view.*
import kotlinx.android.synthetic.main.fragment_kurslar_home.view.*
import models.Kurs

class kurslar_home : Fragment() {
    lateinit var root: View
    lateinit var myadapterList: MyadapterList
    lateinit var myDBHelper: MyDBHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_kurslar_home, container, false)
        myDBHelper = MyDBHelper(root.context)

        root.add_click.setOnClickListener {
            val dialog = AlertDialog.Builder(root.context).create()
            val dialogView = layoutInflater.inflate(R.layout.custom_item_dialog, null, false)
            dialog.setView(dialogView)

            dialogView.click_back.setOnClickListener {
                dialog.dismiss()
            }
            dialogView.click_next.setOnClickListener {
                val name = dialogView.edit_kurs_name.text.toString()
                val about = dialogView.edit_kurs_about.text.toString()
                myDBHelper.addKurs(Kurs(name, about))
                Toast.makeText(root.context, "SAVE", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                onResume()
            }

            dialog.show()
        }


        return root
    }

    override fun onResume() {
        super.onResume()
        myDBHelper = MyDBHelper(root.context)
        val list = ArrayList<Kurs>()
        list.addAll(myDBHelper.getAllKurs())
        myadapterList = MyadapterList(root.context, list, object : MyadapterList.Click {
            override fun clickItem(position: Int) {
                Toast.makeText(root.context, "click$position", Toast.LENGTH_SHORT).show()
            }
        })
        root.recycle_view_kurslar.adapter = myadapterList
    }

}