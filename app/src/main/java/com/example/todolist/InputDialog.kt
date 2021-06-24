package com.example.todolist


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment


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

        taskInput.showKeyboard()

        var addButton = rootView.findViewById<Button>(R.id.btnAdd)
            addButton.setOnClickListener{
                val title = taskInput.text.toString()
                if(!title.isEmpty()){
                    val task = Todo(title = title)
                    todoAdapter.add(task)
                    taskInput.showKeyboard()
                    dismiss()
                }
            }
        return rootView
    }

    private fun View.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}