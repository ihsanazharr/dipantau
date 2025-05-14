package com.example.dipantau.data.remote

import com.example.dipantau.model.Activity
import com.example.dipantau.model.ActivityDetail
import com.example.dipantau.model.ActivityRequest
import com.example.dipantau.model.Admin
import com.example.dipantau.model.AdminDetail
import com.example.dipantau.model.AdminPasswordRequest
import com.example.dipantau.model.AdminProfileUpdateRequest
import com.example.dipantau.model.AdminRequest
import com.example.dipantau.model.ApiResponse
import com.example.dipantau.model.Attendance
import com.example.dipantau.model.AttendanceDetail
import com.example.dipantau.model.AuthResponse
import com.example.dipantau.model.ChangeAdminRequest
import com.example.dipantau.model.CheckInRequest
import com.example.dipantau.model.CheckOutRequest
import com.example.dipantau.model.ForgotPasswordRequest
import com.example.dipantau.model.Himpunan
import com.example.dipantau.model.HimpunanDetail
import com.example.dipantau.model.HimpunanListResponse
import com.example.dipantau.model.HimpunanMember
import com.example.dipantau.model.HimpunanRequest
import com.example.dipantau.model.HimpunanResponse
import com.example.dipantau.model.JoinHimpunanRequest
import com.example.dipantau.model.JoinHimpunanRequestNew
import com.example.dipantau.model.LoginRequest
import com.example.dipantau.model.MessageResponse
import com.example.dipantau.model.Notification
import com.example.dipantau.model.NotificationDetail
import com.example.dipantau.model.NotificationRequest
import com.example.dipantau.model.PagedResponse
import com.example.dipantau.model.RegisterRequest
import com.example.dipantau.model.ResetPasswordRequest
import com.example.dipantau.model.Resource
import com.example.dipantau.model.Task
import com.example.dipantau.model.TaskRequest
import com.example.dipantau.model.UnreadCount
import com.example.dipantau.model.UpdateActivityStatusRequest
import com.example.dipantau.model.UpdateAttendanceRequest
import com.example.dipantau.model.UpdateMemberRoleRequest
import com.example.dipantau.model.UpdateMemberStatusRequest
import com.example.dipantau.model.UpdateMembershipStatusRequestNew
import com.example.dipantau.model.UpdateProfileRequest
import com.example.dipantau.model.UpdateScoreRequest
import com.example.dipantau.model.UpdateTaskRequest
import com.example.dipantau.model.UpdateUserRequest
import com.example.dipantau.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.*
import java.io.IOException

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
    suspend fun updateProfilePicture(
        @Part profilePicture: MultipartBody.Part
    ): Response<ApiResponse<User>>

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
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body request: UpdateUserRequest
    ): Response<ApiResponse<User>>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<ApiResponse<MessageResponse>>

    // ==================== HIMPUNAN API ====================
    @GET("himpunan")
    suspend fun getAllHimpunan(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("search") search: String? = null,
        @Query("status") status: String? = null
    ): Response<HimpunanResponse>

    /**
     * Fungsi generik untuk endpoint yang mengembalikan array di field data
     */


    @GET("himpunan/{id}")
    suspend fun getHimpunanById(@Path("id") id: Int): Response<ApiResponse<HimpunanDetail>>

    @POST("himpunan")
    suspend fun createHimpunan(@Body request: HimpunanRequest): Response<ApiResponse<Himpunan>>

    @PUT("himpunan/{id}")
    suspend fun updateHimpunan(
        @Path("id") id: Int,
        @Body request: HimpunanRequest
    ): Response<ApiResponse<Himpunan>>

    @DELETE("himpunan/{id}")
    suspend fun deleteHimpunan(@Path("id") id: Int): Response<ApiResponse<MessageResponse>>

    @GET("himpunan/my-himpunan")
    suspend fun getMyHimpunan(): Response<HimpunanListResponse>

    @PUT("himpunan/{id}/change-admin")
    suspend fun changeHimpunanAdmin(
        @Path("id") id: Int,
        @Body request: ChangeAdminRequest
    ): Response<ApiResponse<MessageResponse>>

    @Multipart
    @PUT("himpunan/{id}/logo")
    suspend fun updateHimpunanLogo(
        @Path("id") id: Int,
        @Part logo: MultipartBody.Part
    ): Response<ApiResponse<Himpunan>>

    @PUT("himpunan-members/{id}/role")
    suspend fun updateMemberRole(
        @Path("id") id: Int,
        @Body request: UpdateMemberRoleRequest
    ): Response<ApiResponse<HimpunanMember>>

    @DELETE("himpunan-members/{himpunanId}/leave")
    suspend fun leaveHimpunan(@Path("himpunanId") himpunanId: Int): Response<ApiResponse<MessageResponse>>

