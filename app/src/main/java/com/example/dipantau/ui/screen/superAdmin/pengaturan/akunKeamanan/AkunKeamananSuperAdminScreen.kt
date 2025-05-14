package com.example.dipantau.ui.screen.superAdmin.pengaturan.akunKeamanan

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dipantau.ui.theme.ProductSans

@Composable
fun AkunKeamananScreen(
    onBackPressed: () -> Unit,
    onNavigateToEditProfile: () -> Unit,
    onNavigateToChangePassword: () -> Unit,
    onNavigateToTwoFactor: () -> Unit,
    onNavigateToLoginHistory: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Kembali",
                    tint = colorScheme.onTertiary
                )
            }
            Text(
                text = "Akun & Keamanan",
                color = colorScheme.onTertiary,
                fontFamily = ProductSans,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        SecuritySettingItem(
            title = "Ubah Profil",
            subtitle = "Sunting profil akun Anda",
            icon = Icons.Default.AccountCircle,
            colorScheme = colorScheme,
            onClick = onNavigateToEditProfile
        )

        SecuritySettingItem(
            title = "Ubah Password",
            subtitle = "Ganti password akun Anda",
            icon = Icons.Default.Lock,
            colorScheme = colorScheme,
            onClick = onNavigateToChangePassword
        )

        SecuritySettingItem(
            title = "Autentikasi Dua Faktor",
            subtitle = "Tambahkan lapisan keamanan ekstra",
            icon = Icons.Default.Build,
            colorScheme = colorScheme,
            onClick = onNavigateToTwoFactor
        )

        SecuritySettingItem(
            title = "Riwayat Login",
            subtitle = "Lihat aktivitas login akun",
            icon = Icons.Default.Refresh,
            colorScheme = colorScheme,
            onClick = onNavigateToLoginHistory
        )
    }
}

@Composable
private fun SecuritySettingItem(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    colorScheme: ColorScheme,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.onBackground
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = colorScheme.onTertiary,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = title,
                    color = colorScheme.onTertiary,
                    fontFamily = ProductSans,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = subtitle,
                    color = colorScheme.onSurface,
                    fontFamily = ProductSans,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Next",
                tint = colorScheme.onTertiary
            )
        }
    }
}