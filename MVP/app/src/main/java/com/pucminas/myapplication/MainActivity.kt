package com.pucminas.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pucminas.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskContract.View {

    private lateinit var presenter: TaskContract.Presenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        presenter = TaskPresenter(this)
        binding.addTaskButton.setOnClickListener {
            // adiciona nova tarefa

            val newTask = TaskModel("Nova Tarefa", "Descricao Task", isCompleted = false)
            presenter.addTask(newTask)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun showTasks(tasks: List<TaskModel>) {
        val adapter = TaskAdapter(this, tasks)
        binding.taskList.adapter = adapter

        binding.taskList.setOnItemLongClickListener { parent, view , position, id ->
            // Executa o delete
            presenter.deleteTask(tasks[position])
            true
        }

        binding.taskList.setOnItemClickListener { parent , view, position, id ->
            /// executa atualizacao do isCompleted (checked)
            presenter.updateTask(position)
        }
    }
}