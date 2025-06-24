package com.example.dipantau.data.repositories

import com.example.dipantau.data.remote.ApiService
import com.example.dipantau.model.JoinHimpunanRequestNew
import com.example.dipantau.model.PagedResponse
import com.example.dipantau.model.Resource
import com.example.dipantau.model.UpdateMembershipStatusRequestNew
import com.example.dipantau.model.UpdateScoreRequest
import com.example.dipantau.model.UpdateUserRequest
import com.example.dipantau.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MembershipRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MembershipRepository {

    override suspend fun joinHimpunan(request: JoinHimpunanRequestNew): Resource<User> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.joinHimpunan(request)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true) {
                        Resource.Success(body.data!!)
                    } else {
                        Resource.Error(body?.message ?: "Terjadi kesalahan")
                    }
                } else {
                    Resource.Error("Terjadi kesalahan: ${response.code()}")
                }
            } catch (e: Exception) {
                Resource.Error("Terjadi kesalahan: ${e.message}")
            }
        }

    override suspend fun leaveHimpunan(): Resource<Unit> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.leaveHimpunan()
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.success == true) {
                    Resource.Success(Unit)
                } else {
                    Resource.Error(body?.message ?: "Terjadi kesalahan")
                }
            } else {
                Resource.Error("Terjadi kesalahan: ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error("Terjadi kesalahan: ${e.message}")
        }
    }

    override suspend fun getMyMembership(): Resource<User> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getUserProfile()
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.success == true) {
                    Resource.Success(body.data!!)
                } else {
                    Resource.Error(body?.message ?: "Terjadi kesalahan")
                }
            } else {
                Resource.Error("Terjadi kesalahan: ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error("Terjadi kesalahan: ${e.message}")
        }
    }

    override suspend fun getHimpunanUsers(
        himpunanId: Int,
        page: Int,
        limit: Int,
        search: String?
    ): Resource<PagedResponse<User>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getHimpunanUsers(himpunanId, page, limit, search)
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.success == true) {
                    Resource.Success(body.data!!)
                } else {
                    Resource.Error(body?.message ?: "Terjadi kesalahan")
                }
            } else {
                Resource.Error("Terjadi kesalahan: ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error("Terjadi kesalahan: ${e.message}")
        }
    }

    override suspend fun updateMembershipStatus(
        userId: Int,
        request: UpdateMembershipStatusRequestNew
    ): Resource<User> = withContext(Dispatchers.IO) {
        try {
            val response =
                apiService.updateMembershipStatus(userId, request) // Gunakan endpoint baru
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.success == true) {
                    Resource.Success(body.data!!)
                } else {
                    Resource.Error(body?.message ?: "Terjadi kesalahan")
                }
            } else {
                Resource.Error("Terjadi kesalahan: ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error("Terjadi kesalahan: ${e.message}")
        }
    }

    override suspend fun updateUserScore(userId: Int, request: UpdateScoreRequest): Resource<User> =
        withContext(Dispatchers.IO) {
            try {
                val updateRequest = UpdateUserRequest(
                    score = request.score
                )

                val response = apiService.updateUser(userId, updateRequest)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true) {
                        Resource.Success(body.data!!)
                    } else {
                        Resource.Error(body?.message ?: "Terjadi kesalahan")
                    }
                } else {
                    Resource.Error("Terjadi kesalahan: ${response.code()}")
                }
            } catch (e: Exception) {
                Resource.Error("Terjadi kesalahan: ${e.message}")
            }
        }


    override suspend fun removeMember(userId: Int): Resource<Unit> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.deleteUser(userId) // Pastikan ada endpoint ini
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.success == true) {
                    Resource.Success(Unit)
                } else {
                    Resource.Error(body?.message ?: "Terjadi kesalahan")
                }
            } else {
                Resource.Error("Terjadi kesalahan: ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error("Terjadi kesalahan: ${e.message}")
        }
    }
}
