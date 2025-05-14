package com.example.dipantau.data.repositories

import com.example.dipantau.model.ApiResponse
import com.example.dipantau.model.Himpunan
import com.example.dipantau.model.HimpunanListResponse
import com.example.dipantau.model.HimpunanResponse
import com.example.dipantau.model.PagedResponse
import com.example.dipantau.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Base Repository untuk menangani networking dengan pattern yang konsisten
 */
abstract class BaseRepository {

     suspend fun <T> safeApiCall(apiCall: suspend () -> Response<ApiResponse<T>>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiCall()

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true) {
                        body.data?.let {
                            return@withContext Resource.Success(it)
                        } ?: return@withContext Resource.Error("Data kosong")
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

    suspend fun safeApiCallForHimpunanResponse(apiCall: suspend () -> Response<HimpunanResponse>): Resource<PagedResponse<Himpunan>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiCall()

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true) {
                        return@withContext Resource.Success(
                            PagedResponse(
                                count = body.count,
                                totalPages = body.totalPages,
                                currentPage = body.currentPage,
                                data = body.data
                            )
                        )
                    } else {
                        return@withContext Resource.Error("Terjadi kesalahan")
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


    suspend fun <T> safeApiCallForList(apiCall: suspend () -> Response<ApiResponse<List<T>>>): Resource<List<T>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiCall()

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true) {
                        body.data?.let {
                            return@withContext Resource.Success(it)
                        } ?: return@withContext Resource.Error("Data kosong")
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

    /**
     * Fungsi generik untuk melakukan operasi API tanpa return data (hanya message)
     */
    protected suspend fun safeApiCallForMessage(apiCall: suspend () -> Response<ApiResponse<Any>>): Resource<String> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiCall()

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true) {
                        return@withContext Resource.Success(body.message ?: "Operasi berhasil")
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
}