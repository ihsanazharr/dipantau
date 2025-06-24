package com.example.dipantau.data.remote

import com.example.dipantau.model.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    // ==================== USER API ====================
    @POST("users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<ApiResponse<AuthResponse>>

    @POST("users")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<ApiResponse<AuthResponse>>

    @GET("users/profile")
    suspend fun getUserProfile(): Response<ApiResponse<User>>

    @PUT("users/profile")
    suspend fun updateUserProfile(@Body request: UpdateProfileRequest): Response<ApiResponse<User>>

    @POST("users/forgot-password")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): Response<ApiResponse<MessageResponse>>

    @POST("users/reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): Response<ApiResponse<MessageResponse>>

    @Multipart
    @PUT("users/profile/picture")
    suspend fun updateProfilePicture(@Part profilePicture: MultipartBody.Part): Response<ApiResponse<User>>

    // Admin only
    @GET("users")
    suspend fun getAllUsers(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("search") search: String? = null
    ): Response<ApiResponse<PagedResponse<User>>>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<ApiResponse<User>>

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body request: UpdateUserRequest): Response<ApiResponse<User>>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<ApiResponse<MessageResponse>>

    // ==================== HIMPUNAN API ====================
    @GET("himpunan")
    suspend fun getAllHimpunan(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("search") search: String? = null,
        @Query("status") status: String? = null
    ): Response<ApiResponse<HimpunanResponse>>

    @GET("himpunan/{id}")
    suspend fun getHimpunanById(@Path("id") id: Int): Response<ApiResponse<Himpunan>>

    @POST("himpunan")
    suspend fun createHimpunan(@Body request: HimpunanRequest): Response<ApiResponse<Himpunan>>

    @PUT("himpunan/{id}")
    suspend fun updateHimpunan(@Path("id") id: Int, @Body request: HimpunanRequest): Response<ApiResponse<Himpunan>>

    @DELETE("himpunan/{id}")
    suspend fun deleteHimpunan(@Path("id") id: Int): Response<ApiResponse<MessageResponse>>

    @GET("himpunan/my-himpunan")
    suspend fun getMyHimpunan(): Response<ApiResponse<Himpunan>>

    @PUT("himpunan/{id}/change-admin")
    suspend fun changeHimpunanAdmin(@Path("id") id: Int, @Body request: ChangeAdminRequest): Response<ApiResponse<MessageResponse>>

    @POST("users/join-himpunan")
    suspend fun joinHimpunan(@Body request: JoinHimpunanRequestNew): Response<ApiResponse<User>>

    @DELETE("users/leave-himpunan")
    suspend fun leaveHimpunan(): Response<ApiResponse<MessageResponse>>

    @GET("himpunan/{himpunanId}/users")
    suspend fun getHimpunanUsers(
        @Path("himpunanId") himpunanId: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("search") search: String? = null
    ): Response<ApiResponse<PagedResponse<User>>>

    // ==================== TASK API ====================
    @GET("tasks")
    suspend fun getAllTasks(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("himpunanId") himpunanId: Int? = null,
        @Query("status") status: String? = null,
        @Query("priority") priority: String? = null
    ): Response<ApiResponse<PagedResponse<Task>>>

    @GET("tasks/himpunan/{himpunanId}")
    suspend fun getHimpunanTasks(
        @Path("himpunanId") himpunanId: Int,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Response<ApiResponse<PagedResponse<Task>>>

    @GET("tasks/user")
    suspend fun getCurrentUserTasks(): Response<ApiResponse<List<Task>>>

    @GET("tasks/user/{userId}")
    suspend fun getUserTasks(@Path("userId") userId: Int): Response<ApiResponse<List<Task>>>

    @GET("tasks/{id}")
    suspend fun getTaskById(@Path("id") id: Int): Response<ApiResponse<Task>>

    @POST("tasks")
    suspend fun createTask(@Body request: TaskRequest): Response<ApiResponse<Task>>

    @PUT("tasks/{id}")
    suspend fun updateTask(
        @Path("id") id: Int,
        @Body request: UpdateTaskRequest
    ): Response<ApiResponse<Task>>

    @DELETE("tasks/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Response<ApiResponse<MessageResponse>>

    @PUT("users/{id}/membership-status")
    suspend fun updateMembershipStatus(
        @Path("id") userId: Int,
        @Body request: UpdateMembershipStatusRequestNew
    ): Response<ApiResponse<User>>

    @POST("activities")
    suspend fun createActivity(
        @Body activityRequest: ActivityRequest
    ): ApiResponse<Activity>

    @GET("activities/{id}")
    suspend fun getActivityById(
        @Path("id") id: Int
    ): ApiResponse<ActivityDetail>

    @GET("activities")
    suspend fun getAllActivities(): ApiResponse<List<Activity>>
    @POST("activities/{id}/attendances/checkin")
    suspend fun checkInAttendance(@Path("id") activityId: Int, @Body checkInRequest: CheckInRequest): ApiResponse<Attendance>
    @POST("attendances/{id}/checkout")
    suspend fun checkOutAttendance(@Path("id") attendanceId: Int, @Body checkOutRequest: CheckOutRequest): ApiResponse<Attendance>
    @GET("attendances/{id}")
    suspend fun getAttendanceById(@Path("id") id: Int): ApiResponse<AttendanceDetail>
    @POST("attendances/{id}/update")
    suspend fun updateAttendance(@Path("id") attendanceId: Int, @Body updateAttendanceRequest: UpdateAttendanceRequest): ApiResponse<Attendance>

}