//    @DELETE("himpunan-members/{id}")
//    suspend fun removeMember(@Path("id") id: Int): Response<ApiResponse<MessageResponse>>

    // ==================== ACTIVITY API ====================
    @GET("activities")
    suspend fun getAllActivities(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("search") search: String? = null,
        @Query("status") status: String? = null,
        @Query("himpunanId") himpunanId: Int? = null,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null
    ): Response<ApiResponse<PagedResponse<Activity>>>

    @GET("activities/{id}")
    suspend fun getActivityById(@Path("id") id: Int): Response<ApiResponse<ActivityDetail>>

    @POST("activities")
    suspend fun createActivity(@Body request: ActivityRequest): Response<ApiResponse<Activity>>

    @PUT("activities/{id}")
    suspend fun updateActivity(
        @Path("id") id: Int,
        @Body request: ActivityRequest
    ): Response<ApiResponse<Activity>>

    @DELETE("activities/{id}")
    suspend fun deleteActivity(@Path("id") id: Int): Response<ApiResponse<MessageResponse>>

    @GET("himpunan/{himpunanId}/activities")
    suspend fun getHimpunanActivities(
        @Path("himpunanId") himpunanId: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("status") status: String? = null
    ): Response<ApiResponse<PagedResponse<Activity>>>

    @PUT("activities/{id}/status")
    suspend fun updateActivityStatus(
        @Path("id") id: Int,
        @Body request: UpdateActivityStatusRequest
    ): Response<ApiResponse<Activity>>

    // ==================== ATTENDANCE API ====================
    @POST("attendances/check-in")
    suspend fun checkInAttendance(@Body request: CheckInRequest): Response<ApiResponse<Attendance>>

    @PUT("attendances/check-out")
    suspend fun checkOutAttendance(@Body request: CheckOutRequest): Response<ApiResponse<Attendance>>

    @GET("activities/{activityId}/attendances")
    suspend fun getActivityAttendances(
        @Path("activityId") activityId: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("status") status: String? = null
    ): Response<ApiResponse<PagedResponse<Attendance>>>

    @GET("attendances/{id}")
    suspend fun getAttendanceById(@Path("id") id: Int): Response<ApiResponse<AttendanceDetail>>

    @PUT("attendances/{id}")
    suspend fun updateAttendance(
        @Path("id") id: Int,
        @Body request: UpdateAttendanceRequest
    ): Response<ApiResponse<Attendance>>

    @DELETE("attendances/{id}")
    suspend fun deleteAttendance(@Path("id") id: Int): Response<ApiResponse<MessageResponse>>

    @GET("users/my-attendances")
    suspend fun getMyAttendances(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("himpunanId") himpunanId: Int? = null
    ): Response<ApiResponse<PagedResponse<Attendance>>>

    // ==================== NOTIFICATION API ====================
    @GET("notifications/my-notifications")
    suspend fun getMyNotifications(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("isRead") isRead: Boolean? = null,
        @Query("type") type: String? = null
    ): Response<ApiResponse<PagedResponse<Notification>>>

    @GET("notifications/{id}")
    suspend fun getNotificationById(@Path("id") id: Int): Response<ApiResponse<NotificationDetail>>

    @PUT("notifications/{id}/mark-read")
    suspend fun markNotificationAsRead(@Path("id") id: Int): Response<ApiResponse<Notification>>

    @PUT("notifications/mark-all-read")
    suspend fun markAllNotificationsAsRead(): Response<ApiResponse<MessageResponse>>

    @DELETE("notifications/{id}")
    suspend fun deleteNotification(@Path("id") id: Int): Response<ApiResponse<MessageResponse>>

    @GET("notifications/unread-count")
    suspend fun getUnreadNotificationsCount(): Response<ApiResponse<UnreadCount>>

    @POST("notifications")
    suspend fun createNotification(@Body request: NotificationRequest): Response<ApiResponse<Notification>>

    @POST("notifications/himpunan/{himpunanId}")
    suspend fun sendHimpunanNotification(
        @Path("himpunanId") himpunanId: Int,
        @Body request: NotificationRequest
    ): Response<ApiResponse<MessageResponse>>

