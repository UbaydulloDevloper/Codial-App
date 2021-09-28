package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.codial_app.R
import kotlinx.android.synthetic.main.item_rv.view.*
import models.Kurs
import kotlin.coroutines.coroutineContext

class MyadapterList(var context: Context ,var list: ArrayList<Kurs>, var click: Click) :
    RecyclerView.Adapter<MyadapterList.Vh>() {
    inner class Vh(var itemRv: View) : RecyclerView.ViewHolder(itemRv.rootView), Click {
        fun onBind(name: Kurs, position: Int) {
            itemRv.title_text.text = name.name
            clickItem(position)
        }

        override fun clickItem(position: Int) {
        }


    }

    interface Click {
        fun clickItem(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}