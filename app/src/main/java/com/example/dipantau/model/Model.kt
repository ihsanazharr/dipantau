package com.example.dipantau.model

import com.google.gson.annotations.SerializedName

// Model untuk User
data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String?,
    @SerializedName("email") val email: String,
    @SerializedName("fullName") val fullName: String?,
    @SerializedName("role") val role: String,
    @SerializedName("profilePicture") val profilePicture: String?,
    @SerializedName("phoneNumber") val phoneNumber: String?,
    @SerializedName("isActive") val isActive: Boolean,
    @SerializedName("lastLogin") val lastLogin: String?,
    @SerializedName("score") val score: Int,
    @SerializedName("himpunanId") val himpunanId: Int?,
    @SerializedName("membershipStatus") val membershipStatus: String?,
    @SerializedName("joinDate") val joinDate: String?,
    @SerializedName("himpunanRole") val himpunanRole: String?,
    @SerializedName("graduationYear") val graduationYear: Int?,
    @SerializedName("membershipType") val membershipType: String?,
    @SerializedName("himpunan") val himpunan: HimpunanMinimal?,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String
)

// Model untuk Himpunan
data class Himpunan(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("aka") val aka: String,
    @SerializedName("description") val description: String?,
    @SerializedName("logo") val logo: String?,
    @SerializedName("foundedDate") val foundedDate: String?,
    @SerializedName("status") val status: String,
    @SerializedName("contactEmail") val contactEmail: String?,
    @SerializedName("contactPhone") val contactPhone: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("adminId") val adminId: Int,
    @SerializedName("admin") val admin: UserMinimal?,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    val logoColor: androidx.compose.ui.graphics.Color? = null // Tambahkan properti ini
)

// Model untuk Himpunan Minimal
data class HimpunanMinimal(
    val id: Int,
    val name: String,
    val aka: String,
    val description: String?,
    val logoColor: androidx.compose.ui.graphics.Color?
)


// Model untuk Task
data class Task(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("status") val status: String,
    @SerializedName("priority") val priority: String,
    @SerializedName("dueDate") val dueDate: String?,
    @SerializedName("startDate") val startDate: String?,
    @SerializedName("completionDate") val completionDate: String?,
    @SerializedName("scoreReward") val scoreReward: Int,
    @SerializedName("category") val category: String?,
    @SerializedName("tags") val tags: List<String>?,
    @SerializedName("himpunanId") val himpunanId: Int,
    @SerializedName("assignedToId") val assignedToId: Int?,
    @SerializedName("createdById") val createdById: Int,
    @SerializedName("himpunan") val himpunan: HimpunanMinimal?,
    @SerializedName("assignedTo") val assignedTo: UserMinimal?,
    @SerializedName("createdBy") val createdBy: UserMinimal?,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String
)

// Model untuk Activity
data class Activity(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("type") val type: String,
    @SerializedName("startDateTime") val startDateTime: String,
    @SerializedName("endDateTime") val endDateTime: String,
    @SerializedName("location") val location: String?,
    @SerializedName("status") val status: String,
    @SerializedName("attendanceMode") val attendanceMode: String,
    @SerializedName("himpunanId") val himpunanId: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String
)

// Model untuk Attendance
data class Attendance(
    @SerializedName("id") val id: Int,
    @SerializedName("checkInTime") val checkInTime: String?,
    @SerializedName("checkOutTime") val checkOutTime: String?,
    @SerializedName("status") val status: String,
    @SerializedName("location") val location: String?,
    @SerializedName("notes") val notes: String?,
    @SerializedName("userId") val userId: Int,
    @SerializedName("activityId") val activityId: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String
)


// Model untuk Notifikasi
data class Notification(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("type") val type: String,
    @SerializedName("isRead") val isRead: Boolean,
    @SerializedName("priority") val priority: String,
    @SerializedName("recipientId") val recipientId: Int,
    @SerializedName("senderId") val senderId: Int,
    @SerializedName("sender") val sender: UserMinimal?,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String
)

// Model untuk User Minimal
data class UserMinimal(
    @SerializedName("id") val id: Int,
    @SerializedName("fullName") val fullName: String?,
    @SerializedName("email") val email: String,
    @SerializedName("profilePicture") val profilePicture: String? = null
)

// Model untuk Activity Minimal
data class ActivityMinimal(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("startDateTime") val startDateTime: String,
    @SerializedName("endDateTime") val endDateTime: String,
    @SerializedName("himpunan") val himpunan: HimpunanMinimal?
)

data class TaskMinimal(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("status") val status: String,
    @SerializedName("priority") val priority: String,
    @SerializedName("dueDate") val dueDate: String?,
    @SerializedName("scoreReward") val scoreReward: Int
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

// Tambahkan ini di dalam file Model.kt
val availableLogoColors = listOf(
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