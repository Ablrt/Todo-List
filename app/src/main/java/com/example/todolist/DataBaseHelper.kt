package com.example.todolist

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, "todo_list.db", null, 1) {
    private val todoTable = "TODO_TABLE"
    private val task = "TASK"
    private val isActive = "IS_ACTIVE"

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement = "CREATE TABLE $todoTable (id INTEGER PRIMARY KEY AUTOINCREMENT, $task TEXT, $isActive BOOL)"
        db?.execSQL(createTableStatement)
    }

    fun addOne(todo:Todo):Long{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(task, todo.title)
        cv.put(isActive, todo.isChecked)
        val len = db.insert(todoTable,null,cv)
        db.close()
        return len
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}