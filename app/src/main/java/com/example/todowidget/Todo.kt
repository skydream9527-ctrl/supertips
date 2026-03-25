package com.example.todowidget

import java.io.Serializable

data class Todo(
    val id: Long = 0,
    val title: String,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) : Serializable