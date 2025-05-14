package com.example.dipantau.ui.screen.superAdmin.pengaturan.akunKeamanan

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.dipantau.R
import com.example.dipantau.ui.theme.ProductSans

@Composable
fun EditProfileScreen(
    onBackPressed: () -> Unit,
    onSaveProfile: () -> Unit
) {
    var name by remember { mutableStateOf("Nama Admin") }
    var email by remember { mutableStateOf("admin@email.com") }
    var showSaveDialog by remember { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    val colorScheme = MaterialTheme.colorScheme

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { selectedImageUri = it }
    }

    val imageModifier = Modifier
        .size(120.dp)
        .clip(CircleShape)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar with Save Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
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
                text = "Edit Profil",
                color = colorScheme.onTertiary,
                fontFamily = ProductSans,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = { showSaveDialog = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.secondary
                )
            ) {
                Text(
                    text = "Simpan",
                    fontFamily = ProductSans
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Profile Picture Section
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(colorScheme.onSurface.copy(alpha = 0.1f))
                    .border(2.dp, colorScheme.secondary, CircleShape)
                    .clickable { galleryLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (selectedImageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(context)
                                .data(data = selectedImageUri)
                                .build()
                        ),
                        contentDescription = "Selected Profile Picture",
                        modifier = imageModifier,
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.logodipantau),
                        contentDescription = "Profile Picture",
                        modifier = imageModifier,
                        contentScale = ContentScale.Crop
                    )
                }

                // Camera Icon Overlay
                Surface(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = (-8).dp, y = (-8).dp),
                    shape = CircleShape,
                    color = colorScheme.secondary
                ) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Change Photo",
                        tint = colorScheme.onPrimary,
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Ubah Foto Profil",
                color = colorScheme.secondary,
                fontFamily = ProductSans,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable { galleryLauncher.launch("image/*") }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Form Fields
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorScheme.onBackground
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Name Field
                Column {
                    Text(
                        text = "Nama Lengkap",
                        color = colorScheme.onTertiary,
                        fontFamily = ProductSans,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = colorScheme.secondary,
                            unfocusedBorderColor = colorScheme.onTertiary.copy(alpha = 0.5f)
                        ),
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Name",
                                tint = colorScheme.onTertiary
                            )
                        }
                    )
                }

                Column {
                    Text(
                        text = "Email",
                        color = colorScheme.onTertiary,
                        fontFamily = ProductSans,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = colorScheme.secondary,
                            unfocusedBorderColor = colorScheme.onTertiary.copy(alpha = 0.5f)
                        ),
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email",
                                tint = colorScheme.onTertiary
                            )
                        }
                    )
                }
            }
        }
    }

    if (showSaveDialog) {
        AlertDialog(
            onDismissRequest = { showSaveDialog = false },
            title = {
                Text(
                    text = "Simpan Perubahan",
                    fontFamily = ProductSans,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Apakah Anda yakin ingin menyimpan perubahan profil?",
                    fontFamily = ProductSans
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSaveDialog = false
                        onSaveProfile()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.secondary
                    )
                ) {
                    Text("Ya, Simpan")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSaveDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }
}