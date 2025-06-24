package com.example.dipantau.data.repositories

import com.example.dipantau.data.remote.ApiService
import com.example.dipantau.model.*
import com.example.dipantau.model.PagedResponse
import com.example.dipantau.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(

    private val apiService: ApiService
) : BaseRepository(), AdminRepository {

    override suspend fun getAllAdmins(
        page: Int,
        limit: Int,
        search: String
    ): Resource<PagedResponse<User>> = safeApiCall {
        apiService.getAllUsers(page, limit, search)
    }
    override suspend fun getAdminById(id: Int): Resource<User> = safeApiCall {
        apiService.getUserById(id)
    }

    override suspend fun createAdmin(adminRequest: AdminRequest): Resource<User> {
        TODO("Not yet implemented")
    }

    override suspend fun updateAdmin(id: Int, updateRequest: UpdateUserRequest): Resource<User> = safeApiCall {
        apiService.updateUser(id, updateRequest)
    }
    override suspend fun deleteAdmin(id: Int): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.deleteUser(id)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true) {
                        Resource.Success(Unit)
                    } else {
                        Resource.Error(body?.message ?: "Terjadi kesalahan")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Resource.Error(errorBody ?: "Terjadi kesalahan")
                }
            } catch (e: HttpException) {
                Resource.Error("Terjadi kesalahan jaringan: ${e.message()}")
            } catch (e: IOException) {
                Resource.Error("Tidak dapat terhubung ke server")
            } catch (e: Exception) {
                Resource.Error("Terjadi kesalahan: ${e.message}")
            }
        }
    }

    override suspend fun resetAdminPassword(
        id: Int,
        passwordRequest: AdminPasswordRequest
    ): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateAdminProfileImage(
        id: Int,
        profileImage: MultipartBody.Part
    ): Resource<User> {
        TODO("Not yet implemented")
    }
//    override suspend fun updateAdminProfileImage(id: Int, profileImage: MultipartBody.Part): Resource<User> {
//        return safeApiCall {
//            apiService.updateProfilePicture(id, profileImage) // Mengirimkan ID dan gambar profil
//        }
//    }

    override suspend fun getMyAdminProfile(): Resource<User> = safeApiCall {
        apiService.getUserProfile()
    }
}