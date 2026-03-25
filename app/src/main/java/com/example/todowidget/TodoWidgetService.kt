package com.example.todowidget

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService

class TodoWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return TodoRemoteViewsFactory(applicationContext)
    }
}

class TodoRemoteViewsFactory(private val context: Context) : RemoteViewsService.RemoteViewsFactory {
    
    private var todos: List<Todo> = emptyList()

    override fun onCreate() {}

    override fun onDataSetChanged() {
        val dbHelper = TodoDatabaseHelper(context)
        todos = dbHelper.getUncompletedTodos()
    }

    override fun onDestroy() {
        todos = emptyList()
    }

    override fun getCount(): Int = todos.size

    override fun getViewAt(position: Int): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_item)
        val todo = todos[position]
        views.setTextViewText(R.id.widget_item_text, todo.title)
        return views
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = todos[position].id

    override fun hasStableIds(): Boolean = true
}