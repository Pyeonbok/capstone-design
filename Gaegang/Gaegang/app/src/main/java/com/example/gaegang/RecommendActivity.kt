package com.example.gaegang

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gaegang.adapter.RecommendAdapter
import com.example.gaegang.dataClass.RecommendedItem
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_recommend.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*
import kotlin.concurrent.timer


class RecommendActivity : AppCompatActivity() {

    private var firebaseDatabase: FirebaseDatabase? = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = firebaseDatabase!!.getReference()
    var mRootDatabaseReference = FirebaseDatabase.getInstance().reference


    fun getLocalIpAddress(): String? {
        try {
            val en: Enumeration<NetworkInterface> = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf: NetworkInterface = en.nextElement()
                val enumIpAddr: Enumeration<InetAddress> = intf.getInetAddresses()
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress: InetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress() && inetAddress is Inet4Address) {
                        return inetAddress.getHostAddress()
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }
        return null
    }

    val TAG = "TAG_RecommencdActivity"

    lateinit var mRetrofit: Retrofit
    lateinit var mRetrofitAPI: RetrofitAPI
    lateinit var mCallTodoList: retrofit2.Call<RecommendedItem>

    var stt_text = ""

    val recList:ArrayList<RecommendedItem> = arrayListOf<RecommendedItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend)

        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val myclass : DatabaseReference = database.getReference("Recommendedclass")



        var temp = arrayListOf<String>()
        //Read from the database
        val postListener = object : ValueEventListener {
            var Lecture= ""
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI

                val value = dataSnapshot?.value
                //textView.text = "$value"
                Log.d(TAG, "Value is: " + value.toString());

                Lecture = value.toString()//강의 스트링으로 받기
                var arr = Lecture.split("], [")
                var recommendedClasses = Array(10, {item -> ""})
                //스트링 형태 분리
                for(i in 0 until arr.size){
                    if(i==0) {
                        val str1 = arr[i].replace("[[","")
//                    Log.w(TAG, ">>"+ i + " " + str1)
                        recommendedClasses[i]=str1
                    }
                    else if(i==9){
                        val str2 = arr[i].replace("]]","")
//                    Log.w(TAG, ">>"+ i + " " + str2)
                        recommendedClasses[i]=str2
                    }
                    else {
                        val str3=arr[i]
//                    Log.w(TAG, ">>"+ i + " " + arr[i])
                        recommendedClasses[i]=str3
                    }

                }
                //데이터 갯수만큼 반복,recList에추가
                for(i in 0 until recommendedClasses.size){
                    var classArray = recommendedClasses[i].split(", ")

                    recList.add(RecommendedItem(classArray[0], classArray[1], classArray[2], classArray[3], classArray[4],
                            classArray[5], classArray[6], classArray[7], classArray[8], classArray[9]))
                }


            }


            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }


        }
        myclass.addValueEventListener(postListener)




        // intent 전환
        val prev_intent = findViewById(R.id.button_prev) as ImageButton
        prev_intent.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        val next_intent = findViewById(R.id.button_next) as ImageButton
        next_intent.setOnClickListener {
            val intent = Intent(this, TimeTableActivity::class.java)
            startActivity(intent)
        }


        // textView (stt) 값 전달받기
        val getintent = getIntent()
        if (getintent.hasExtra("textStt")) {
            text_stt2.text = getintent.getStringExtra("textStt")
            stt_text = getintent.getStringExtra("textStt").toString()
        } else {
        }


        // recyclerview
        val mAdapter = RecommendAdapter(this, recList)
        recyclerview_rec.adapter = mAdapter

        val lm = LinearLayoutManager(this)
        recyclerview_rec.layoutManager = lm
        recyclerview_rec.setHasFixedSize(true)

        setRetrofit()
        callTodoList()


    }

    // 리스트를 불러온다.
    private fun callTodoList() {
        mCallTodoList = mRetrofitAPI.getTodoList(getSttString())
        mCallTodoList.enqueue(mRetrofitCallback)
    }

    // 음성 인식 결과 text
    private fun getSttString(): String {
        var result = stt_text
        //  var result = findViewById<TextView>(R.id.text_non0)   // test

        return result
        // return result.text.toString()
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
                .baseUrl("http://192.168.0.19:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        //인터페이스로 만든 레트로핏 api요청 받는 것 변수로 등록
        mRetrofitAPI = mRetrofit.create(RetrofitAPI::class.java)
    }
}