package com.example.dipantau.model.superAdmin

import androidx.compose.ui.graphics.Color

data class HimpunanData(
    val id: String,
    val nama: String,
    val aka: String,
    val deskripsi: String,
    val jumlahAnggota: Int,
    val logoColor: Color
)

val dummyHimpunanData = listOf(
    HimpunanData(
        id = "1",
        nama = "Himpunan Mahasiswa Komputer",
        aka = "HIMAKOM",
        deskripsi = "Himpunan mahasiswa jurusan ilmu komputer dan teknologi informasi",
        jumlahAnggota = 150,
        logoColor = Color(0xFF4CAF50) // Green
    ),
    HimpunanData(
        id = "2",
        nama = "Himpunan Mahasiswa Arsitektur Naval",
        aka = "HMAN",
        deskripsi = "Himpunan mahasiswa jurusan arsitektur kapal dan teknik perkapalan",
        jumlahAnggota = 246,
        logoColor = Color(0xFF2196F3) // Blue
    ),
    HimpunanData(
        id = "3",
        nama = "Himpunan Mahasiswa Kesehatan dan Psikologi",
        aka = "HIMAKAPS",
        deskripsi = "Himpunan mahasiswa jurusan kesehatan masyarakat dan psikologi",
        jumlahAnggota = 142,
        logoColor = Color(0xFFFFC107) // Amber
    ),
    HimpunanData(
        id = "4",
        nama = "Ikatan Mahasiswa Teknik Aeronautika",
        aka = "IMT-AERO",
        deskripsi = "Himpunan mahasiswa jurusan teknik penerbangan dan aeronautika",
        jumlahAnggota = 97,
        logoColor = Color(0xFFE91E63) // Pink
    )
)

// Daftar warna yang dapat digunakan untuk logo himpunan baru
val availableLogoColors = listOf(
    Color(0xFF4CAF50), // Green
    Color(0xFF2196F3), // Blue
    Color(0xFFFFC107), // Amber
    Color(0xFFE91E63), // Pink
    Color(0xFF9C27B0), // Purple
    Color(0xFF3F51B5), // Indigo
    Color(0xFF009688), // Teal
    Color(0xFFFF5722)  // Deep Orange
)