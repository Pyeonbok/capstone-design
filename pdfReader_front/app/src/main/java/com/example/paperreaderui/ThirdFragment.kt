package com.example.paperreaderui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class ThirdFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_third, container, false)

        val recyclerView=view.findViewById(R.id.recyclerView) as RecyclerView
        val list = ArrayList<titleItem>()
        list.add(titleItem(
            AppCompatResources.getDrawable(view.context, R.drawable.title1)!!,
            getString(R.string.title01),
            getString(R.string.author01),
            getString(R.string.source01),
            getString(R.string.year01)))
        list.add(titleItem(
            AppCompatResources.getDrawable(view.context, R.drawable.title2)!!,
            getString(R.string.title02),
            getString(R.string.author02),
            getString(R.string.source02),
            getString(R.string.year02)))
        list.add(titleItem(
            AppCompatResources.getDrawable(view.context, R.drawable.title3)!!,
            getString(R.string.title03),
            getString(R.string.author03),
            getString(R.string.source03),
            getString(R.string.year03)))
        list.add(titleItem(
            AppCompatResources.getDrawable(view.context, R.drawable.title4)!!,
            getString(R.string.title04),
            getString(R.string.author04),
            getString(R.string.source04),
            getString(R.string.year04)))
        list.add(titleItem(
            AppCompatResources.getDrawable(view.context, R.drawable.title5)!!,
            getString(R.string.title05),
            getString(R.string.author05),
            getString(R.string.source05),
            getString(R.string.year05)))
        list.add(titleItem(
            AppCompatResources.getDrawable(view.context, R.drawable.title6)!!,
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

        return view
    }
}