package com.pucminas.myapplication

class TaskController {

    private val tasks = mutableListOf<TaskModel>()

    fun getTasks(): List<TaskModel> {
        return tasks
    }

    fun addTasks(task: TaskModel) {
        task.id = tasks.size
        tasks.add(task)
    }

    fun updateTask(position: Int) {
        tasks[position].isCompleted = !tasks[position].isCompleted
    }

    fun deleteTask(position: Int) {
        tasks.remove(tasks[position])
    }
}