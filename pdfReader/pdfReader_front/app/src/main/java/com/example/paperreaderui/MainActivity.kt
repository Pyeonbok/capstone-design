package com.example.paperreaderui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // spinner
        val spinner = findViewById(R.id.spinner_paper) as Spinner
        val adapter = ArrayAdapter.createFromResource(
                this, R.array.paper_array,
                android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter=adapter
        spinner.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            // spinner select
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        // tabLayout
        val tabLayout = findViewById(R.id.tabLayout) as TabLayout
        val viewPager = findViewById(R.id.viewPager) as ViewPager

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter=fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)

        // 설정으로 intent 전환
        val go_intent = findViewById(R.id.imageButton3) as ImageButton
        go_intent.setOnClickListener {
            val intent= Intent(this,SettingActivity::class.java)
            startActivity(intent)
        }


        // 파일 불러오기 (미완)
        /**
        fun fileRead(context: Context,fileUri: Uri):String{
            val sb = StringBuilder()
            val inputStream = context.contentResolver.openInputStream(fileUri)
            inputStream?.let {
                val reader = BufferedReader(InputStreamReader(inputStream))
                val readLines = reader.readLines()
                readLines.forEach {
                    sb.append(it)
                }
                it.close()
            }
            return sb.toString()
        }

        val fileButton = findViewById(R.id.imageButton2) as ImageButton
        fileButton.setOnClickListener {

        }
        **/
    }
}
