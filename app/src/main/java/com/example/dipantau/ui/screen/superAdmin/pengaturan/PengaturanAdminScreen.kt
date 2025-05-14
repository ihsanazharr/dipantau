package com.example.dipantau.ui.screen.superAdmin.pengaturan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun PengaturanAdminScreen(
    onBackPressed: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    var showAddAdminDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Kembali",
                        tint = colorScheme.onTertiary
                    )
                }
                Text(
                    text = "Pengaturan Admin",
                    color = colorScheme.onTertiary,
                    fontFamily = ProductSans,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Add Admin Button
            FilledTonalButton(
                onClick = { showAddAdminDialog = true },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = colorScheme.tertiary,
                    contentColor = colorScheme.onPrimary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Admin"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Tambah Admin",
                    fontFamily = ProductSans
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Admin List
        LazyColumn {
            items(sampleAdmins) { admin ->
                AdminListItem(
                    admin = admin,
                    colorScheme = colorScheme
                )
            }
        }
    }

    if (showAddAdminDialog) {
        AddAdminDialog(
            onDismiss = { showAddAdminDialog = false },
            colorScheme = colorScheme
        )
    }
}

data class Admin(
    val name: String,
    val email: String,
    val role: String
)

private val sampleAdmins = listOf(
    Admin("John Doe", "john.doe@example.com", "Super Admin"),
    Admin("Jane Smith", "jane.smith@example.com", "Admin"),
    Admin("Mark Johnson", "mark.j@example.com", "Admin")
)

@Composable
private fun AdminListItem(
    admin: Admin,
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
            // Admin Avatar
            Surface(
                modifier = Modifier.size(40.dp),
                shape = RoundedCornerShape(20.dp),
                color = colorScheme.tertiary
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Admin Avatar",
                    tint = colorScheme.onPrimary,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = admin.name,
                    color = colorScheme.onTertiary,
                    fontFamily = ProductSans,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = admin.email,
                    color = colorScheme.onSurface,
                    fontFamily = ProductSans,
                    fontSize = 14.sp
                )
                Text(
                    text = admin.role,
                    color = colorScheme.tertiary,
                    fontFamily = ProductSans,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { /* Handle more options */ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More Options",
                    tint = colorScheme.onTertiary
                )
            }
        }
    }
}

@Composable
private fun AddAdminDialog(
    onDismiss: () -> Unit,
    colorScheme: ColorScheme
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Tambah Admin Baru",
                fontFamily = ProductSans,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text("Nama Lengkap") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text("Role") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.tertiary
                )
            ) {
                Text("Tambah")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal")
            }
        }
    )
}