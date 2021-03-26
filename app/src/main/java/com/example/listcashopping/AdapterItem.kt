package com.example.listcashopping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.check_todo.*
import kotlinx.android.synthetic.main.check_todo.view.*


class AdapterItem(
        private val todos: MutableList<Todos>
) : RecyclerView.Adapter<AdapterItem.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.check_todo,
                        parent,
                        false
                )
        )
    }

    fun addItem(todo: Todos) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun removeItem() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.itemView.apply {
            tvTitles.text = currentTodo.title
            cbComplete.isChecked = currentTodo.isChecked
            cbComplete.setOnCheckedChangeListener{ _, isChecked ->
                currentTodo.isChecked = !currentTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}