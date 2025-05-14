package com.example.dipantau.ui.screen.superAdmin.pengaturan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dipantau.R
import com.example.dipantau.ui.theme.ProductSans

@Composable
fun PengaturanScreen(
    onNavigateToAkunKeamanan: () -> Unit,
    onNavigateToPengaturanOrganisasi: () -> Unit,
    onNavigateToPengaturanAdmin: () -> Unit,
    onNavigateToNotifikasi: () -> Unit,
    onLogout: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    var showLogoutDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Foto profil + nama + email
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logodipantau),
                contentDescription = "Foto Profil",
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(colorScheme.onSurface.copy(alpha = 0.2f))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "Nama Pengguna",
                    color = colorScheme.onTertiary,
                    fontFamily = ProductSans,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "email@pengguna.com",
                    color = colorScheme.onSurface,
                    fontFamily = ProductSans,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Menu Items
        PengaturanButton(
            text = "Akun & Keamanan",
            icon = Icons.Default.Lock,
            backgroundColor = colorScheme.onBackground,
            contentColor = colorScheme.onTertiary,
            onClick = onNavigateToAkunKeamanan
        )

        PengaturanButton(
            text = "Pengaturan Organisasi",
            icon = Icons.Default.Settings,
            backgroundColor = colorScheme.onBackground,
            contentColor = colorScheme.onTertiary,
            onClick = onNavigateToPengaturanOrganisasi
        )

        PengaturanButton(
            text = "Pengaturan Admin",
            icon = Icons.Default.Person,
            backgroundColor = colorScheme.onBackground,
            contentColor = colorScheme.onTertiary,
            onClick = onNavigateToPengaturanAdmin
        )

        PengaturanButton(
            text = "Notifikasi & Aktivitas",
            icon = Icons.Default.Notifications,
            backgroundColor = colorScheme.onBackground,
            contentColor = colorScheme.onTertiary,
            onClick = onNavigateToNotifikasi
        )

        PengaturanButton(
            text = "Keluar",
            icon = Icons.Default.ExitToApp,
            backgroundColor = colorScheme.tertiary,
            contentColor = colorScheme.onPrimary,
            onClick = { showLogoutDialog = true }
        )
    }

    // Logout Confirmation Dialog
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = {
                Text(
                    text = "Konfirmasi Keluar",
                    fontFamily = ProductSans,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Apakah Anda yakin ingin keluar dari aplikasi?",
                    fontFamily = ProductSans
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showLogoutDialog = false
                        onLogout()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.tertiary
                    )
                ) {
                    Text("Ya, Keluar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }
}

@Composable
fun PengaturanButton(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    backgroundColor: androidx.compose.ui.graphics.Color,
    contentColor: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = text,
                    tint = contentColor
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = text,
                    color = contentColor,
                    fontFamily = ProductSans,
                    fontSize = 16.sp
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Next",
                tint = contentColor
            )
        }
    }
}