package com.example.leandemo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.leancloud.LCObject

class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _dataList = listOf<LCObject>()

    fun updateDataList(newList : List<LCObject>) {
        _dataList = newList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_holder, parent, false)
        return object : RecyclerView.ViewHolder(v){}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ob = _dataList[position]
        holder.itemView.findViewById<TextView>(R.id.textViewContent).text = ob.get("word").toString()
        holder.itemView.findViewById<TextView>(R.id.textViewTime).text = ob.get("createdAt").toString()

    }

    override fun getItemCount(): Int {
        return _dataList.size
    }
}