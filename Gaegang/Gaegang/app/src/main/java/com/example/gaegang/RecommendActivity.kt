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

/* 아래의 recList는 test를 위한 예시입니다 */

    /*
    * 강의명 / 교수명
    * 이수구분 / 학점
    * 수업방법 (온라인/오프라인)
    * 요일 / 시간
    */
    var recList = arrayListOf<Recommended>(
        Recommended("강의명","교수명","이수구분","3","수업방법","요일","시간"),
        Recommended("강의명","교수명","이수구분","3","수업방법","요일","시간") ,
        Recommended("강의명","교수명","이수구분","3","수업방법","요일","시간") ,
        Recommended("강의명","교수명","이수구분","3","수업방법","요일","시간") ,
        Recommended("강의명","교수명","이수구분","3","수업방법","요일","시간") ,
        Recommended("강의명","교수명","이수구분","3","수업방법","요일","시간") ,
        Recommended("강의명","교수명","이수구분","3","수업방법","요일","시간") ,
        Recommended("강의명","교수명","이수구분","3","수업방법","요일","시간") ,
        Recommended("강의명","교수명","이수구분","3","수업방법","요일","시간") ,
        Recommended("강의명","교수명","이수구분","3","수업방법","요일","시간")
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

        // textView (stt) 값 전달받기
        val getintent = getIntent()
        if (getintent.hasExtra("textStt")){
            text_stt2.text = getintent.getStringExtra("textStt")
        } else {
        }


        // recyclerview
        val mAdapter = RecommendAdapter(this, recList)
        recyclerview_rec.adapter = mAdapter

        val lm = LinearLayoutManager(this)
        recyclerview_rec.layoutManager = lm
        recyclerview_rec.setHasFixedSize(true)
    }
}