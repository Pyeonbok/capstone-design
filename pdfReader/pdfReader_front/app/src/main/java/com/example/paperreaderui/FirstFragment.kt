package com.example.paperreaderui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        val recyclerView=view.findViewById(R.id.recyclerView) as RecyclerView
        val list = ArrayList<titleItem>()

        list.add(titleItem(getDrawable(view.context,R.drawable.title6)!!,
            getString(R.string.title06),
            getString(R.string.author06),
            getString(R.string.source06),
            getString(R.string.year06)))

        val reAdapter = RecyclerAdapter(list)
        recyclerView.adapter = reAdapter


        // 구분선
        recyclerView.addItemDecoration(
            DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        )

 /**
        recyclerView.addItemDecoration(
            DividerItemDecoration(view.context, DividerItemDecoration.HORIZONTAL)
        )

**/
        return view
    }
}
