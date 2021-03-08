package com.example.gaegang.dataClass

/*
   * 강의명 / 교수명
   * 이수구분 / 학점
   * 수업방법 (온라인/오프라인)
   * 시간 / 강의실
   */

data class Recommended (
    var lecture:String, var professor:String,
    var classification:String, var credit:String,
    var teaching_method:String,
    var time:String, var classroom:String)
