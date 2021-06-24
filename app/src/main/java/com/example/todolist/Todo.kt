package com.example.todolist

data class Todo(
  var id: Int =-1,
  val title: String,
  var isChecked: Boolean = false
)
