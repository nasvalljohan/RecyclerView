package com.jojjenator.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private val itemsList =  ArrayList<String>()
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView) //vår view
        customAdapter = CustomAdapter(itemsList)
        val layoutManager = LinearLayoutManager(applicationContext)

        // NEW - Interface (on Item Click)
        customAdapter.setOnItemClickListener(object: CustomAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                itemsList.removeAt(position)
                customAdapter.notifyItemRemoved(position)
            }
        })


        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter
        prepareItems()

    }

    private fun prepareItems(){
        for (index in 0..10){
            itemsList.add("Apples$index")
        }
        customAdapter.notifyDataSetChanged()
    }
}