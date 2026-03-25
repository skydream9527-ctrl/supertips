package com.example.todowidget

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    
    private lateinit var viewModel: TodoViewModel
    private lateinit var adapter: TodoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: TextView
    private lateinit var fabAdd: FloatingActionButton
    
    private val todoChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            viewModel.loadTodos()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        viewModel.init(this)
        
        initViews()
        setupRecyclerView()
        observeData()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        emptyView = findViewById(R.id.emptyView)
        fabAdd = findViewById(R.id.fabAdd)
        
        fabAdd.setOnClickListener {
            showAddTodoDialog()
        }
    }

    private fun setupRecyclerView() {
        adapter = TodoAdapter(
            onItemClickListener = { todo ->
                
            },
            onItemCompleteListener = { todo ->
                viewModel.completeTodo(todo, this)
            },
            onItemDeleteListener = { id ->
                viewModel.deleteTodo(id, this)
            }
        )
        
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        
        val swipeCallback = SwipeCallback(
            adapter = adapter,
            onComplete = { todo ->
                viewModel.completeTodo(todo, this)
            },
            onDelete = { id ->
                viewModel.deleteTodo(id, this)
            }
        )
        
        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun observeData() {
        viewModel.todos.observe(this) { todos ->
            adapter.submitList(todos)
            emptyView.visibility = if (todos.isEmpty()) View.VISIBLE else View.GONE
            recyclerView.visibility = if (todos.isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun showAddTodoDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.add_todo)
        
        val input = EditText(this)
        input.hint = getString(R.string.hint_todo)
        builder.setView(input)
        
        builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
            val title = input.text.toString().trim()
            if (title.isNotEmpty()) {
                viewModel.addTodo(title, this)
            }
            dialog.dismiss()
        }
        
        builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.cancel()
        }
        
        builder.show()
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(TodoViewModel.ACTION_TODO_CHANGED)
        registerReceiver(todoChangeReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(todoChangeReceiver)
    }
}