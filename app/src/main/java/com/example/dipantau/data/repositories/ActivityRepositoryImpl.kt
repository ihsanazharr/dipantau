package com.example.dipantau.data.repositories

import com.example.dipantau.data.remote.ApiService
import com.example.dipantau.model.*
import javax.inject.Inject

//class ActivityRepositoryImpl @Inject constructor(private val apiService: ApiService) : ActivityRepository {
//
//    override suspend fun createActivity(activityRequest: ActivityRequest): Resource<Activity> {
//        return try {
//            val response = apiService.createActivity(activityRequest)
//            if (response.success) {
//                Resource.Success(response.data!!)
//            } else {
//                Resource.Error(response.message ?: "Error creating activity")
//            }
//        } catch (e: Exception) {
//            Resource.Error(e.message ?: "Network error")
//        }
//    }
//
//    override suspend fun getActivityById(id: Int): Resource<ActivityDetail> {
//        return try {
//            val response = apiService.getActivityById(id)
//            if (response.success) {
//                Resource.Success(response.data!!)
//            } else {
//                Resource.Error(response.message ?: "Error fetching activity")
//            }
//        } catch (e: Exception) {
//            Resource.Error(e.message ?: "Network error")
//        }
//    }
//
//    override suspend fun getAllActivities(): Resource<List<Activity>> {
//        return try {
//            val response = apiService.getAllActivities()
//            if (response.success) {
//                Resource.Success(response.data!!)
//            } else {
//                Resource.Error(response.message ?: "Error fetching activities")
//            }
//        } catch (e: Exception) {
//            Resource.Error(e.message ?: "Network error")
//        }
//    }
//}
