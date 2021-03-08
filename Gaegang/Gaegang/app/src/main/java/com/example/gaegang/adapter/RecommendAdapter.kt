package com.example.gaegang.adapter

import android.content.Context
import android.system.Os.bind
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gaegang.R
import com.example.gaegang.dataClass.Recommended

class RecommendAdapter(val context: Context, val recList : ArrayList<Recommended>) :
RecyclerView.Adapter<RecommendAdapter.Holder>() {


    /*
    * 강의명 / 교수명
    * 이수구분 / 학점
    * 수업방법 (온라인/오프라인)
    * 시간 / 강의실
    */
    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val lecture = itemView?.findViewById<TextView>(R.id.text_lecture)
        val professor = itemView?.findViewById<TextView>(R.id.text_professor)
        val classification = itemView?.findViewById<TextView>(R.id.text_classification)
        val credit = itemView?.findViewById<TextView>(R.id.text_credit)
        val teaching_method = itemView?.findViewById<TextView>(R.id.text_teaching_method)
        val time = itemView?.findViewById<TextView>(R.id.text_time)
        val classroom = itemView?.findViewById<TextView>(R.id.text_classroom)

        fun bind(rec: Recommended, context: Context) {
            lecture?.text = rec.lecture
            professor?.text = rec.professor
            classification?.text = rec.classification
            credit?.text=rec.credit
            teaching_method?.text = rec.teaching_method
            time?.text = rec.time
            classroom?.text=rec.classroom
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.recommended_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(recList[position], context)
    }

    override fun getItemCount(): Int {
        return recList.size
    }
}
