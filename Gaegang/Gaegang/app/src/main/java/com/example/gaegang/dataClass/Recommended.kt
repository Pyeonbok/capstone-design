package com.example.gaegang.dataClass



class Recommended : ArrayList<RecommendedItem>()

/*
   * 강의명 / 교수명
   * 이수구분 / 학점
   * 수업방법 (온라인/오프라인)
   * 시간 / 강의실
   */

data class RecommendedItem (
    val lecture:String, val professor:String,
    val classification:String, val credit:String,
    val teaching_method:String,
    val time:String, val classroom:String){}
