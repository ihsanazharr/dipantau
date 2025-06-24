package com.example.dipantau.ui.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dipantau.model.Himpunan
import com.example.dipantau.model.HimpunanMinimal
import com.example.dipantau.ui.theme.ProductSans

@Composable
fun HimpunanCard(
    himpunan: HimpunanMinimal, // Ubah tipe parameter menjadi HimpunanMinimal
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = colorScheme.onPrimary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo Himpunan
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(himpunan.logoColor ?: Color.Gray), // Gunakan logoColor dari HimpunanMinimal
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = himpunan.aka.take(2),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    fontFamily = ProductSans
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = himpunan.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = colorScheme.onTertiary,
                    fontFamily = ProductSans
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = himpunan.aka,
                    fontSize = 14.sp,
                    color = colorScheme.onTertiary.copy(alpha = 0.7f),
                    fontFamily = ProductSans
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Tampilkan jumlah anggota
                Text(
                    text = "0 Anggota", // Ganti dengan jumlah anggota yang sesuai
                    fontSize = 12.sp,
                    color = colorScheme.secondary,
                    fontFamily = ProductSans
                )
            }

            // Action Buttons
            Row {
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = colorScheme.primary
                    )
                }

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