//     ==================== ADMIN API ====================
    @GET("admin")
    suspend fun getAllAdmins(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("search") search: String? = null,
        @Query("himpunanId") himpunanId: Int? = null
    ): Response<ApiResponse<PagedResponse<Admin>>>

    @GET("admin/{id}")
    suspend fun getAdminById(@Path("id") id: Int): Response<ApiResponse<AdminDetail>>

    @POST("admin")
    suspend fun createAdmin(@Body request: AdminRequest): Response<ApiResponse<Admin>>

    @PUT("admin/{id}")
    suspend fun updateAdmin(
        @Path("id") id: Int,
        @Body request: AdminProfileUpdateRequest
    ): Response<ApiResponse<Admin>>

    @DELETE("admin/{id}")
    suspend fun deleteAdmin(@Path("id") id: Int): Response<ApiResponse<MessageResponse>>

    @PUT("admin/{id}/reset-password")
    suspend fun resetAdminPassword(
        @Path("id") id: Int,
        @Body request: AdminPasswordRequest
    ): Response<ApiResponse<MessageResponse>>

    @PUT("admin/{id}/activate")
    suspend fun activateAdmin(
        @Path("id") id: Int
    ): Response<ApiResponse<MessageResponse>>

    @PUT("admin/{id}/deactivate")
    suspend fun deactivateAdmin(
        @Path("id") id: Int
    ): Response<ApiResponse<MessageResponse>>

    @Multipart
    @PUT("admin/{id}/profile-image")
    suspend fun updateAdminProfileImage(
        @Path("id") id: Int,
        @Part profileImage: MultipartBody.Part
    ): Response<ApiResponse<Admin>>

    @GET("admin/my-profile")
    suspend fun getMyAdminProfile(): Response<ApiResponse<AdminDetail>>

    // ==================== MEMBERSHIP API (Updated) ====================
    @POST("users/join-himpunan")
    suspend fun joinHimpunan(@Body request: JoinHimpunanRequestNew): Response<ApiResponse<User>>

    @DELETE("users/leave-himpunan")
    suspend fun leaveHimpunan(): Response<ApiResponse<MessageResponse>>

    @GET("users/my-membership")
    suspend fun getMyMembership(): Response<ApiResponse<User>>

    @GET("himpunan/{himpunanId}/users")
    suspend fun getHimpunanUsers(
        @Path("himpunanId") himpunanId: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("search") search: String? = null
    ): Response<ApiResponse<PagedResponse<User>>>

    @PUT("users/{userId}/membership-status")
    suspend fun updateMembershipStatus(
        @Path("userId") userId: Int,
        @Body request: UpdateMembershipStatusRequestNew
    ): Response<ApiResponse<User>>

    @DELETE("users/{userId}/remove-member")
    suspend fun removeMember(@Path("userId") userId: Int): Response<ApiResponse<MessageResponse>>

    @PUT("users/{userId}/score")
    suspend fun updateUserScore(
        @Path("userId") userId: Int,
        @Body request: UpdateScoreRequest
    ): Response<ApiResponse<User>>

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

}

