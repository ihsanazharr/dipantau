package com.example.dipantau.data.repositories

import com.example.dipantau.model.ApiResponse
import com.example.dipantau.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<ApiResponse<T>>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true) {
                        body.data?.let {
                            Resource.Success(it)
                        } ?: Resource.Error("Data kosong")
                    } else {
                        Resource.Error(body?.message ?: "Terjadi kesalahan")
                    }
                } else {
                    Resource.Error("Terjadi kesalahan: ${response.code()}")
                }
            } catch (e: IOException) {
                Resource.Error("Tidak dapat terhubung ke server")
            } catch (e: Exception) {
                Resource.Error("Terjadi kesalahan: ${e.message}")
            }
        }
    }

    suspend fun <T> safeApiCallForList(apiCall: suspend () -> Response<ApiResponse<List<T>>>): Resource<List<T>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true) {
                        body.data?.let {
                            Resource.Success(it)
                        } ?: Resource.Error("Data kosong")
                    } else {
                        Resource.Error(body?.message ?: "Terjadi kesalahan")
                    }
                } else {
                    Resource.Error("Terjadi kesalahan: ${response.code()}")
                }
            } catch (e: IOException) {
                Resource.Error("Tidak dapat terhubung ke server")
            } catch (e: Exception) {
                Resource.Error("Terjadi kesalahan: ${e.message}")
            }
        }
    }
}