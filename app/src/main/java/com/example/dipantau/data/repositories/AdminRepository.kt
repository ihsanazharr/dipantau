package com.example.dipantau.data.repositories

import com.example.dipantau.model.Admin
import com.example.dipantau.model.AdminDetail
import com.example.dipantau.model.AdminPasswordRequest
import com.example.dipantau.model.AdminProfileUpdateRequest
import com.example.dipantau.model.AdminRequest
import com.example.dipantau.model.PagedResponse
import com.example.dipantau.model.Resource
import okhttp3.MultipartBody

interface AdminRepository {
    suspend fun getAllAdmins(
        page: Int = 1,
        limit: Int = 10,
        search: String = "",
        himpunanId: Int? = null
    ): Resource<PagedResponse<Admin>>

    suspend fun getAdminById(id: Int): Resource<AdminDetail>

    suspend fun createAdmin(adminRequest: AdminRequest): Resource<Admin>

    suspend fun updateAdmin(id: Int, updateRequest: AdminProfileUpdateRequest): Resource<Admin>

    suspend fun deleteAdmin(id: Int): Resource<Unit>

    suspend fun resetAdminPassword(id: Int, passwordRequest: AdminPasswordRequest): Resource<Unit>

    suspend fun activateAdmin(id: Int): Resource<Unit>

    suspend fun deactivateAdmin(id: Int): Resource<Unit>

    suspend fun updateAdminProfileImage(id: Int, profileImage: MultipartBody.Part): Resource<Admin>

    suspend fun getMyAdminProfile(): Resource<AdminDetail>
}