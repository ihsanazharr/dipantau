package com.example.dipantau.data.repositories

import com.example.dipantau.model.*
import com.example.dipantau.model.PagedResponse
import com.example.dipantau.model.Resource
import okhttp3.MultipartBody

interface AdminRepository {

    suspend fun getAllAdmins(
        page: Int = 1,
        limit: Int = 10,
        search: String = ""
    ): Resource<PagedResponse<User>>

    suspend fun getAdminById(id: Int): Resource<User>

    suspend fun createAdmin(adminRequest: AdminRequest): Resource<User>

    suspend fun updateAdmin(id: Int, updateRequest: UpdateUserRequest): Resource<User>

    suspend fun deleteAdmin(id: Int): Resource<Unit>

    suspend fun resetAdminPassword(id: Int, passwordRequest: AdminPasswordRequest): Resource<Unit>

    suspend fun updateAdminProfileImage(id: Int, profileImage: MultipartBody.Part): Resource<User>

    suspend fun getMyAdminProfile(): Resource<User>
}
