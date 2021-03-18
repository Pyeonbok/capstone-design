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
    * 시간 / 강의실
    */
    var recList = arrayListOf<Recommended>(
        Recommended("역사로만나는중국의문화코드","조형진",
                "교양선택","3",
                "온라인(동영상+화상)",
                "화(5B-6)(7-8A)","SM108"),
        Recommended("역사로만나는중국의문화코드","송승석",
                "교양선택","3",
                "온라인(동영상+화상)",
                "화(5B-6)(7-8A)","SM303") ,
        Recommended("중국어1","주기평",
                "교양필수","3",
                "온라인(동영상)",
                "월(7-8A)(8B-9)","SM205") ,
        Recommended("중국어1","주기평",
                "교양필수","3",
                "온라인(동영상)",
                "월(야1)(야2)(야3)","SM205") ,
        Recommended("중국어1","안성재",
                "교양필수","3",
                "온라인(동영상)",
                "월(4-5A),화(4-5A)","SM506") ,
        Recommended("(이공계를위한)중국과학기술의10가지기적","이정희",
                "교양선택","3",
                "온라인(화상)+오프라인",
                "금(2B-3)(4-5A)","SM503") ,
        Recommended("영상으로만나는중국","이정희",
                "교양선택","3",
                "온라인(화상)+오프라인",
                "수(1-2A)(2B-3)","SM503") ,
        Recommended("영상으로만나는중국","이정희",
                "교양선택","3",
                "온라인(화상)+오프라인",
                "목(5B-6)(7-8A)","SM503") ,
        Recommended("현대중국의이해","우병국",
                "교양선택","3",
                "온라인(화상)",
                "금(1-2A)(2B-3)","SM506") ,
        Recommended("현대중국의이해","우병국",
                "교양선택","3",
                "온라인(화상)",
                "금(5B-6)(7-8A)","SM506")
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

        val next_intent = findViewById(R.id.button_next) as ImageButton
        next_intent.setOnClickListener {
            val intent= Intent(this,TimeTableActivity::class.java)
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