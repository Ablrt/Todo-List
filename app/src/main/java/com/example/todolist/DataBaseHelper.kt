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
        val createTableStatement = "CREATE TABLE $todoTable (id INTEGER PRIMARY KEY AUTOINCREMENT, $task TEXT NOT NULL, $isActive INTEGER NOT NULL)"
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

    fun viewAll():MutableList<Todo>{
        val tasks = mutableListOf<Todo>()
        val db = this.readableDatabase
        val SQLquery = "SELECT * FROM $todoTable"
        val cursor = db.rawQuery(SQLquery, null)
        if(cursor.moveToFirst()){
            do {
                val id = cursor.getInt(0)
                val title = cursor.getString(1)
                val isActive = cursor.getInt(2) == 1
                val task = Todo(id, title, isActive)
                tasks.add(task)
            }while(cursor.moveToNext())
        }
        db.close()
        cursor.close()
        return tasks
    }

    fun deleteOne(todo:Todo){
        val SQLquery = "DELETE FROM $todoTable WHERE id = ${todo.id}"
        val db = this.writableDatabase
        db.execSQL(SQLquery)
        db.close()

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}