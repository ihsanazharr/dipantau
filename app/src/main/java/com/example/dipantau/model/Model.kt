package com.example.dipantau.model

import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName

/**
 * Data model untuk User
 */
// Tambahkan ke Model.kt
data class User(
    @SerializedName("id")
    val id: Int,

    @SerializedName("username")
    val username: String?,

    @SerializedName("email")
    val email: String,

    @SerializedName("fullName")
    val fullName: String?,

    @SerializedName("role")
    val role: String,

    @SerializedName("profilePicture")
    val profilePicture: String?,

    @SerializedName("phoneNumber")
    val phoneNumber: String?,

    @SerializedName("isActive")
    val isActive: Boolean,

    @SerializedName("lastLogin")
    val lastLogin: String?,

    // === FIELDS BARU DARI HIMPUNAN MEMBER ===
    @SerializedName("score")
    val score: Int,

    @SerializedName("himpunanId")
    val himpunanId: Int?,

    @SerializedName("membershipStatus")
    val membershipStatus: String?,

    @SerializedName("joinDate")
    val joinDate: String?,

    @SerializedName("himpunanRole")
    val himpunanRole: String?,

    @SerializedName("graduationYear")
    val graduationYear: Int?,

    @SerializedName("membershipType")
    val membershipType: String?,

    // === RELATIONSHIP ===
    @SerializedName("himpunan")
    val himpunan: HimpunanMinimal?,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)

/**
 * Data model minimal untuk User
 */
data class UserMinimal(
    @SerializedName("id")
    val id: Int,

    @SerializedName("fullName")
    val fullName: String?,

    @SerializedName("email")
    val email: String,

    @SerializedName("profilePicture")
    val profilePicture: String? = null
)

/**
 * Data model untuk Himpunan
 */
data class Himpunan(
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

    @SerializedName("adminId")
    val adminId: Int,

    @SerializedName("admin")
    val admin: UserMinimal?,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)

data class HimpunanListResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: List<Himpunan>?
)

/**
 * Data model minimal untuk Himpunan
 */
data class HimpunanMinimal(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("logo")
    val logo: String?
)

/**
 * Data model untuk Keanggotaan Himpunan
 */
data class HimpunanMember(
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

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("himpunanId")
    val himpunanId: Int,

    @SerializedName("user")
    val user: UserMinimal?,

    @SerializedName("himpunan")
    val himpunan: HimpunanMinimal?,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)


/**
 * Data model untuk Kegiatan
 */
data class Activity(
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

    @SerializedName("attendanceMode")
    val attendanceMode: String,

    @SerializedName("himpunanId")
    val himpunanId: Int,

    @SerializedName("himpunan")
    val himpunan: HimpunanMinimal?,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)

/**
 * Data model minimal untuk Kegiatan
 */
data class ActivityMinimal(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("startDateTime")
    val startDateTime: String,

    @SerializedName("endDateTime")
    val endDateTime: String,

    @SerializedName("himpunan")
    val himpunan: HimpunanMinimal?
)

/**
 * Data model untuk Kehadiran
 */
data class Attendance(
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

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("activityId")
    val activityId: Int,

    @SerializedName("user")
    val user: UserMinimal?,

    @SerializedName("activity")
    val activity: ActivityMinimal?,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)

/**
 * Data model untuk Notifikasi
 */
data class Notification(
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

    @SerializedName("recipientId")
    val recipientId: Int,

    @SerializedName("senderId")
    val senderId: Int,

    @SerializedName("sender")
    val sender: UserMinimal?,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)

data class Admin(
    @SerializedName("id")
    val id: Int,

    @SerializedName("username")
    val username: String?,

    @SerializedName("email")
    val email: String,

    @SerializedName("fullName")
    val fullName: String?,

    @SerializedName("role")
    val role: String,

    @SerializedName("profilePicture")
    val profilePicture: String?,

    @SerializedName("phoneNumber")
    val phoneNumber: String?,

    @SerializedName("isActive")
    val isActive: Boolean,

    @SerializedName("himpunanId")
    val himpunanId: Int?,

    @SerializedName("himpunan")
    val himpunan: HimpunanMinimal?,

    @SerializedName("lastLogin")
    val lastLogin: String?,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)

