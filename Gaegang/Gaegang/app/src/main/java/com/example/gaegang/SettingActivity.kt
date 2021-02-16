package com.example.gaegang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class SettingActivity : AppCompatActivity() {
    lateinit var adapter1: ArrayAdapter<CharSequence>
    var adapter2: ArrayAdapter<CharSequence>? = null
    var choice_uni = ""
    var choice_major = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        // intent 전환
        val next_intent = findViewById(R.id.button_next) as ImageButton
        next_intent.setOnClickListener {
            val intent= Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }

        // Spinner
        val spinner1: Spinner = findViewById(R.id.major_spinner1) as Spinner
        val spinner2: Spinner = findViewById(R.id.major_spinner2) as Spinner
        adapter1 = ArrayAdapter.createFromResource(this, R.array.arr_uni, android.R.layout.simple_spinner_dropdown_item)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.setAdapter(adapter1)
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                if (adapter1.getItem(i) == "인문대학") {

                    choice_uni = "인문대학" //버튼 클릭시 출력을 위해 값을 넣었습니다.
                    adapter2 = ArrayAdapter.createFromResource(this@SettingActivity, R.array.arr_inmun, android.R.layout.simple_spinner_dropdown_item)

                    adapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner2.setAdapter(adapter2)
                    spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                            choice_major = adapter2!!.getItem(i).toString()
                        }

                        override fun onNothingSelected(adapterView: AdapterView<*>?) {
                            Toast.makeText(this@SettingActivity, "소속학과를 선택해주세요!", Toast.LENGTH_SHORT).show()
                        }
                    })
                } else if (adapter1.getItem(i) == "자연과학대학") {
                    choice_uni = "자연과학대학"
                    adapter2 = ArrayAdapter.createFromResource(this@SettingActivity, R.array.arr_jayeon, android.R.layout.simple_spinner_dropdown_item)
                    adapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner2.setAdapter(adapter2)
                    spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                            choice_major = adapter2!!.getItem(i).toString()
                        }

                        override fun onNothingSelected(adapterView: AdapterView<*>?) {
                            Toast.makeText(this@SettingActivity, "소속학과를 선택해주세요!", Toast.LENGTH_SHORT).show()

                        }
                    })
                } else if (adapter1.getItem(i) == "사회과학대학") {
                    choice_uni = "사회과학대학"
                    adapter2 = ArrayAdapter.createFromResource(this@SettingActivity, R.array.arr_sahoe, android.R.layout.simple_spinner_dropdown_item)
                    adapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner2.setAdapter(adapter2)
                    spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                            choice_major = adapter2!!.getItem(i).toString()
                        }

                        override fun onNothingSelected(adapterView: AdapterView<*>?) {
                            Toast.makeText(this@SettingActivity, "소속학과를 선택해주세요!", Toast.LENGTH_SHORT).show()

                        }
                    })
                } else if (adapter1.getItem(i) == "글로벌정경대학") {
                    choice_uni = "글로벌정경대학"
                    adapter2 = ArrayAdapter.createFromResource(this@SettingActivity, R.array.arr_global, android.R.layout.simple_spinner_dropdown_item)
                    adapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner2.setAdapter(adapter2)
                    spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                            choice_major = adapter2!!.getItem(i).toString()
                        }

                        override fun onNothingSelected(adapterView: AdapterView<*>?) {
                            Toast.makeText(this@SettingActivity, "소속학과를 선택해주세요!", Toast.LENGTH_SHORT).show()

                        }
                    })
                } else if (adapter1.getItem(i) == "공과대학") {
                    choice_uni = "공과대학"
                    adapter2 = ArrayAdapter.createFromResource(this@SettingActivity, R.array.arr_gong, android.R.layout.simple_spinner_dropdown_item)
                    adapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner2.setAdapter(adapter2)
                    spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                            choice_major = adapter2!!.getItem(i).toString()
                        }

                        override fun onNothingSelected(adapterView: AdapterView<*>?) {
                            Toast.makeText(this@SettingActivity, "소속학과를 선택해주세요!", Toast.LENGTH_SHORT).show()

                        }
                    })
                } else if (adapter1.getItem(i) == "정보기술대학") {
                    choice_uni = "정보기술대학"
                    adapter2 = ArrayAdapter.createFromResource(this@SettingActivity, R.array.arr_jeongbo, android.R.layout.simple_spinner_dropdown_item)
                    adapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner2.setAdapter(adapter2)
                    spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                            choice_major = adapter2!!.getItem(i).toString()
                        }

                        override fun onNothingSelected(adapterView: AdapterView<*>?) {
                            Toast.makeText(this@SettingActivity, "소속학과를 선택해주세요!", Toast.LENGTH_SHORT).show()

                        }
                    })
                } else if (adapter1.getItem(i) == "경영대학") {
                    choice_uni = "경영대학"
                    adapter2 = ArrayAdapter.createFromResource(this@SettingActivity, R.array.arr_gyeongyeong, android.R.layout.simple_spinner_dropdown_item)
                    adapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner2.setAdapter(adapter2)
                    spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                            choice_major = adapter2!!.getItem(i).toString()
                        }

                        override fun onNothingSelected(adapterView: AdapterView<*>?) {
                            Toast.makeText(this@SettingActivity, "소속학과를 선택해주세요!", Toast.LENGTH_SHORT).show()

                        }
                    })
                } else if (adapter1.getItem(i) == "예술체육대학") {
                    choice_uni = "예술체육대학"
                    adapter2 = ArrayAdapter.createFromResource(this@SettingActivity, R.array.arr_yesul, android.R.layout.simple_spinner_dropdown_item)
                    adapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner2.setAdapter(adapter2)
                    spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                            choice_major = adapter2!!.getItem(i).toString()
                        }

                        override fun onNothingSelected(adapterView: AdapterView<*>?) {
                            Toast.makeText(this@SettingActivity, "소속학과를 선택해주세요!", Toast.LENGTH_SHORT).show()

                        }
                    })
                } else if (adapter1.getItem(i) == "사범대학") {
                    choice_uni = "사범대학"
                    adapter2 = ArrayAdapter.createFromResource(this@SettingActivity, R.array.arr_sabeom, android.R.layout.simple_spinner_dropdown_item)
                    adapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner2.setAdapter(adapter2)
                    spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                            choice_major = adapter2!!.getItem(i).toString()
                        }

                        override fun onNothingSelected(adapterView: AdapterView<*>?) {
                            Toast.makeText(this@SettingActivity, "소속학과를 선택해주세요!", Toast.LENGTH_SHORT).show()

                        }
                    })
                } else if (adapter1.getItem(i) == "도시과학대학") {
                    choice_uni = "도시과학대학"
                    adapter2 = ArrayAdapter.createFromResource(this@SettingActivity, R.array.arr_dosi, android.R.layout.simple_spinner_dropdown_item)
                    adapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner2.setAdapter(adapter2)
                    spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                            choice_major = adapter2!!.getItem(i).toString()
                        }

                        override fun onNothingSelected(adapterView: AdapterView<*>?) {
                            Toast.makeText(this@SettingActivity, "소속학과를 선택해주세요!", Toast.LENGTH_SHORT).show()

                        }
                    })
                } else if (adapter1.getItem(i) == "생명과학기술대학") {
                    choice_uni = "생명과학기술대학"
                    adapter2 = ArrayAdapter.createFromResource(this@SettingActivity, R.array.arr_saengmyeong, android.R.layout.simple_spinner_dropdown_item)
                    adapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner2.setAdapter(adapter2)
                    spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                            choice_major = adapter2!!.getItem(i).toString()
                        }

                        override fun onNothingSelected(adapterView: AdapterView<*>?) {
                            Toast.makeText(this@SettingActivity, "소속학과를 선택해주세요!", Toast.LENGTH_SHORT).show()

                        }
                    })
                } else if (adapter1.getItem(i) == "동북아국제통상대학") {
                    choice_uni = "동북아국제통상대학"
                    adapter2 = ArrayAdapter.createFromResource(this@SettingActivity, R.array.arr_dongbuga, android.R.layout.simple_spinner_dropdown_item)
                    adapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner2.setAdapter(adapter2)
                    spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                            choice_major = adapter2!!.getItem(i).toString()
                        }

                        override fun onNothingSelected(adapterView: AdapterView<*>?) {
                            Toast.makeText(this@SettingActivity, "소속학과를 선택해주세요!", Toast.LENGTH_SHORT).show()

                        }
                    })
                } else if (adapter1.getItem(i) == "법학부") {
                    choice_uni = "법학부"
                    adapter2 = ArrayAdapter.createFromResource(this@SettingActivity, R.array.arr_beob, android.R.layout.simple_spinner_dropdown_item)
                    adapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner2.setAdapter(adapter2)
                    spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                            choice_major = adapter2!!.getItem(i).toString()
                        }

                        override fun onNothingSelected(adapterView: AdapterView<*>?) {
                            Toast.makeText(this@SettingActivity, "소속학과를 선택해주세요!", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}