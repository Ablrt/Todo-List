package com.example.todolist

data class Todo(
  val id: Int =-1,
  val title: String,
  var isChecked: Boolean = false
)
