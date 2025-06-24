package com.example.dipantau.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dipantau.data.repositories.TaskRepository
import com.example.dipantau.model.Task
import com.example.dipantau.model.TaskRequest
import com.example.dipantau.model.UpdateTaskRequest
import com.example.dipantau.model.PagedResponse
import com.example.dipantau.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _taskList = MutableStateFlow<Resource<PagedResponse<Task>>>(Resource.Loading)
    val taskList: StateFlow<Resource<PagedResponse<Task>>> = _taskList

    private val _himpunanTasks = MutableStateFlow<Resource<PagedResponse<Task>>>(Resource.Loading)
    val himpunanTasks: StateFlow<Resource<PagedResponse<Task>>> = _himpunanTasks

    private val _userTasks = MutableStateFlow<Resource<List<Task>>>(Resource.Loading)
    val userTasks: StateFlow<Resource<List<Task>>> = _userTasks

    private val _taskDetail = MutableStateFlow<Resource<Task>>(Resource.Loading)
    val taskDetail: StateFlow<Resource<Task>> = _taskDetail

    private val _createTaskResult = MutableStateFlow<Resource<Task>?>(null)
    val createTaskResult: StateFlow<Resource<Task>?> = _createTaskResult

    private val _updateTaskResult = MutableStateFlow<Resource<Task>?>(null)
    val updateTaskResult: StateFlow<Resource<Task>?> = _updateTaskResult

    private val _deleteTaskResult = MutableStateFlow<Resource<Unit>?>(null)
    val deleteTaskResult: StateFlow<Resource<Unit>?> = _deleteTaskResult

    fun getAllTasks(
        page: Int = 1,
        limit: Int = 10,
        himpunanId: Int? = null,
        status: String? = null,
        priority: String? = null
    ) {
        viewModelScope.launch {
            _taskList.value = Resource.Loading
            try {
                val result = taskRepository.getAllTasks(page, limit, himpunanId, status, priority)
                _taskList.value = result
            } catch (e: Exception) {
                _taskList.value = Resource.Error(e.message ?: "Terjadi kesalahan saat mengambil data tasks")
            }
        }
    }

    fun getHimpunanTasks(himpunanId: Int, page: Int = 1, limit: Int = 10) {
        viewModelScope.launch {
            _himpunanTasks.value = Resource.Loading
            try {
                val result = taskRepository.getHimpunanTasks(himpunanId, page, limit)
                _himpunanTasks.value = result
            } catch (e: Exception) {
                _himpunanTasks.value = Resource.Error(e.message ?: "Terjadi kesalahan saat mengambil tasks himpunan")
            }
        }
    }

    fun getCurrentUserTasks() {
        viewModelScope.launch {
            _userTasks.value = Resource.Loading
            try {
                val result = taskRepository.getCurrentUserTasks()
                _userTasks.value = result
            } catch (e: Exception) {
                _userTasks.value = Resource.Error(e.message ?: "Terjadi kesalahan saat mengambil tasks user")
            }
        }
    }

    fun getUserTasks(userId: Int) {
        viewModelScope.launch {
            _userTasks.value = Resource.Loading
            try {
                val result = taskRepository.getUserTasks(userId)
                _userTasks.value = result
            } catch (e: Exception) {
                _userTasks.value = Resource.Error(e.message ?: "Terjadi kesalahan saat mengambil tasks user")
            }
        }
    }

    fun getTaskById(id: Int) {
        viewModelScope.launch {
            _taskDetail.value = Resource.Loading
            try {
                val result = taskRepository.getTaskById(id)
                _taskDetail.value = result
            } catch (e: Exception) {
                _taskDetail.value = Resource.Error(e.message ?: "Terjadi kesalahan saat mengambil detail task")
            }
        }
    }

    fun createTask(
        title: String,
        description: String?,
        priority: String,
        dueDate: String?,
        startDate: String?,
        scoreReward: Int,
        category: String?,
        tags: List<String>?,
        himpunanId: Int,
        assignedToId: Int?
    ) {
        viewModelScope.launch {
            _createTaskResult.value = Resource.Loading
            try {
                val request = TaskRequest(
                    title = title,
                    description = description,
                    priority = priority,
                    dueDate = dueDate,
                    startDate = startDate,
                    scoreReward = scoreReward,
                    category = category,
                    tags = tags,
                    himpunanId = himpunanId,
                    assignedToId = assignedToId
                )
                val result = taskRepository.createTask(request)
                _createTaskResult.value = result

                // Refresh task list if successful
                if (result is Resource.Success) {
                    getHimpunanTasks(himpunanId)
                }
            } catch (e: Exception) {
                _createTaskResult.value = Resource.Error(e.message ?: "Gagal membuat task")
            }
        }
    }

    fun updateTask(
        id: Int,
        title: String? = null,
        description: String? = null,
        status: String? = null,
        priority: String? = null,
        dueDate: String? = null,
        startDate: String? = null,
        scoreReward: Int? = null,
        category: String? = null,
        tags: List<String>? = null,
        assignedToId: Int? = null
    ) {
        viewModelScope.launch {
            _updateTaskResult.value = Resource.Loading
            try {
                val request = UpdateTaskRequest(
                    title = title,
                    description = description,
                    status = status,
                    priority = priority,
                    dueDate = dueDate,
                    startDate = startDate,
                    scoreReward = scoreReward,
                    category = category,
                    tags = tags,
                    assignedToId = assignedToId
                )
                val result = taskRepository.updateTask(id, request)
                _updateTaskResult.value = result

                // Refresh task list and detail if successful
                if (result is Resource.Success) {
                    getTaskById(id)
                }
            } catch (e: Exception) {
                _updateTaskResult.value = Resource.Error(e.message ?: "Gagal memperbarui task")
            }
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            _deleteTaskResult.value = Resource.Loading
            try {
                val result = taskRepository.deleteTask(id)
                _deleteTaskResult.value = result
            } catch (e: Exception) {
                _deleteTaskResult.value = Resource.Error(e.message ?: "Gagal menghapus task")
            }
        }
    }

    // Reset functions
    fun resetCreateResult() {
        _createTaskResult.value = null
    }

    fun resetUpdateResult() {
        _updateTaskResult.value = null
    }

    fun resetDeleteResult() {
        _deleteTaskResult.value = null
    }
}