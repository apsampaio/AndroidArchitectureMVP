package com.pucminas.androidarchitecturemvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel: ViewModel() {

    private val _tasks: MutableLiveData<MutableList<TaskModel>> = MutableLiveData(mutableListOf())
    val tasks: LiveData<MutableList<TaskModel>> = _tasks
    fun addTask(task: TaskModel) {
        val currentTasks = _tasks.value ?: mutableListOf()
        currentTasks.add(task)
        _tasks.value = currentTasks
    }

    fun updateTask(position: Int) {
        val currentTasks = _tasks.value ?: mutableListOf()
        currentTasks[position].isCompleted = !currentTasks[position].isCompleted
        _tasks.value = currentTasks
    }

    fun deleteTask(position: Int) {
        val currentTasks = _tasks.value ?: mutableListOf()
        currentTasks.remove(currentTasks[position])
        _tasks.value = currentTasks
    }
}