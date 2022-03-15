package com.jojjenator.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


internal class CustomAdapter(private var itemsList: List<String>) : //kan vara annan arrayList
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>(){

    // Init - Extend interface
    private lateinit var mListener : OnItemClickListener

    // Create interface
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    // Method Definition
    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    internal inner class MyViewHolder (view: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(view){
        var itemTextView: TextView = view.findViewById(R.id.itemTextView) //kan vara annat ID

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item, parent, false)
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position] //kan vara annan arrayList
        holder.itemTextView.text = item //kan vara annat ID
    }

    override fun getItemCount(): Int {
        return itemsList.size // kan vara annan arrayList
    }


}