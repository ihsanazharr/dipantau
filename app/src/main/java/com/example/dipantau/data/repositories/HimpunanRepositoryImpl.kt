package com.example.dipantau.data.repositories

import com.example.dipantau.data.remote.ApiService
import com.example.dipantau.model.Himpunan
import com.example.dipantau.model.HimpunanDetail
import com.example.dipantau.model.HimpunanRequest
import com.example.dipantau.model.PagedResponse
import com.example.dipantau.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class HimpunanRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : BaseRepository(), HimpunanRepository {

    override suspend fun getAllHimpunan(
        page: Int,
        limit: Int,
        search: String,
        status: String
    ): Resource<PagedResponse<Himpunan>> = safeApiCallForHimpunanResponse {
        apiService.getAllHimpunan(page, limit, search, status)
    }

    override suspend fun getMyHimpunan(): Resource<PagedResponse<Himpunan>> = safeApiCallForHimpunanResponse {
        apiService.getAllHimpunan(page = 1, limit = 50, status = "my")
    }

    override suspend fun createHimpunan(himpunan: Himpunan): Resource<Himpunan> = safeApiCall {
        val request = HimpunanRequest(
            name = himpunan.name,
            aka = himpunan.aka,
            description = himpunan.description,
            foundedDate = himpunan.foundedDate,
            contactEmail = himpunan.contactEmail,
            contactPhone = himpunan.contactPhone,
            address = himpunan.address,
            status = himpunan.status
        )
        apiService.createHimpunan(request)
    }

    override suspend fun updateHimpunan(id: Int, himpunan: Himpunan): Resource<Himpunan> = safeApiCall {
        val request = HimpunanRequest(
            name = himpunan.name,
            aka = himpunan.aka,
            description = himpunan.description,
            foundedDate = himpunan.foundedDate,
            contactEmail = himpunan.contactEmail,
            contactPhone = himpunan.contactPhone,
            address = himpunan.address,
            status = himpunan.status
        )
        apiService.updateHimpunan(id, request)
    }

    override suspend fun deleteHimpunan(id: Int): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.deleteHimpunan(id)

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

    override suspend fun getHimpunanById(id: Int): Resource<HimpunanDetail> = safeApiCall {
        apiService.getHimpunanById(id)
    }
}