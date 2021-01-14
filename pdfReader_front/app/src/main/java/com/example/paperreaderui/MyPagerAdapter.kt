package com.example.paperreaderui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) { //switch()문과 동일하다.
            0 -> {FirstFragment()}
            1 -> {SecondFragment()}
            else -> {return ThirdFragment()}
        }
    }

    override fun getCount(): Int {
        return 3 //3개
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "읽고 있는 문서"
            1 -> "저장된 문서"
            else -> {return "모든 문서"}
        }
    }

}
