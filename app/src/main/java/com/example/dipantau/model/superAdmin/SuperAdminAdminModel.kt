package com.example.dipantau.model.superAdmin

import androidx.compose.ui.graphics.Color
import com.example.dipantau.model.Himpunan
import com.example.dipantau.model.HimpunanMinimal
import com.example.dipantau.model.User
import com.example.dipantau.model.UserMinimal
import com.google.gson.annotations.SerializedName

data class AdminData(
    val id: String,
    val nama: String,
    val email: String,
    val himpunan: String,
    val jabatan: String,
    val status: String,
    val avatarColor: Color
)

val dummyAdminData = listOf(
    AdminData(
        id = "1",
        nama = "Ahmad Rizky",
        email = "ahmad.rizky@mail.com",
        himpunan = "HIMAKOM",
        jabatan = "Ketua",
        status = "Aktif",
        avatarColor = Color(0xFF4CAF50)
    ),
    AdminData(
        id = "2",
        nama = "Budi Santoso",
        email = "budi.santoso@mail.com",
        himpunan = "HMAN",
        jabatan = "Sekretaris",
        status = "Aktif",
        avatarColor = Color(0xFF2196F3) // Blue
    ),
    AdminData(
        id = "3",
        nama = "Cindy Paramita",
        email = "cindy.paramita@mail.com",
        himpunan = "HIMAKAPS",
        jabatan = "Bendahara",
        status = "Nonaktif",
        avatarColor = Color(0xFFFFC107) // Amber
    ),
    AdminData(
        id = "4",
        nama = "Deni Kurniawan",
        email = "deni.kurniawan@mail.com",
        himpunan = "IMT-AERO",
        jabatan = "Humas",
        status = "Aktif",
        avatarColor = Color(0xFFE91E63) // Pink
    )
)

// Daftar himpunan untuk dropdown
val himpunanList = listOf("HIMAKOM", "HMAN", "HIMAKAPS", "IMT-AERO")

// Daftar jabatan untuk dropdown
val jabatanList = listOf("Ketua", "Wakil Ketua", "Sekretaris", "Bendahara", "Humas", "Anggota")

// Daftar status untuk dropdown
val statusList = listOf("Aktif", "Nonaktif")

// Daftar warna yang dapat digunakan untuk avatar
val availableAvatarColors = listOf(
    Color(0xFF4CAF50), // Green
    Color(0xFF2196F3), // Blue
    Color(0xFFFFC107), // Amber
    Color(0xFFE91E63), // Pink
    Color(0xFF9C27B0), // Purple
    Color(0xFF3F51B5), // Indigo
    Color(0xFF009688), // Teal
    Color(0xFFFF5722)  // Deep Orange
)

///**
// * Model untuk Admin
// */
//data class Admin(
//    @SerializedName("id")
//    val id: Int,
//
//    @SerializedName("position")
//    val position: String,
//
//    @SerializedName("permissions")
//    val permissions: Map<String, Boolean>?,
//
//    @SerializedName("joinedAt")
//    val joinedAt: String,
//
//    @SerializedName("lastActive")
//    val lastActive: String?,
//
//    @SerializedName("profileImage")
//    val profileImage: String?,
//
//    @SerializedName("phoneNumber")
//    val phoneNumber: String?,
//
//    @SerializedName("User")
//    val user: UserMinimal?,
//
//    @SerializedName("Himpunan")
//    val himpunan: HimpunanMinimal?
//)
//
///**
// * Model untuk detail Admin
// */
//data class AdminDetail(
//    @SerializedName("id")
//    val id: Int,
//
//    @SerializedName("position")
//    val position: String,
//
//    @SerializedName("permissions")
//    val permissions: Map<String, Boolean>?,
//
//    @SerializedName("joinedAt")
//    val joinedAt: String,
//
//    @SerializedName("lastActive")
//    val lastActive: String?,
//
//    @SerializedName("profileImage")
//    val profileImage: String?,
//
//    @SerializedName("phoneNumber")
//    val phoneNumber: String?,
//
//    @SerializedName("User")
//    val user: User,
//
//    @SerializedName("Himpunan")
//    val himpunan: Himpunan,
//
//    @SerializedName("createdAt")
//    val createdAt: String,
//
//    @SerializedName("updatedAt")
//    val updatedAt: String
//)
//
//data class AdminRequest(
//    @SerializedName("name")
//    val name: String,
//
//    @SerializedName("email")
//    val email: String,
//
//    @SerializedName("password")
//    val password: String? = null,
//
//    @SerializedName("himpunanId")
//    val himpunanId: Int? = null,
//
//    @SerializedName("position")
//    val position: String? = null,
//
//    @SerializedName("permissions")
//    val permissions: Map<String, Boolean>? = null,
//
//    @SerializedName("phoneNumber")
//    val phoneNumber: String? = null
//)
//
//data class AdminPasswordRequest(
//    @SerializedName("newPassword")
//    val newPassword: String
//)
//
//data class AdminProfileUpdateRequest(
//    @SerializedName("position")
//    val position: String? = null,
//
//    @SerializedName("phoneNumber")
//    val phoneNumber: String? = null,
//
//    @SerializedName("name")
//    val name: String? = null,
//
//    @SerializedName("email")
//    val email: String? = null
//)