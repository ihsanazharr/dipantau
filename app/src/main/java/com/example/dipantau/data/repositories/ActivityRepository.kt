package com.example.dipantau.data.repositories

import com.example.dipantau.model.*

interface ActivityRepository {
    suspend fun createActivity(activityRequest: ActivityRequest): Resource<Activity>
    suspend fun getActivityById(id: Int): Resource<ActivityDetail>
    suspend fun getAllActivities(): Resource<List<Activity>>
}
