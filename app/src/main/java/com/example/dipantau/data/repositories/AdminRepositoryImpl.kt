package com.example.dipantau.data.repositories

import com.example.dipantau.data.remote.ApiService
import com.example.dipantau.model.Admin
import com.example.dipantau.model.AdminDetail
import com.example.dipantau.model.AdminPasswordRequest
import com.example.dipantau.model.AdminProfileUpdateRequest
import com.example.dipantau.model.AdminRequest
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
        search: String,
        himpunanId: Int?
    ): Resource<PagedResponse<Admin>> = safeApiCall {
        apiService.getAllAdmins(page, limit, search, himpunanId)
    }

    override suspend fun getAdminById(id: Int): Resource<AdminDetail> = safeApiCall {
        apiService.getAdminById(id)
    }

    override suspend fun createAdmin(adminRequest: AdminRequest): Resource<Admin> = safeApiCall {
        apiService.createAdmin(adminRequest)
    }

    override suspend fun updateAdmin(
        id: Int,
        updateRequest: AdminProfileUpdateRequest
    ): Resource<Admin> = safeApiCall {
        apiService.updateAdmin(id, updateRequest)
    }

    override suspend fun deleteAdmin(id: Int): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.deleteAdmin(id)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true) {
                        return@withContext Resource.Success(Unit)
                    } else {
                        return@withContext Resource.Error(body?.message ?: "Terjadi kesalahan")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    return@withContext Resource.Error(errorBody ?: "Terjadi kesalahan")
                }
            } catch (e: HttpException) {
                return@withContext Resource.Error("Terjadi kesalahan jaringan: ${e.message()}")
            } catch (e: IOException) {
                return@withContext Resource.Error("Tidak dapat terhubung ke server")
            } catch (e: Exception) {
                return@withContext Resource.Error("Terjadi kesalahan: ${e.message}")
            }
        }
    }

    override suspend fun resetAdminPassword(
        id: Int,
        passwordRequest: AdminPasswordRequest
    ): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.resetAdminPassword(id, passwordRequest)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true) {
                        return@withContext Resource.Success(Unit)
                    } else {
                        return@withContext Resource.Error(body?.message ?: "Terjadi kesalahan")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    return@withContext Resource.Error(errorBody ?: "Terjadi kesalahan")
                }
            } catch (e: HttpException) {
                return@withContext Resource.Error("Terjadi kesalahan jaringan: ${e.message()}")
            } catch (e: IOException) {
                return@withContext Resource.Error("Tidak dapat terhubung ke server")
            } catch (e: Exception) {
                return@withContext Resource.Error("Terjadi kesalahan: ${e.message}")
            }
        }
    }

    override suspend fun activateAdmin(id: Int): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.activateAdmin(id)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true) {
                        return@withContext Resource.Success(Unit)
                    } else {
                        return@withContext Resource.Error(body?.message ?: "Terjadi kesalahan")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    return@withContext Resource.Error(errorBody ?: "Terjadi kesalahan")
                }
            } catch (e: HttpException) {
                return@withContext Resource.Error("Terjadi kesalahan jaringan: ${e.message()}")
            } catch (e: IOException) {
                return@withContext Resource.Error("Tidak dapat terhubung ke server")
            } catch (e: Exception) {
                return@withContext Resource.Error("Terjadi kesalahan: ${e.message}")
            }
        }
    }

    override suspend fun deactivateAdmin(id: Int): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.deactivateAdmin(id)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true) {
                        return@withContext Resource.Success(Unit)
                    } else {
                        return@withContext Resource.Error(body?.message ?: "Terjadi kesalahan")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    return@withContext Resource.Error(errorBody ?: "Terjadi kesalahan")
                }
            } catch (e: HttpException) {
                return@withContext Resource.Error("Terjadi kesalahan jaringan: ${e.message()}")
            } catch (e: IOException) {
                return@withContext Resource.Error("Tidak dapat terhubung ke server")
            } catch (e: Exception) {
                return@withContext Resource.Error("Terjadi kesalahan: ${e.message}")
            }
        }
    }

    override suspend fun updateAdminProfileImage(
        id: Int,
        profileImage: MultipartBody.Part
    ): Resource<Admin> = safeApiCall {
        apiService.updateAdminProfileImage(id, profileImage)
    }

    override suspend fun getMyAdminProfile(): Resource<AdminDetail> = safeApiCall {
        apiService.getMyAdminProfile()
    }
}