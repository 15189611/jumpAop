package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R


/**
 * Author: Charles.pan
 * Version V1.0
 * Date: 2020/7/15
 * Description:
 * Modification History:
 * Date Author Version Description
 * -----------------------------------------------------------------------------------
 * 2020/7/15 Charles.pan 1.0
 * Why & What is modified:
 */

class MyAdapter(private val context: Context, private val data: List<String>) :
    RecyclerView.Adapter<MyAdapter.SimpleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_recvcler, parent, false)
        return SimpleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.textView.text = data[position]
    }

    inner class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.item_tv)
    }
}


