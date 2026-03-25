package com.example.todowidget

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    
    private lateinit var databaseHelper: TodoDatabaseHelper
    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos
    
    private val scope = CoroutineScope(Dispatchers.Main)
    
    companion object {
        const val ACTION_TODO_CHANGED = "com.example.todowidget.TODO_CHANGED"
    }

    fun init(context: Context) {
        databaseHelper = TodoDatabaseHelper(context.applicationContext)
        loadTodos()
    }

    fun loadTodos() {
        scope.launch(Dispatchers.IO) {
            val todoList = databaseHelper.getAllTodos()
            _todos.postValue(todoList)
        }
    }

    fun addTodo(title: String, context: Context) {
        scope.launch(Dispatchers.IO) {
            val todo = Todo(title = title)
            databaseHelper.insertTodo(todo)
            loadTodos()
            notifyWidgetUpdate(context)
        }
    }

    fun updateTodo(todo: Todo, context: Context) {
        scope.launch(Dispatchers.IO) {
            databaseHelper.updateTodo(todo)
            loadTodos()
            notifyWidgetUpdate(context)
        }
    }

    fun deleteTodo(id: Long, context: Context) {
        scope.launch(Dispatchers.IO) {
            databaseHelper.deleteTodo(id)
            loadTodos()
            notifyWidgetUpdate(context)
        }
    }

    fun completeTodo(todo: Todo, context: Context) {
        scope.launch(Dispatchers.IO) {
            val completedTodo = todo.copy(isCompleted = true)
            databaseHelper.updateTodo(completedTodo)
            loadTodos()
            notifyWidgetUpdate(context)
        }
    }

    private fun notifyWidgetUpdate(context: Context) {
        val intent = Intent(ACTION_TODO_CHANGED)
        context.sendBroadcast(intent)
    }
}