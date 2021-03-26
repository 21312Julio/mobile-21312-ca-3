package com.example.listcashopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: AdapterItem

    private fun saveData(todoAdapter : AdapterItem) {
        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val editor = sharedPref.edit()
        val jsonString = Gson().toJson(todoAdapter)
        editor.putString("todoAdapter", jsonString).apply()
        editor.commit()
    }

    private fun loadData() {
        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val jsonString = sharedPref.getString("todoAdapter", null)
        val type = object : TypeToken<MutableList<Todos>>() {}.type
        todoAdapter = Gson().fromJson(jsonString, type)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = AdapterItem(mutableListOf())
        rvListItem.adapter = todoAdapter

        loadData()
        rvListItem.layoutManager = LinearLayoutManager(this)

        btnAddItem.setOnClickListener{
            val todoTitle = etItemName.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todos(todoTitle)
                todoAdapter.addItem(todo)
                etItemName.text.clear()
            }
        }

        btnRemoveItem.setOnClickListener{
            todoAdapter.removeItem()
        }

        ibSave.setOnClickListener {
           saveData(todoAdapter)
        }

    }
}