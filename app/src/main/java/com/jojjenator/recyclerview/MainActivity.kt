package com.jojjenator.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                customAdapter.getFilter().filter(p0)
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
        itemsList.add("Apples")
        itemsList.add("Johan")
        itemsList.add("Peter")
        itemsList.add("Adam")
        itemsList.add("Andreas")
        itemsList.add("Oskar")
        itemsList.add("Jakob")
        itemsList.add("Gabriel")
        itemsList.add("Tjena")

        customAdapter.notifyDataSetChanged()
    }
}