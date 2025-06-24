package com.example.dipantau.data.repositories

import com.example.dipantau.data.remote.ApiService
import com.example.dipantau.model.Himpunan
import com.example.dipantau.model.HimpunanRequest
import com.example.dipantau.model.HimpunanResponse // Import
import com.example.dipantau.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HimpunanRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : BaseRepository(), HimpunanRepository {

    override suspend fun getAllHimpunan(
        page: Int,
        limit: Int,
        search: String,
        status: String
    ): Resource<HimpunanResponse> = safeApiCall {
        apiService.getAllHimpunan(page, limit, search, status)
    }

    override suspend fun createHimpunan(himpunan: HimpunanRequest): Resource<Himpunan> = withContext(
        Dispatchers.IO) {
        try {
            val response = apiService.createHimpunan(himpunan)
            if (response.isSuccessful) {
                Resource.Success(response.body()!!.data!!)
            } else {
                Resource.Error("Terjadi kesalahan: ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error("Terjadi kesalahan: ${e.message}")
        }
    }

    override suspend fun updateHimpunan(id: Int, himpunan: HimpunanRequest): Resource<Himpunan> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.updateHimpunan(id, himpunan)
            if (response.isSuccessful) {
                Resource.Success(response.body()!!.data!!)
            } else {
                Resource.Error("Terjadi kesalahan: ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error("Terjadi kesalahan: ${e.message}")
        }
    }

    override suspend fun deleteHimpunan(id: Int): Resource<Unit> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.deleteHimpunan(id)
            if (response.isSuccessful) {
                Resource.Success(Unit)
            } else {
                Resource.Error("Terjadi kesalahan: ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error("Terjadi kesalahan: ${e.message}")
        }
    }

    override suspend fun getMyHimpunan(): Resource<Himpunan> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getMyHimpunan()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!.data!!)
            } else {
                Resource.Error("Terjadi kesalahan: ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error("Terjadi kesalahan: ${e.message}")
        }
    }

    override suspend fun getHimpunanById(id: Int): Resource<Himpunan> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getHimpunanById(id)
            if (response.isSuccessful) {
                Resource.Success(response.body()!!.data!!)
            } else {
                Resource.Error("Terjadi kesalahan: ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error("Terjadi kesalahan: ${e.message}")
        }
    }
}