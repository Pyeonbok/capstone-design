package com.example.gaegang

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gaegang.adapter.RecommendAdapter
import com.example.gaegang.dataClass.RecommendedItem
import kotlinx.android.synthetic.main.activity_recommend.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecommendActivity : AppCompatActivity() {

/* 아래의 recList는 test를 위한 예시입니다 */
/*
* 강의명
* 교수명
* 학수번호
* 대학(원)
* 학과(부)
* 이수구분
* 학년
* 학점
* 수업방법
* 시간,강의실
*/

    var recList = arrayListOf<RecommendedItem>(
        RecommendedItem("강의명","교수명","학수번호",
                "대학(원)","학과(부)","이수구분",
                "학년", "학점","수업방법","시간,강의실"),
        RecommendedItem("강의명","교수명","학수번호",
            "대학(원)","학과(부)","이수구분",
            "학년", "학점","수업방법","시간,강의실"),
        RecommendedItem("강의명","교수명","학수번호",
            "대학(원)","학과(부)","이수구분",
            "학년", "학점","수업방법","시간,강의실"),
        RecommendedItem("강의명","교수명","학수번호",
            "대학(원)","학과(부)","이수구분",
            "학년", "학점","수업방법","시간,강의실"),
        RecommendedItem("강의명","교수명","학수번호",
            "대학(원)","학과(부)","이수구분",
            "학년", "학점","수업방법","시간,강의실"),
        RecommendedItem("강의명","교수명","학수번호",
            "대학(원)","학과(부)","이수구분",
            "학년", "학점","수업방법","시간,강의실"),
        RecommendedItem("강의명","교수명","학수번호",
            "대학(원)","학과(부)","이수구분",
            "학년", "학점","수업방법","시간,강의실"),
        RecommendedItem("강의명","교수명","학수번호",
            "대학(원)","학과(부)","이수구분",
            "학년", "학점","수업방법","시간,강의실"),
        RecommendedItem("강의명","교수명","학수번호",
            "대학(원)","학과(부)","이수구분",
            "학년", "학점","수업방법","시간,강의실"),
        RecommendedItem("강의명","교수명","학수번호",
            "대학(원)","학과(부)","이수구분",
            "학년", "학점","수업방법","시간,강의실")
    )


    val TAG = "TAG_RecommencdActivity"

    lateinit var mRetrofit: Retrofit
    lateinit var mRetrofitAPI: RetrofitAPI
    lateinit var mCallTodoList: retrofit2.Call<RecommendedItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend)


        setRetrofit()
        callTodoList()

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

    // 리스트를 불러온다.
    private fun callTodoList() {
        mCallTodoList = mRetrofitAPI.getTodoList(getSttString())
        mCallTodoList.enqueue(mRetrofitCallback)
    }

    // 음성 인식 결과 text
    private fun getSttString(): String {
      //  var result = findViewById<TextView>(R.id.text_stt2)
        var result = findViewById<TextView>(R.id.text_non0)

        return result.text.toString()
    }

    // http요청을 보냈고 이건 응답을 받을 콜벡메서드
    private val mRetrofitCallback = (object : retrofit2.Callback<RecommendedItem> {
        override fun onFailure(call: Call<RecommendedItem>, t: Throwable) {
            t.printStackTrace()
            Log.d(TAG, "에러입니다. => ${t.message.toString()}")

        }

        override fun onResponse(call: Call<RecommendedItem>, response: Response<RecommendedItem>) {
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