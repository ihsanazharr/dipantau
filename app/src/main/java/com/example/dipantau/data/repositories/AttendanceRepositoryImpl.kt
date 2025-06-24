package com.example.dipantau.data.repositories

import com.example.dipantau.data.remote.ApiService
import com.example.dipantau.model.*
import javax.inject.Inject

class AttendanceRepositoryImpl @Inject constructor(private val apiService: ApiService) : AttendanceRepository {

    override suspend fun checkInAttendance(activityId: Int, checkInRequest: CheckInRequest): Resource<Attendance> {
        return try {
            val response = apiService.checkInAttendance(activityId, checkInRequest)
            if (response.success) {
                Resource.Success(response.data!!)
            } else {
                Resource.Error(response.message ?: "Error checking in")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error")
        }
    }

    override suspend fun checkOutAttendance(attendanceId: Int, checkOutRequest: CheckOutRequest): Resource<Attendance> {
        return try {
            val response = apiService.checkOutAttendance(attendanceId, checkOutRequest)
            if (response.success) {
                Resource.Success(response.data!!)
            } else {
                Resource.Error(response.message ?: "Error checking out")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error")
        }
    }

    override suspend fun updateAttendance(attendanceId: Int, updateAttendanceRequest: UpdateAttendanceRequest): Resource<Attendance> {
        return try {
            val response = apiService.updateAttendance(attendanceId, updateAttendanceRequest)
            if (response.success) {
                Resource.Success(response.data!!)
            } else {
                Resource.Error(response.message ?: "Error updating attendance")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error")
        }
    }

    override suspend fun getAttendanceById(id: Int): Resource<AttendanceDetail> {
        return try {
            val response = apiService.getAttendanceById(id)
            if (response.success) {
                Resource.Success(response.data!!)
            } else {
                Resource.Error(response.message ?: "Error fetching attendance")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error")
        }
    }
}
