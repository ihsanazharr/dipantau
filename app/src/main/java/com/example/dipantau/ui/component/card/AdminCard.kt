package com.example.dipantau.ui.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.dipantau.model.User // Menggunakan model User
import com.example.dipantau.ui.theme.ProductSans

@Composable
fun AdminCard(
    admin: User, // Menggunakan model User
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onResetPasswordClick: () -> Unit = {},
    onToggleActiveClick: () -> Unit = {}
) {
    val colorScheme = MaterialTheme.colorScheme

    // Mengambil inisial dari nama lengkap atau username
    val initialText = admin.fullName?.take(1)?.uppercase() ?:
    admin.username?.take(1)?.uppercase() ?:
    admin.email.take(1).uppercase()

    // Menentukan warna acak untuk avatar jika tidak ada foto profil
    val avatarColor = Color(
        red = (admin.id * 123) % 256 / 255f,
        green = (admin.id * 456) % 256 / 255f,
        blue = (admin.id * 789) % 256 / 255f
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = colorScheme.onPrimary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            // Informasi utama admin
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar/Foto profil
                Box(
                    modifier = Modifier.size(56.dp).clip(CircleShape).background(avatarColor),
                    contentAlignment = Alignment.Center
                ) {
                    if (admin.profilePicture != null) {
                        AsyncImage(
                            model = admin.profilePicture,
                            contentDescription = "Foto profil",
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Text(
                            text = initialText,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            fontFamily = ProductSans
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Informasi admin
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = admin.fullName ?: admin.username ?: admin.email,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = colorScheme.onTertiary,
                        fontFamily = ProductSans
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = admin.email,
                        fontSize = 14.sp,
                        color = colorScheme.onTertiary.copy(alpha = 0.7f),
                        fontFamily = ProductSans
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Badge role
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = when (admin.role) {
                                "SUPER_ADMIN" -> colorScheme.primary
                                else -> colorScheme.secondary
                            },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Text(
                                text = admin.role,
                                color = Color.White,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontFamily = ProductSans
                            )
                        }

                        // Badge status aktif/nonaktif
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (admin.isActive) Color(0xFF4CAF50) else Color(0xFFE57373),
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Text(
                                text = if (admin.isActive) "Aktif" else "Nonaktif",
                                color = Color.White,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontFamily = ProductSans
                            )
                        }
                    }

                    // Tampilkan nomor telepon jika ada
                    admin.phoneNumber?.let {
                        if (it.isNotBlank()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Telp: $it",
                                fontSize = 14.sp,
                                color = colorScheme.onTertiary.copy(alpha = 0.6f),
                                fontFamily = ProductSans
                            )
                        }
                    }

                    // Tampilkan waktu login terakhir jika ada
                    admin.lastLogin?.let {
                        if (it.isNotBlank()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Login terakhir: $it",
                                fontSize = 12.sp,
                                color = colorScheme.onTertiary.copy(alpha = 0.5f),
                                fontFamily = ProductSans
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol-tombol aksi
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                // Tombol toggle aktif/nonaktif
                IconButton(onClick = onToggleActiveClick) {
                    Icon(
                        imageVector = if (admin.isActive) Icons.Default.Person else Icons.Default.AccountBox,
                        contentDescription = if (admin.isActive) "Nonaktifkan" else "Aktifkan",
                        tint = if (admin.isActive) Color(0xFFE57373) else Color(0xFF4CAF50)
                    )
                }

                // Tombol reset password
                IconButton(onClick = onResetPasswordClick) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Reset Password",
                        tint = colorScheme.tertiary
                    )
                }

                // Tombol edit
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = colorScheme.primary
                    )
                }

                // Tombol hapus
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Hapus",
                        tint = colorScheme.error
                    )
                }
            }
        }
    }
}
