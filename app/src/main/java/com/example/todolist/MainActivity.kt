package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(DataBaseHelper(this))

        rvTasks.adapter = todoAdapter
        rvTasks.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener{
            val inputDialog = InputDialog(todoAdapter)
            inputDialog.show(supportFragmentManager, "Task Input")
        }

        removeButton.setOnClickListener{
            todoAdapter.remove()
        }
    }
}