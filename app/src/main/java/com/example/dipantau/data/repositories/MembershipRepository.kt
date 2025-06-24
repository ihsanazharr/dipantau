package com.example.dipantau.data.repositories

import com.example.dipantau.model.User
import com.example.dipantau.model.PagedResponse
import com.example.dipantau.model.Resource
import com.example.dipantau.model.JoinHimpunanRequestNew
import com.example.dipantau.model.UpdateMembershipStatusRequestNew
import com.example.dipantau.model.UpdateScoreRequest

interface MembershipRepository {
    suspend fun joinHimpunan(request: JoinHimpunanRequestNew): Resource<User>
    suspend fun leaveHimpunan(): Resource<Unit>
    suspend fun getMyMembership(): Resource<User>
    suspend fun getHimpunanUsers(
        himpunanId: Int,
        page: Int = 1,
        limit: Int = 10,
        search: String? = null
    ): Resource<PagedResponse<User>>
    suspend fun updateMembershipStatus(userId: Int, request: UpdateMembershipStatusRequestNew): Resource<User>
    suspend fun removeMember(userId: Int): Resource<Unit>
    suspend fun updateUserScore(userId: Int, request: UpdateScoreRequest): Resource<User>
}
