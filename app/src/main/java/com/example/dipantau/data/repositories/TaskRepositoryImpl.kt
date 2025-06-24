package com.example.dipantau.data.repositories

import com.example.dipantau.data.remote.ApiService
import com.example.dipantau.model.Task
import com.example.dipantau.model.TaskRequest
import com.example.dipantau.model.UpdateTaskRequest
import com.example.dipantau.model.PagedResponse
import com.example.dipantau.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : BaseRepository(), TaskRepository {

    override suspend fun getAllTasks(
        page: Int,
        limit: Int,
        himpunanId: Int?,
        status: String?,
        priority: String?
    ): Resource<PagedResponse<Task>> = safeApiCall {
        apiService.getAllTasks(page, limit, himpunanId, status, priority)
    }

    override suspend fun getHimpunanTasks(
        himpunanId: Int,
        page: Int,
        limit: Int
    ): Resource<PagedResponse<Task>> = safeApiCall {
        apiService.getHimpunanTasks(himpunanId, page, limit)
    }

    override suspend fun getCurrentUserTasks(): Resource<List<Task>> = safeApiCallForList {
        apiService.getCurrentUserTasks()
    }

    override suspend fun getUserTasks(userId: Int): Resource<List<Task>> = safeApiCallForList {
        apiService.getUserTasks(userId)
    }

    override suspend fun getTaskById(id: Int): Resource<Task> = safeApiCall {
        apiService.getTaskById(id)
    }

    override suspend fun createTask(taskRequest: TaskRequest): Resource<Task> = safeApiCall {
        apiService.createTask(taskRequest)
    }

    override suspend fun updateTask(id: Int, updateRequest: UpdateTaskRequest): Resource<Task> = safeApiCall {
        apiService.updateTask(id, updateRequest)
    }

    override suspend fun deleteTask(id: Int): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.deleteTask(id)
                if (response.isSuccessful) {
                    Resource.Success(Unit)
                } else {
                    Resource.Error("Terjadi kesalahan: ${response.code()}")
                }
            } catch (e: Exception) {
                Resource.Error("Terjadi kesalahan: ${e.message}")
            }
        }
    }
}
