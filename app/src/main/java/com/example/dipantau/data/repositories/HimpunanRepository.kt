package com.example.dipantau.data.repositories

import com.example.dipantau.model.Himpunan
import com.example.dipantau.model.HimpunanRequest
import com.example.dipantau.model.HimpunanResponse
import com.example.dipantau.model.Resource

interface HimpunanRepository {
    suspend fun getAllHimpunan(
        page: Int = 1,
        limit: Int = 10,
        search: String = "",
        status: String = "active"
    ): Resource<HimpunanResponse>
    suspend fun getHimpunanById(id: Int): Resource<Himpunan>
    suspend fun createHimpunan(himpunanRequest: HimpunanRequest): Resource<Himpunan>
    suspend fun updateHimpunan(id: Int, himpunanRequest: HimpunanRequest): Resource<Himpunan>
    suspend fun deleteHimpunan(id: Int): Resource<Unit>
    suspend fun getMyHimpunan(): Resource<Himpunan>
}
