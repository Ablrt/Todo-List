package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_main.*

class InputDialog(var todoAdapter: TodoAdapter): DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.task_input, container, false)
        var taskInput = rootView.findViewById<EditText>(R.id.etInput)
        var cancelButton = rootView.findViewById<Button>(R.id.btnCancel)
            cancelButton.setOnClickListener{
                dismiss()
            }
        var addButton = rootView.findViewById<Button>(R.id.btnAdd)
            addButton.setOnClickListener{
                val title = taskInput.text.toString()
                if(!title.isEmpty()){
                    val task = Todo(title)
                    todoAdapter.add(task)
                    dismiss()
                }
            }

        return rootView
    }
}