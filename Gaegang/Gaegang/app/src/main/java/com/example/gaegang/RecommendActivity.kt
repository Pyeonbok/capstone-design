package com.example.gaegang

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gaegang.adapter.RecommendAdapter
import com.example.gaegang.dataClass.Recommended
import com.example.gaegang.dataClass.Test
import kotlinx.android.synthetic.main.activity_recommend.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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


    val TAG = "TAG_RecommencdActivity"

    lateinit var mRetrofit: Retrofit
    lateinit var mRetrofitAPI: RetrofitAPI
    lateinit var mCallTodoList: retrofit2.Call<Test>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend)

        setRetrofit()

        // intent 전환
        val prev_intent = findViewById(R.id.button_prev) as ImageButton
        prev_intent.setOnClickListener {
            val intent= Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }

        val next_intent = findViewById(R.id.button_next) as ImageButton
        next_intent.setOnClickListener {
            val intent= Intent(this,TimeTableActivity::class.java)
            callTodoList()
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

    // 리스트를 불러온다.
    private fun callTodoList() {
        mCallTodoList = mRetrofitAPI.getTodoList(getSttString())
        mCallTodoList.enqueue(mRetrofitCallback)
    }

    // 나중에 음성인식 결과로 바꾸고 ㄱㄱ 
    // 테스트중
    private fun getSttString(): String {
        var result = findViewById<TextView>(R.id.text_non0)

        return result.text.toString()
    }

    // http요청을 보냈고 이건 응답을 받을 콜벡메서드
    private val mRetrofitCallback = (object : retrofit2.Callback<Test> {
        override fun onFailure(call: Call<Test>, t: Throwable) {
            t.printStackTrace()
            Log.d(TAG, "에러입니다. => ${t.message.toString()}")

        }

        override fun onResponse(call: Call<Test>, response: Response<Test>) {
            val result = response.body()
            Log.d(TAG, "결과는 => $result")

        }
    })

    private fun setRetrofit() {
        //레트로핏으로 가져올 url설정하고 세팅
        mRetrofit = Retrofit
            .Builder()
            .baseUrl("http://192.168.0.9:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //인터페이스로 만든 레트로핏 api요청 받는 것 변수로 등록
        mRetrofitAPI = mRetrofit.create(RetrofitAPI::class.java)
    }
}