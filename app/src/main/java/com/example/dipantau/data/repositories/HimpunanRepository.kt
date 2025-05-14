package com.example.dipantau.data.repositories

import com.example.dipantau.model.Himpunan
import com.example.dipantau.model.HimpunanDetail
import com.example.dipantau.model.PagedResponse
import com.example.dipantau.model.Resource

interface HimpunanRepository {
    suspend fun getAllHimpunan(
        page: Int = 1,
        limit: Int = 10,
        search: String = "",
        status: String = "active"
    ): Resource<PagedResponse<Himpunan>>

    suspend fun createHimpunan(himpunan: Himpunan): Resource<Himpunan>
    suspend fun updateHimpunan(id: Int, himpunan: Himpunan): Resource<Himpunan>
    suspend fun deleteHimpunan(id: Int): Resource<Unit>
    suspend fun getMyHimpunan(): Resource<PagedResponse<Himpunan>>
    suspend fun getHimpunanById(id: Int): Resource<HimpunanDetail>
}