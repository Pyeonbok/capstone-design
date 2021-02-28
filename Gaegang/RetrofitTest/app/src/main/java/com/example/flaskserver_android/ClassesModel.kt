package com.example.flaskserver_android

data class Test(val class1:Recommended,
                val class2:Recommended,
                val class3:Recommended){

}

data class Recommended(
    var lecture: String,
    var professor: String,
    var classification: String,
    var credit: String,
    var teaching_method: String,
    var week: String, var time: String
)
