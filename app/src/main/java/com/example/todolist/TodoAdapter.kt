package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task.view.*


class TodoAdapter(private val tasks: MutableList<Todo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    class TodoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    fun add(task: Todo){
        tasks.add(task)
        notifyItemInserted(tasks.size-1)
    }

    fun remove(){
        tasks.removeAll{ task -> task.isChecked}
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.task, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTask = tasks[position]
        holder.itemView.apply {
            tvTask.text = curTask.title
            cbDone.isChecked = curTask.isChecked
            toggleStrikeThrough(tvTask, cbDone.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTask, isChecked)
                curTask.isChecked = !curTask.isChecked
            }
        }
    }

    private fun toggleStrikeThrough(tvTask: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTask.paintFlags = tvTask.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTask.paintFlags = tvTask.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}