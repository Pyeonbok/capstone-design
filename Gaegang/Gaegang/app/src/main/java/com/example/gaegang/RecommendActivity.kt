package com.example.gaegang

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gaegang.adapter.RecommendAdapter
import com.example.gaegang.dataClass.Recommended
import kotlinx.android.synthetic.main.activity_recommend.*

class RecommendActivity : AppCompatActivity() {

/* 라벨, 강의이름, 교수이름, 이수구분, 수업방법, 요일, 시간
*
* 아래의 recList는 test를 위한 예시입니다 */
    var recList = arrayListOf<Recommended>(
    Recommended("라벨","강의명","교수명","이수구분","수업방법","요일","시간") ,
    Recommended("외국어","프랑스어2","교수명","교양필수","온라인","목요일","1A-2B"),
    Recommended("역사","전쟁사","교수명","이수구분","수업방법","요일","시간"),
    Recommended("라벨","강의명","교수명","이수구분","수업방법","요일","시간"),
    Recommended("라벨","강의명","교수명","이수구분","수업방법","요일","시간") ,
    Recommended("라벨","강의명","교수명","이수구분","수업방법","요일","시간"),
    Recommended("라벨","강의명","교수명","이수구분","수업방법","요일","시간")
)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend)

        // intent 전환
        val prev_intent = findViewById(R.id.button_prev) as ImageButton
        prev_intent.setOnClickListener {
            val intent= Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }

        // recyclerview
        val mAdapter = RecommendAdapter(this, recList)
        recyclerview_rec.adapter = mAdapter

        val lm = LinearLayoutManager(this)
        recyclerview_rec.layoutManager = lm
        recyclerview_rec.setHasFixedSize(true)
    }


}