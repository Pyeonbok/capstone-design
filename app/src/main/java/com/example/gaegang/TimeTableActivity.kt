package com.example.gaegang

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class TimeTableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable)

        // intent 전환
        val prev_intent = findViewById(R.id.button_prev) as ImageButton
        prev_intent.setOnClickListener {
            val intent= Intent(this,RecommendActivity::class.java)
            startActivity(intent)
        }

    }
}