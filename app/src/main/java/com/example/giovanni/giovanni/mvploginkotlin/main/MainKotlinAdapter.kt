package com.example.giovanni.giovanni.mvploginkotlin.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.giovanni.giovanni.R

class MainKotlinAdapter(private val items: List<String>, private val listener: (String) -> Unit) : RecyclerView.Adapter<MainKotlinAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.textview_item, parent, false) as TextView

        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item
        holder.textView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int = items.size

    class MainViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {}
}