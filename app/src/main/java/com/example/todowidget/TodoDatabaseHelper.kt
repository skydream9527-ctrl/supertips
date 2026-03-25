package com.example.todowidget

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TodoDatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    
    companion object {
        private const val DATABASE_NAME = "todo.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "todos"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_IS_COMPLETED = "is_completed"
        private const val COLUMN_CREATED_AT = "created_at"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITLE TEXT NOT NULL,
                $COLUMN_IS_COMPLETED INTEGER DEFAULT 0,
                $COLUMN_CREATED_AT INTEGER NOT NULL
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertTodo(todo: Todo): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, todo.title)
            put(COLUMN_IS_COMPLETED, if (todo.isCompleted) 1 else 0)
            put(COLUMN_CREATED_AT, todo.createdAt)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllTodos(): List<Todo> {
        val todos = mutableListOf<Todo>()
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_CREATED_AT DESC"
        )
        with(cursor) {
            while (moveToNext()) {
                val todo = Todo(
                    id = getLong(getColumnIndexOrThrow(COLUMN_ID)),
                    title = getString(getColumnIndexOrThrow(COLUMN_TITLE)),
                    isCompleted = getInt(getColumnIndexOrThrow(COLUMN_IS_COMPLETED)) == 1,
                    createdAt = getLong(getColumnIndexOrThrow(COLUMN_CREATED_AT))
                )
                todos.add(todo)
            }
        }
        cursor.close()
        return todos
    }

    fun updateTodo(todo: Todo): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, todo.title)
            put(COLUMN_IS_COMPLETED, if (todo.isCompleted) 1 else 0)
            put(COLUMN_CREATED_AT, todo.createdAt)
        }
        return db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(todo.id.toString()))
    }

    fun deleteTodo(id: Long): Int {
        val db = writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    fun getUncompletedTodos(): List<Todo> {
        val todos = mutableListOf<Todo>()
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            null,
            "$COLUMN_IS_COMPLETED = ?",
            arrayOf("0"),
            null,
            null,
            "$COLUMN_CREATED_AT DESC"
        )
        with(cursor) {
            while (moveToNext()) {
                val todo = Todo(
                    id = getLong(getColumnIndexOrThrow(COLUMN_ID)),
                    title = getString(getColumnIndexOrThrow(COLUMN_TITLE)),
                    isCompleted = getInt(getColumnIndexOrThrow(COLUMN_IS_COMPLETED)) == 1,
                    createdAt = getLong(getColumnIndexOrThrow(COLUMN_CREATED_AT))
                )
                todos.add(todo)
            }
        }
        cursor.close()
        return todos
    }
}