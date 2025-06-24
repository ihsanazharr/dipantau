package com.example.dipantau.model

import com.google.gson.annotations.SerializedName

/**
 * Request untuk login
 */
data class LoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

/**
 * Request untuk registrasi
 */
data class RegisterRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("fullName") val fullName: String?,
    @SerializedName("phoneNumber") val phoneNumber: String?
)

/**
 * Request untuk update profil
 */
data class UpdateProfileRequest(
    @SerializedName("fullName") val fullName: String?,
    @SerializedName("phoneNumber") val phoneNumber: String?,
    @SerializedName("username") val username: String?
)

/**
 * Request untuk lupa password
 */
data class ForgotPasswordRequest(
    @SerializedName("email") val email: String
)

/**
 * Request untuk reset password
 */
data class ResetPasswordRequest(
    @SerializedName("token") val token: String,
    @SerializedName("password") val password: String
)

/**
 * Request untuk update user (admin)
 */
data class UpdateUserRequest(
@SerializedName("fullName") val fullName: String? = null,
@SerializedName("email") val email: String? = null,
@SerializedName("role") val role: String? = null,
@SerializedName("isActive") val isActive: Boolean? = null,
@SerializedName("phoneNumber") val phoneNumber: String? = null,
@SerializedName("username") val username: String? = null,
@SerializedName("score") val score: Int? = null
)

/**
 * Request untuk membuat/mengupdate himpunan
 */
data class HimpunanRequest(
    @SerializedName("name") val name: String,
    @SerializedName("aka") val aka: String,
    @SerializedName("description") val description: String? = null,
    @SerializedName("foundedDate") val foundedDate: String? = null,
    @SerializedName("contactEmail") val contactEmail: String? = null,
    @SerializedName("contactPhone") val contactPhone: String? = null,
    @SerializedName("address") val address: String? = null,
    @SerializedName("status") val status: String? = null
)

/**
 * Request untuk mengubah admin himpunan
 */
data class ChangeAdminRequest(
    @SerializedName("newAdminId") val newAdminId: Int
)

/**
 * Request untuk bergabung dengan himpunan
 */
data class JoinHimpunanRequestNew(
    @SerializedName("himpunanId") val himpunanId: Int
)

/**
 * Request untuk membuat notifikasi
 */
data class NotificationRequest(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("type") val type: String? = null,
    @SerializedName("recipientId") val recipientId: Int? = null,
    @SerializedName("priority") val priority: String? = null
)

/**
 * Request untuk membuat admin baru
 */
data class AdminRequest(
    @SerializedName("email") val email: String,
    @SerializedName("fullName") val fullName: String?,
    @SerializedName("phoneNumber") val phoneNumber: String?,
    @SerializedName("role") val role: String,
    @SerializedName("himpunanId") val himpunanId: Int? = null,
    @SerializedName("username") val username: String? = null
)

/**
 * Request untuk mengupdate profil admin
 */
data class AdminProfileUpdateRequest(
    @SerializedName("fullName") val fullName: String? = null,
    @SerializedName("phoneNumber") val phoneNumber: String? = null,
    @SerializedName("username") val username: String? = null,
    @SerializedName("role") val role: String? = null,
    @SerializedName("himpunanId") val himpunanId: Int? = null,
    @SerializedName("isActive") val isActive: Boolean? = null
)

/**
 * Request untuk mengubah password admin
 */
data class AdminPasswordRequest(
    @SerializedName("password") val password: String
)

// Tambahkan di Request.kt
data class TaskRequest(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String? = null,
    @SerializedName("priority") val priority: String,
    @SerializedName("dueDate") val dueDate: String? = null,
    @SerializedName("startDate") val startDate: String? = null,
    @SerializedName("scoreReward") val scoreReward: Int,
    @SerializedName("category") val category: String? = null,
    @SerializedName("tags") val tags: List<String>? = null,
    @SerializedName("himpunanId") val himpunanId: Int,
    @SerializedName("assignedToId") val assignedToId: Int? = null
)

data class UpdateTaskRequest(
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("priority") val priority: String? = null,
    @SerializedName("dueDate") val dueDate: String? = null,
    @SerializedName("startDate") val startDate: String? = null,
    @SerializedName("scoreReward") val scoreReward: Int? = null,
    @SerializedName("category") val category: String? = null,
    @SerializedName("tags") val tags: List<String>? = null,
    @SerializedName("assignedToId") val assignedToId: Int? = null
)

data class UpdateScoreRequest(
    @SerializedName("score") val score: Int
)

data class UpdateMembershipStatusRequestNew(
    @SerializedName("membershipStatus") val membershipStatus: String,
    @SerializedName("himpunanRole") val himpunanRole: String? = null,
    @SerializedName("membershipType") val membershipType: String? = null
)

// Request untuk membuat/mengupdate kegiatan
data class ActivityRequest(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String? = null,
    @SerializedName("type") val type: String,
    @SerializedName("startDateTime") val startDateTime: String,
    @SerializedName("endDateTime") val endDateTime: String,
    @SerializedName("location") val location: String? = null,
    @SerializedName("himpunanId") val himpunanId: Int,
    @SerializedName("attendanceMode") val attendanceMode: String? = null
)

// Request untuk check-in kehadiran
data class CheckInRequest(
    @SerializedName("activityId") val activityId: Int,
    @SerializedName("qrCode") val qrCode: String,
    @SerializedName("location") val location: String? = null
)

// Request untuk check-out kehadiran
data class CheckOutRequest(
    @SerializedName("activityId") val activityId: Int,
    @SerializedName("location") val location: String? = null
)

// Request untuk update kehadiran
data class UpdateAttendanceRequest(
    @SerializedName("status") val status: String? = null,
    @SerializedName("notes") val notes: String? = null
)


