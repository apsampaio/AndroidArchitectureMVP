package com.pucminas.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TaskAdapter
    private val controller = TaskController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = TaskAdapter(this, controller.getTasks())

        val tasksList = findViewById<ListView>(R.id.taskList)
        tasksList.adapter = adapter


        tasksList.setOnItemLongClickListener { parent, view, position, id ->
            controller.deleteTask(position)
            adapter.notifyDataSetChanged()
            true
        }

        tasksList.setOnItemClickListener{ parent, view, position, id ->
            controller.updateTask(position)
            adapter.notifyDataSetChanged()
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            val newTask = TaskModel("Titulo de Tarefa", "Descricao", false)
            controller.addTasks(newTask)
            adapter.notifyDataSetChanged()
        }
    }
}