package com.pucminas.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView

class TaskAdapter(
    private val context: Context,
    private val tasks: List<TaskModel>,
) : BaseAdapter() {
    override fun getCount(): Int {
        return tasks.size
    }

    override fun getItem(position: Int): Any {
        return tasks[position]
    }

    override fun getItemId(position: Int): Long {
        return tasks[position].id?.toLong() ?: 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_task, parent, false)
        val titleTextView = view.findViewById<TextView>(R.id.titleTask)
        val descriptionTextView = view.findViewById<TextView>(R.id.descriptionTask)
        val checkBox = view.findViewById<CheckBox>(R.id.checkbox)

        val task = tasks[position]
        titleTextView.text = task.title
        descriptionTextView.text = task.description
        checkBox.isChecked = task.isCompleted

        return view
    }
}