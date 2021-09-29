package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.codial_app.R
import com.example.codial_app.databinding.ItemRvBinding
import kotlinx.android.synthetic.main.item_rv.view.*
import models.Kurs
import kotlin.coroutines.coroutineContext

class MyadapterList( var list: ArrayList<Kurs>, var click: Click) :
    RecyclerView.Adapter<MyadapterList.Vh>() {
    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(name: Kurs, position: Int) {
            itemRv.titleText.text = name.name
            itemRv.root.setOnClickListener {
                click.clickItem(name, position)
            }

        }
    }

    interface Click {
        fun clickItem(kurs: Kurs, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}