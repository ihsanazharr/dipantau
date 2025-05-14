package com.example.dipantau.ui.screen.superAdmin.dashboard.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dipantau.ui.theme.ProductSans

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailDataPenggunaScreen(navController: NavHostController) {
    val colorScheme = MaterialTheme.colorScheme
    var searchQuery by remember { mutableStateOf("") }

    // Data pengguna contoh
    val daftarPengguna = remember {
        listOf(
            PenggunaData("Ahmad Rizky", "HIMAKOM", "Admin", "Aktif"),
            PenggunaData("Budi Santoso", "HMAN", "User", "Aktif"),
            PenggunaData("Cindy Paramita", "HIMAKAPS", "Admin", "Nonaktif"),
            PenggunaData("Deni Kurniawan", "IMT-AERO", "User", "Aktif"),
            PenggunaData("Eka Putri", "HIMAKOM", "User", "Aktif"),
            PenggunaData("Faisal Rahman", "HMAN", "Admin", "Nonaktif"),
            PenggunaData("Gita Nirmala", "HIMAKAPS", "User", "Aktif"),
            PenggunaData("Hadi Wijaya", "IMT-AERO", "Admin", "Aktif")
        )
    }

    val filteredPengguna = if (searchQuery.isEmpty()) {
        daftarPengguna
    } else {
        daftarPengguna.filter {
            it.nama.contains(searchQuery, ignoreCase = true) ||
                    it.himpunan.contains(searchQuery, ignoreCase = true) ||
                    it.role.contains(searchQuery, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
    ) {
        // Top App Bar
        SmallTopAppBar(
            title = {
                Text(
                    text = "Detail Data Pengguna",
                    fontFamily = ProductSans,
                    color = colorScheme.onTertiary
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Kembali",
                        tint = colorScheme.onTertiary
                    )
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = colorScheme.background
            )
        )

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Cari pengguna...", fontFamily = ProductSans) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = colorScheme.secondary
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorScheme.primary,
                unfocusedBorderColor = colorScheme.outline,
                cursorColor = colorScheme.primary,
                containerColor = colorScheme.onPrimary
            )
        )

        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Nama",
                fontWeight = FontWeight.Bold,
                fontFamily = ProductSans,
                color = colorScheme.onTertiary,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Himpunan",
                fontWeight = FontWeight.Bold,
                fontFamily = ProductSans,
                color = colorScheme.onTertiary,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Role",
                fontWeight = FontWeight.Bold,
                fontFamily = ProductSans,
                color = colorScheme.onTertiary,
                modifier = Modifier.weight(0.8f)
            )
            Text(
                text = "Status",
                fontWeight = FontWeight.Bold,
                fontFamily = ProductSans,
                color = colorScheme.onTertiary,
                modifier = Modifier.weight(0.8f)
            )
            Spacer(modifier = Modifier.width(48.dp)) // Ruang untuk tombol aksi
        }

        // Divider
        Divider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = colorScheme.outline.copy(alpha = 0.5f)
        )

        // Daftar Pengguna
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            items(filteredPengguna) { pengguna ->
                PenggunaItem(
                    penggunaData = pengguna,
                    onEditClick = { /* Implementasi edit */ },
                    onDeleteClick = { /* Implementasi delete */ }
                )
                Divider(
                    color = colorScheme.outline.copy(alpha = 0.3f)
                )
            }
        }

        // Floating Action Button untuk Tambah Pengguna
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                onClick = { /* Implementasi tambah pengguna */ },
                containerColor = colorScheme.primary,
                contentColor = colorScheme.onPrimary
            ) {
                Text(
                    text = "+",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun PenggunaItem(
    penggunaData: PenggunaData,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = penggunaData.nama,
            fontFamily = ProductSans,
            color = colorScheme.onTertiary,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = penggunaData.himpunan,
            fontFamily = ProductSans,
            color = colorScheme.onTertiary,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = penggunaData.role,
            fontFamily = ProductSans,
            color = colorScheme.onTertiary,
            modifier = Modifier.weight(0.8f)
        )

        // Badge Status
        Box(
            modifier = Modifier
                .weight(0.8f)
                .padding(end = 8.dp)
        ) {
            val statusColor = if (penggunaData.status == "Aktif") {
                Color(0xFF4CAF50) // Green
            } else {
                Color(0xFFF44336) // Red
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(statusColor.copy(alpha = 0.2f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = penggunaData.status,
                    color = statusColor,
                    fontFamily = ProductSans,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Action Buttons
        Row {
            IconButton(
                onClick = onEditClick,
                modifier = Modifier.size(24.dp)
            ) {
                // Edit icon (gunakan Icon jika tersedia)
                Text(
                    text = "✎",
                    color = colorScheme.primary,
                    fontSize = 16.sp
                )
            }

            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.size(24.dp)
            ) {
                // Delete icon (gunakan Icon jika tersedia)
                Text(
                    text = "✖",
                    color = Color(0xFFF44336),
                    fontSize = 16.sp
                )
            }
        }
    }
}

data class PenggunaData(
    val nama: String,
    val himpunan: String,
    val role: String,
    val status: String
)