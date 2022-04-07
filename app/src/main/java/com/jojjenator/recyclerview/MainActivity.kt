package com.jojjenator.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val itemsList = ArrayList<String>()
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // X()

        val searchView = findViewById<androidx.appcompat.widget.SearchView>(R.id.sv_searchbar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        customAdapter = CustomAdapter(itemsList)
        val layoutManager = LinearLayoutManager(applicationContext)

        searchView.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                customAdapter.getFilter().filter(newText)
                return false
            }
        })

        // NEW - Interface (on Item Click)
        customAdapter.setOnItemClickListener(object: CustomAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {

                // CRASHES if it's -1
                if (position >= 0) {
                    itemsList.removeAt(position)
                    customAdapter.notifyItemRemoved(position)
                }
            }
        })

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter
        prepareItems()
    }

    private fun prepareItems(){
        for (index in 0..20){
            itemsList.add("Apples$index")
        }
        customAdapter.notifyDataSetChanged()
    }
}