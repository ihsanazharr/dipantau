package com.example.dipantau.data.repositories

import com.example.dipantau.model.Task
import com.example.dipantau.model.TaskRequest
import com.example.dipantau.model.UpdateTaskRequest
import com.example.dipantau.model.PagedResponse
import com.example.dipantau.model.Resource

interface TaskRepository {
    suspend fun getAllTasks(
        page: Int = 1,
        limit: Int = 10,
        himpunanId: Int? = null,
        status: String? = null,
        priority: String? = null
    ): Resource<PagedResponse<Task>>

    suspend fun getHimpunanTasks(
        himpunanId: Int,
        page: Int = 1,
        limit: Int = 10
    ): Resource<PagedResponse<Task>>

    suspend fun getCurrentUserTasks(): Resource<List<Task>>

    suspend fun getUserTasks(userId: Int): Resource<List<Task>>

    suspend fun getTaskById(id: Int): Resource<Task>

    suspend fun createTask(taskRequest: TaskRequest): Resource<Task>

    suspend fun updateTask(id: Int, updateRequest: UpdateTaskRequest): Resource<Task>

    suspend fun deleteTask(id: Int): Resource<Unit>
}
