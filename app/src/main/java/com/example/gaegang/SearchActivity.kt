package com.example.gaegang

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.gaegang.R.id
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SearchActivity : AppCompatActivity() {

    var mRecognizer: SpeechRecognizer? = null
    var sttBtn: ImageButton? = null
    var textView: TextView? = null
    val PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // stt
        if (Build.VERSION.SDK_INT >= 29) {
            // 퍼미션 체크
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.INTERNET,
                Manifest.permission.RECORD_AUDIO), PERMISSION)
        }
        textView = findViewById<View>(R.id.text_stt) as TextView
        sttBtn = findViewById<View>(R.id.button_stt) as ImageButton
        intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent!!.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, packageName)
        intent!!.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")
        sttBtn!!.setOnClickListener { v: View? ->
            mRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
            mRecognizer?.run {
                setRecognitionListener(listener)
                startListening(intent)
            }
        }


        // intent 전환
        val next_intent = findViewById(id.button_next) as ImageButton
        next_intent.setOnClickListener {
            val intent = Intent(this, RecommendActivity::class.java)
            if (textView!!.text == "※ 이 곳에 음성 인식 결과가 나타납니다.") {
                Toast.makeText(applicationContext, "음성 인식 결과가 없습니다.",
                    Toast.LENGTH_SHORT).show()
            } else {
                val database : FirebaseDatabase = FirebaseDatabase.getInstance()
                val myRef : DatabaseReference = database.getReference("sttText")
                myRef.setValue(textView!!.text.toString())


                intent.putExtra("textStt", textView!!.text)
                startActivity(intent)
            }
        }

        /*
        // test용 intent (음성인식 못할 때 ㅠ)
        val next_intent = findViewById(id.button_next) as ImageButton
        next_intent.setOnClickListener {
            val intent = Intent(this, RecommendActivity::class.java)
            intent.putExtra("textStt", textView!!.text)
            startActivity(intent)

        }
*/

        val prev_intent = findViewById(id.button_prev) as ImageButton
        prev_intent.setOnClickListener {
            val intent2= Intent(this,SettingActivity::class.java)
            startActivity(intent2)
        }
    }

    private val listener: RecognitionListener = object : RecognitionListener {
        override fun onReadyForSpeech(params: Bundle) {
            Toast.makeText(applicationContext, "음성인식을 시작합니다.",
                Toast.LENGTH_SHORT).show()
        }

        override fun onBeginningOfSpeech() {}
        override fun onRmsChanged(rmsdB: Float) {}
        override fun onBufferReceived(buffer: ByteArray) {}
        override fun onEndOfSpeech() {}
        override fun onError(error: Int) {
            val message: String
            message = when (error) {
                SpeechRecognizer.ERROR_AUDIO -> "오디오 에러"
                SpeechRecognizer.ERROR_CLIENT -> "클라이언트 에러"
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "퍼미션 없음"
                SpeechRecognizer.ERROR_NETWORK -> "네트워크 에러"
                SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "네트웍 타임아웃"
                SpeechRecognizer.ERROR_NO_MATCH -> "찾을 수 없음"
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RECOGNIZER가 바쁨"
                SpeechRecognizer.ERROR_SERVER -> "서버가 이상함"
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "말하는 시간초과"
                else -> "알 수 없는 오류임"
            }
            Toast.makeText(applicationContext, "에러가 발생하였습니다. : " +
                    message, Toast.LENGTH_SHORT).show()
        }

        override fun onResults(results: Bundle) {
            // 말을 하면 ArrayList에 단어를 넣고 textView에 단어를 이어줍니다.
            val matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            for (i in matches!!.indices) {
                textView!!.text = matches[i]
            }
        }

        override fun onPartialResults(partialResults: Bundle) {}
        override fun onEvent(eventType: Int, params: Bundle) {}
    }
}