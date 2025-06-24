package com.example.dipantau.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dipantau.data.repositories.AttendanceRepository
import com.example.dipantau.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val attendanceRepository: AttendanceRepository
) : ViewModel() {

    private val _attendanceDetail = MutableStateFlow<Resource<AttendanceDetail>>(Resource.Loading)
    val attendanceDetail: StateFlow<Resource<AttendanceDetail>> = _attendanceDetail

    private val _checkInResult = MutableStateFlow<Resource<Attendance>?>(null)
    val checkInResult: StateFlow<Resource<Attendance>?> = _checkInResult

    private val _checkOutResult = MutableStateFlow<Resource<Attendance>?>(null)
    val checkOutResult: StateFlow<Resource<Attendance>?> = _checkOutResult

    private val _updateAttendanceResult = MutableStateFlow<Resource<Attendance>?>(null)
    val updateAttendanceResult: StateFlow<Resource<Attendance>?> = _updateAttendanceResult

    fun checkInAttendance(activityId: Int, checkInRequest: CheckInRequest) {
        viewModelScope.launch {
            _checkInResult.value = Resource.Loading
            try {
                val result = attendanceRepository.checkInAttendance(activityId, checkInRequest)
                _checkInResult.value = result
            } catch (e: Exception) {
                _checkInResult.value = Resource.Error(e.message ?: "Error checking in")
            }
        }
    }

    fun checkOutAttendance(attendanceId: Int, checkOutRequest: CheckOutRequest) {
        viewModelScope.launch {
            _checkOutResult.value = Resource.Loading
            try {
                val result = attendanceRepository.checkOutAttendance(attendanceId, checkOutRequest)
                _checkOutResult.value = result
            } catch (e: Exception) {
                _checkOutResult.value = Resource.Error(e.message ?: "Error checking out")
            }
        }
    }

    fun updateAttendance(attendanceId: Int, updateAttendanceRequest: UpdateAttendanceRequest) {
        viewModelScope.launch {
            _updateAttendanceResult.value = Resource.Loading
            try {
                val result = attendanceRepository.updateAttendance(attendanceId, updateAttendanceRequest)
                _updateAttendanceResult.value = result
            } catch (e: Exception) {
                _updateAttendanceResult.value = Resource.Error(e.message ?: "Error updating attendance")
            }
        }
    }

    fun getAttendanceById(id: Int) {
        viewModelScope.launch {
            _attendanceDetail.value = Resource.Loading
            try {
                val result = attendanceRepository.getAttendanceById(id)
                _attendanceDetail.value = result
            } catch (e: Exception) {
                _attendanceDetail.value = Resource.Error(e.message ?: "Error fetching attendance")
            }
        }
    }

    fun resetCheckInResult() {
        _checkInResult.value = null
    }

    fun resetCheckOutResult() {
        _checkOutResult.value = null
    }

    fun resetUpdateAttendanceResult() {
        _updateAttendanceResult.value = null
    }
}