/**
 * Data model untuk detail Admin (informasi lebih lengkap)
 */
data class AdminDetail(
    @SerializedName("id")
    val id: Int,

    @SerializedName("username")
    val username: String?,

    @SerializedName("email")
    val email: String,

    @SerializedName("fullName")
    val fullName: String?,

    @SerializedName("role")
    val role: String,

    @SerializedName("profilePicture")
    val profilePicture: String?,

    @SerializedName("phoneNumber")
    val phoneNumber: String?,

    @SerializedName("isActive")
    val isActive: Boolean,

    @SerializedName("himpunanId")
    val himpunanId: Int?,

    @SerializedName("himpunan")
    val himpunan: HimpunanMinimal?,

    @SerializedName("lastLogin")
    val lastLogin: String?,

    @SerializedName("permissions")
    val permissions: List<String>?,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)

// Tambahkan ke Model.kt
data class Task(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("status")
    val status: String,

    @SerializedName("priority")
    val priority: String,

    @SerializedName("dueDate")
    val dueDate: String?,

    @SerializedName("startDate")
    val startDate: String?,

    @SerializedName("completionDate")
    val completionDate: String?,

    @SerializedName("scoreReward")
    val scoreReward: Int,

    @SerializedName("category")
    val category: String?,

    @SerializedName("tags")
    val tags: List<String>?,

    @SerializedName("himpunanId")
    val himpunanId: Int,

    @SerializedName("assignedToId")
    val assignedToId: Int?,

    @SerializedName("createdById")
    val createdById: Int,

    // === RELATIONSHIPS ===
    @SerializedName("himpunan")
    val himpunan: HimpunanMinimal?,

    @SerializedName("assignedTo")
    val assignedTo: UserMinimal?,

    @SerializedName("createdBy")
    val createdBy: UserMinimal?,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)

data class TaskMinimal(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("priority")
    val priority: String,

    @SerializedName("dueDate")
    val dueDate: String?,

    @SerializedName("scoreReward")
    val scoreReward: Int
)


data class AdminData(
    val id: String,
    val fullName: String,
    val email: String,
    val role: String,
    val isActive: Boolean,
    val himpunanName: String?,
    val profilePicture: String?,
    val profileColor: androidx.compose.ui.graphics.Color
)

val availableProfileColors = listOf(
    androidx.compose.ui.graphics.Color(0xFF1E88E5), // Blue
    androidx.compose.ui.graphics.Color(0xFF43A047), // Green
    androidx.compose.ui.graphics.Color(0xFFE53935), // Red
    androidx.compose.ui.graphics.Color(0xFF8E24AA), // Purple
    androidx.compose.ui.graphics.Color(0xFF3949AB), // Indigo
    androidx.compose.ui.graphics.Color(0xFF039BE5), // Light Blue
    androidx.compose.ui.graphics.Color(0xFF00ACC1), // Cyan
    androidx.compose.ui.graphics.Color(0xFF7CB342), // Light Green
    androidx.compose.ui.graphics.Color(0xFFFFB300), // Amber
    androidx.compose.ui.graphics.Color(0xFFFB8C00)  // Orange
)

val dummyAdminData = listOf(
    AdminData(
        id = "1",
        fullName = "Admin Sistem",
        email = "admin@example.com",
        role = "SUPER_ADMIN",
        isActive = true,
        himpunanName = null,
        profilePicture = null,
        profileColor = availableProfileColors[0]
    ),
    AdminData(
        id = "2",
        fullName = "Admin Himpunan A",
        email = "admin.a@example.com",
        role = "ADMIN",
        isActive = true,
        himpunanName = "Himpunan Mahasiswa A",
        profilePicture = null,
        profileColor = availableProfileColors[1]
    ),
    AdminData(
        id = "3",
        fullName = "Admin Himpunan B",
        email = "admin.b@example.com",
        role = "ADMIN",
        isActive = false,
        himpunanName = "Himpunan Mahasiswa B",
        profilePicture = null,
        profileColor = availableProfileColors[2]
    )
)