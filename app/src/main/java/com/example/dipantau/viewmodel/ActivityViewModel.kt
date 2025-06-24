package com.example.dipantau.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dipantau.data.repositories.ActivityRepository
import com.example.dipantau.model.Activity
import com.example.dipantau.model.ActivityDetail
import com.example.dipantau.model.ActivityRequest
import com.example.dipantau.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val activityRepository: ActivityRepository
) : ViewModel() {

    private val _activityList = MutableStateFlow<Resource<List<Activity>>>(Resource.Loading)
    val activityList: StateFlow<Resource<List<Activity>>> = _activityList

    private val _activityDetail = MutableStateFlow<Resource<ActivityDetail>>(Resource.Loading) // Perbaikan di sini
    val activityDetail: StateFlow<Resource<ActivityDetail>> = _activityDetail // Perbaikan di sini

    private val _createActivityResult = MutableStateFlow<Resource<Activity>?>(null)
    val createActivityResult: StateFlow<Resource<Activity>?> = _createActivityResult

    fun getAllActivities() {
        viewModelScope.launch {
            _activityList.value = Resource.Loading
            try {
                val result = activityRepository.getAllActivities()
                _activityList.value = result
            } catch (e: Exception) {
                _activityList.value = Resource.Error(e.message ?: "Error fetching activities")
            }
        }
    }

    fun getActivityById(id: Int) {
        viewModelScope.launch {
            _activityDetail.value = Resource.Loading
            try {
                val result = activityRepository.getActivityById(id)
                _activityDetail.value = result
            } catch (e: Exception) {
                _activityDetail.value = Resource.Error(e.message ?: "Error fetching activity")
            }
        }
    }

    fun createActivity(activityRequest: ActivityRequest) {
        viewModelScope.launch {
            _createActivityResult.value = Resource.Loading
            try {
                val result = activityRepository.createActivity(activityRequest)
                _createActivityResult.value = result
            } catch (e: Exception) {
                _createActivityResult.value = Resource.Error(e.message ?: "Error creating activity")
            }
        }
    }

    fun resetCreateResult() {
        _createActivityResult.value = null
    }
}
