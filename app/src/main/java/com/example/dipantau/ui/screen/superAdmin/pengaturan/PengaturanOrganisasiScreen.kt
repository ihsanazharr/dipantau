package com.example.dipantau.ui.screen.superAdmin.pengaturan

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
fun PengaturanOrganisasiScreen(
    onBackPressed: () -> Unit
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
                text = "Pengaturan Organisasi",
                color = colorScheme.onTertiary,
                fontFamily = ProductSans,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Organization Info Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorScheme.tertiary
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Nama Organisasi",
                    color = colorScheme.onPrimary,
                    fontFamily = ProductSans,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "ID: ORG123456",
                    color = colorScheme.onPrimary.copy(alpha = 0.8f),
                    fontFamily = ProductSans,
                    fontSize = 14.sp
                )
            }
        }

        // Settings Items
        OrganizationSettingItem(
            title = "Profil Organisasi",
            subtitle = "Ubah informasi dasar organisasi",
            icon = Icons.Default.AccountBox,
            colorScheme = colorScheme
        )

        OrganizationSettingItem(
            title = "Struktur Organisasi",
            subtitle = "Atur departemen dan hierarki",
            icon = Icons.Default.Menu,
            colorScheme = colorScheme
        )

        OrganizationSettingItem(
            title = "Manajemen Tim",
            subtitle = "Kelola tim dan anggota",
            icon = Icons.Default.Face,
            colorScheme = colorScheme
        )

        OrganizationSettingItem(
            title = "Kebijakan & Aturan",
            subtitle = "Atur kebijakan organisasi",
            icon = Icons.Default.Build,
            colorScheme = colorScheme
        )
    }
}

@Composable
private fun OrganizationSettingItem(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    colorScheme: ColorScheme
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
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