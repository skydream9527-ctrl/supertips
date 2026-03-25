package com.example.todowidget

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class SwipeCallback(
    private val adapter: TodoAdapter,
    private val onComplete: (Todo) -> Unit,
    private val onDelete: (Long) -> Unit
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val todo = adapter.getTodoAt(position)
        
        when (direction) {
            ItemTouchHelper.RIGHT -> {
                onComplete(todo)
            }
            ItemTouchHelper.LEFT -> {
                onDelete(todo.id)
            }
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        
        if (dX > 0) {
            val completeView = itemView.findViewById<View>(R.id.tvComplete)
            completeView?.let {
                it.translationX = -itemView.width + dX
            }
        } else if (dX < 0) {
            val deleteView = itemView.findViewById<View>(R.id.tvDelete)
            deleteView?.let {
                it.translationX = itemView.width + dX
            }
        }

        val foregroundView = itemView.findViewById<View>(R.id.foreground)
        foregroundView?.translationX = dX

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val alpha = 1 - abs(dX) / itemView.width
            itemView.alpha = alpha
            itemView.translationX = dX
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        val itemView = viewHolder.itemView
        itemView.alpha = 1.0f
        itemView.translationX = 0f
        
        val foregroundView = itemView.findViewById<View>(R.id.foreground)
        foregroundView?.translationX = 0f
        
        val completeView = itemView.findViewById<View>(R.id.tvComplete)
        completeView?.translationX = 0f
        
        val deleteView = itemView.findViewById<View>(R.id.tvDelete)
        deleteView?.translationX = 0f
    }
}