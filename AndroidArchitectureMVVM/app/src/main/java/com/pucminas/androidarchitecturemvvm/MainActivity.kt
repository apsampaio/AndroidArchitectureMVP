package com.pucminas.androidarchitecturemvvm

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TaskAdapter
    private val taskViewModel: TaskViewModel = TaskViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = TaskAdapter(this, taskViewModel.tasks.value?: listOf())

        val tasksList = findViewById<ListView>(R.id.taskList)
        tasksList.adapter = adapter

        taskViewModel.tasks.observe(this) {
            adapter.notifyDataSetChanged()
        }

        tasksList.setOnItemLongClickListener{ _, _, position, _ ->
            taskViewModel.deleteTask(position)
            true
        }

        tasksList.setOnItemClickListener { _, _, position, _ ->
            taskViewModel.updateTask(position)
        }

        findViewById<Button>(R.id.addTaskButton).setOnClickListener {
            val newTaskModel = TaskModel("Nova Tarefa", "Description", false)
            taskViewModel.addTask(newTaskModel)
        }


    }
}