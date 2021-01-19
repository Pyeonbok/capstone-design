package com.example.paperreaderui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text


class RecyclerAdapter(private val items: ArrayList<titleItem>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener { it ->
//            Toast.makeText(it.context, "Clicked: ${item.title}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_ing_item, parent, false)
        return RecyclerAdapter.ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v

        fun bind(listener: View.OnClickListener, item: titleItem) {
            view.findViewById<ImageView>(R.id.paper_sign).setImageDrawable(item.image)
            view.findViewById<TextView>(R.id.title).text = item.title
            view.findViewById<TextView>(R.id.author).text = item.author
            view.findViewById<TextView>(R.id.source).text = item.source
            view.findViewById<TextView>(R.id.year).text = item.year
            view.setOnClickListener(listener)
        }
    }
}