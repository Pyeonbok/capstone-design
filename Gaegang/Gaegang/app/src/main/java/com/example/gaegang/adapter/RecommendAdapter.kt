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

    /* 라벨, 강의이름, 교수이름, 이수구분, 수업방법, 요일, 시간 */
    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val label = itemView?.findViewById<TextView>(R.id.text_label)
        val lecture = itemView?.findViewById<TextView>(R.id.text_lecture)
        val professor = itemView?.findViewById<TextView>(R.id.text_professor)
        val classification = itemView?.findViewById<TextView>(R.id.text_classification)
        val teaching_method = itemView?.findViewById<TextView>(R.id.text_teaching_method)
        val week = itemView?.findViewById<TextView>(R.id.text_week)
        val time = itemView?.findViewById<TextView>(R.id.text_time)

        fun bind(rec: Recommended, context: Context) {
            label?.text = rec.label
            lecture?.text = rec.lecture
            professor?.text = rec.professor
            classification?.text = rec.classification
            teaching_method?.text = rec.teaching_method
            week?.text = rec.week
            time?.text = rec.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.recommended_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(recList[position], context)
    }

    override fun getItemCount(): Int {
        return recList.size
    }
}
