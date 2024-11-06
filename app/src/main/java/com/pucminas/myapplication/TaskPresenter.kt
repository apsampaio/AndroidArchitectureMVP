package com.pucminas.myapplication

class TaskPresenter(
    private val view: TaskContract.View,
) : TaskContract.Presenter {
    private val tasks = mutableListOf<TaskModel>()

    init {
        tasks.add(
            TaskModel("Tarefa 1", "Tarefa 1", isCompleted = false)
        )
        tasks.add(
            TaskModel("Tarefa 2", "Tarefa 2", isCompleted = true)
        )
    }

    override fun getTasks() {
        view.showTasks(tasks)
    }

    override fun addTask(task: TaskModel) {
        task.id = tasks.size
        tasks.add(task)
        view.showTasks(tasks)
    }

    override fun updateTask(position: Int) {
        tasks[position].isCompleted = !tasks[position].isCompleted
        view.showTasks(tasks)
    }

    override fun deleteTask(task: TaskModel) {
        tasks.remove(task)
        view.showTasks(tasks)
    }

}