package com.example.dipantau.model

import com.google.gson.annotations.SerializedName

/**
 * Wrapper untuk semua response API
 */
data class ApiResponse<T>(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("data")
    val data: T?,

    @SerializedName("error")
    val error: String? = null
)

/**
 * Response untuk data paging
 */
data class PagedResponse<T>(
    @SerializedName("count")
    val count: Int,

    @SerializedName("totalPages")
    val totalPages: Int,

    @SerializedName("currentPage")
    val currentPage: Int,

    @SerializedName("data")
    val data: List<T>
)

/**
 * Response untuk login dan register
 */
data class AuthResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("email")
    val email: String,

    @SerializedName("fullName")
    val fullName: String?,

    @SerializedName("role")
    val role: String,

    @SerializedName("token")
    val token: String
)

/**
 * Response sederhana hanya dengan pesan
 */
data class MessageResponse(
    @SerializedName("message")
    val message: String
)

/**
 * Response detail himpunan yang menyertakan admin dan daftar anggota
 */

// Buat model HimpunanResponse untuk mencocokkan format respons API
data class HimpunanResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("count")
    val count: Int,

    @SerializedName("totalPages")
    val totalPages: Int,

    @SerializedName("currentPage")
    val currentPage: Int,

    @SerializedName("data")
    val data: List<Himpunan>
)

// Update HimpunanDetail di Response.kt
data class HimpunanDetail(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("aka")
    val aka: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("logo")
    val logo: String?,

    @SerializedName("foundedDate")
    val foundedDate: String?,

    @SerializedName("status")
    val status: String,

    @SerializedName("contactEmail")
    val contactEmail: String?,

    @SerializedName("contactPhone")
    val contactPhone: String?,

    @SerializedName("address")
    val address: String?,

    @SerializedName("admin")
    val admin: UserMinimal,

    // UBAH: members sekarang langsung User, bukan HimpunanMemberWithUser
    @SerializedName("members")
    val members: List<User>,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)

/**
 * Response detail kegiatan yang menyertakan daftar kehadiran
 */
data class ActivityDetail(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("type")
    val type: String,

    @SerializedName("startDateTime")
    val startDateTime: String,

    @SerializedName("endDateTime")
    val endDateTime: String,

    @SerializedName("location")
    val location: String?,

    @SerializedName("status")
    val status: String,

    @SerializedName("qrCode")
    val qrCode: String?,

    @SerializedName("qrCodeUrl")
    val qrCodeUrl: String?,

    @SerializedName("attendanceMode")
    val attendanceMode: String,

    @SerializedName("himpunanId")
    val himpunanId: Int,

    @SerializedName("himpunan")
    val himpunan: HimpunanMinimal,

    @SerializedName("attendances")
    val attendances: List<AttendanceWithUser>?,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)

/**
 * Response detail kehadiran yang menyertakan user dan kegiatan
 */
data class AttendanceDetail(
    @SerializedName("id")
    val id: Int,

    @SerializedName("checkInTime")
    val checkInTime: String?,

    @SerializedName("checkOutTime")
    val checkOutTime: String?,

    @SerializedName("status")
    val status: String,

    @SerializedName("location")
    val location: String?,

    @SerializedName("notes")
    val notes: String?,

    @SerializedName("user")
    val user: UserMinimal,

    @SerializedName("activity")
    val activity: ActivityMinimal
)

/**
 * Response detail notifikasi yang menyertakan pengirim dan penerima
 */
data class NotificationDetail(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("isRead")
    val isRead: Boolean,

    @SerializedName("priority")
    val priority: String,

    @SerializedName("recipient")
    val recipient: UserMinimal,

    @SerializedName("sender")
    val sender: UserMinimal,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)

/**
 * Response untuk jumlah notifikasi yang belum dibaca
 */
data class UnreadCount(
    @SerializedName("count")
    val count: Int
)

/**
 * Kelas untuk anggota himpunan dengan data user lengkap
 */
data class HimpunanMemberWithUser(
    @SerializedName("id")
    val id: Int,

    @SerializedName("status")
    val status: String,

    @SerializedName("joinDate")
    val joinDate: String,

    @SerializedName("role")
    val role: String,

    @SerializedName("graduationYear")
    val graduationYear: Int?,

    @SerializedName("membershipType")
    val membershipType: String,

    @SerializedName("user")
    val user: UserMinimal
)

/**
 * Kelas untuk kehadiran dengan data user
 */
data class AttendanceWithUser(
    @SerializedName("id")
    val id: Int,

    @SerializedName("checkInTime")
    val checkInTime: String?,

    @SerializedName("checkOutTime")
    val checkOutTime: String?,

    @SerializedName("status")
    val status: String,

    @SerializedName("user")
    val user: UserMinimal
)

data class AdminListResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: List<Admin>?
)

/**
 * Response para detalles de un administrador
 */
data class AdminDetailResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: AdminDetail?
)

data class TaskResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: Task?
)

data class TaskListResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: List<Task>?
)

data class UserWithMembershipResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: User?
)