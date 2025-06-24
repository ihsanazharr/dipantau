package com.example.dipantau.ui.screen.superAdmin.kelolaAdmin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dipantau.R
import com.example.dipantau.model.Resource
import com.example.dipantau.model.User // Mengganti Admin dengan User
import com.example.dipantau.ui.component.card.AdminCard
import com.example.dipantau.ui.theme.ProductSans
import com.example.dipantau.viewmodel.AdminViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KelolaAdminScreen(
    navController: NavHostController? = null,
    viewModel: AdminViewModel = hiltViewModel()
) {
    val colorScheme = MaterialTheme.colorScheme
    var searchQuery by remember { mutableStateOf("") }
    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }
    var showResetPasswordDialog by remember { mutableStateOf(false) }
    var showToggleActiveDialog by remember { mutableStateOf(false) }
    var selectedAdmin by remember { mutableStateOf<User?>(null) } // Mengganti Admin dengan User
    var newPassword by remember { mutableStateOf("") }

    val updateState by viewModel.updateAdminResult.collectAsState()
    val deleteState by viewModel.deleteAdminResult.collectAsState()
    val resetPasswordState by viewModel.resetPasswordResult.collectAsState()
    val adminListState by viewModel.adminList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllAdmins()
    }

    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    LaunchedEffect(updateState) {
        updateState?.let {
            when (it) {
                is Resource.Success -> {
                    snackbarMessage = "Admin berhasil diperbarui"
                    showSnackbar = true
                    viewModel.resetUpdateResult()
                }
                is Resource.Error -> {
                    snackbarMessage = it.message ?: "Gagal memperbarui admin"
                    showSnackbar = true
                    viewModel.resetUpdateResult()
                }
                else -> {}
            }
        }
    }

    LaunchedEffect(deleteState) {
        deleteState?.let {
            when (it) {
                is Resource.Success -> {
                    snackbarMessage = "Admin berhasil dihapus"
                    showSnackbar = true
                    viewModel.resetDeleteResult()
                }
                is Resource.Error -> {
                    snackbarMessage = it.message ?: "Gagal menghapus admin"
                    showSnackbar = true
                    viewModel.resetDeleteResult()
                }
                else -> {}
            }
        }
    }

    LaunchedEffect(resetPasswordState) {
        resetPasswordState?.let {
            when (it) {
                is Resource.Success -> {
                    snackbarMessage = "Password admin berhasil direset"
                    showSnackbar = true
                    viewModel.resetResetPasswordResult()
                }
                is Resource.Error -> {
                    snackbarMessage = it.message ?: "Gagal mereset password admin"
                    showSnackbar = true
                    viewModel.resetResetPasswordResult()
                }
                else -> {}
            }
        }
    }

    val adminList = when (val state = adminListState) {
        is Resource.Success -> state.data.data
        else -> emptyList()
    }

    val filteredAdmins = if (searchQuery.isEmpty()) {
        adminList
    } else {
        adminList.filter {
            (it.fullName?.contains(searchQuery, ignoreCase = true) ?: false) ||
                    (it.username?.contains(searchQuery, ignoreCase = true) ?: false) ||
                    it.email.contains(searchQuery, ignoreCase = true) ||
                    it.role.contains(searchQuery, ignoreCase = true) ||
                    (it.himpunan?.name?.contains(searchQuery, ignoreCase = true) ?: false)
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = remember { SnackbarHostState() }.apply {
                if (showSnackbar) {
                    LaunchedEffect(snackbarMessage) {
                        showSnackbar(snackbarMessage)
                        showSnackbar = false
                    }
                }
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        if (it.length >= 3 || it.isEmpty()) {
                            viewModel.getAllAdmins(search = it)
                        }
                    },
                    placeholder = {
                        Text(
                            text = "Cari Admin",
                            color = colorScheme.onSurface.copy(alpha = 0.6f),
                            fontFamily = ProductSans
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Cari",
                            tint = colorScheme.onSurface
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorScheme.onSurface.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorScheme.primary)
                        .clickable {
                            navController?.navigate("admin/add")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Tambah Admin",
                        tint = colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tampilkan loading atau error jika ada
            when (val state = adminListState) {
                is Resource.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is Resource.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = state.message ?: "Terjadi kesalahan",
                            color = colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.getAllAdmins() }) {
                            Text("Coba Lagi")
                        }
                    }
                }
                else -> {}
            }

            // Jika tidak ada data yang sesuai dengan pencarian
            if (filteredAdmins.isEmpty() && adminListState !is Resource.Loading) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.notfound),
                        contentDescription = "Tidak ada data",
                        modifier = Modifier.size(180.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Tidak ada data",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = colorScheme.primary,
                        fontFamily = ProductSans
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Silahkan gunakan kata kunci admin pada kolom pencarian atau tambahkan admin baru",
                        color = colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        fontFamily = ProductSans
                    )
                }
            } else if (adminListState !is Resource.Loading) {
                // Daftar Admin
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(filteredAdmins) { admin ->
                        AdminCard(
                            admin = admin,
                            onEditClick = {
                                // Navigasi ke halaman edit
                                navController?.navigate("admin/edit/${admin.id}")
                            },
                            onDeleteClick = {
                                selectedAdmin = admin
                                showDeleteConfirmationDialog = true
                            },
                            onResetPasswordClick = {
                                selectedAdmin = admin
                                showResetPasswordDialog = true
                            },
                            onToggleActiveClick = {
                                selectedAdmin = admin
                                showToggleActiveDialog = true
                            }
                        )
                    }
                }
            }
        }
    }

    // Dialog konfirmasi hapus
    if (showDeleteConfirmationDialog && selectedAdmin != null) {
        DeleteConfirmationDialog(
            adminName = selectedAdmin!!.fullName ?: selectedAdmin!!.username ?: selectedAdmin!!.email,
            onDismiss = { showDeleteConfirmationDialog = false },
            onConfirm = {
                viewModel.deleteAdmin(selectedAdmin!!.id)
                showDeleteConfirmationDialog = false
            }
        )
    }

    // Dialog untuk reset password
    if (showResetPasswordDialog && selectedAdmin != null) {
        ResetPasswordDialog(
            adminName = selectedAdmin!!.fullName ?: selectedAdmin!!.username ?: selectedAdmin!!.email,
            password = newPassword,
            onPasswordChange = { newPassword = it },
            onDismiss = {
                showResetPasswordDialog = false
                newPassword = ""
            },
            onConfirm = {
                viewModel.resetAdminPassword(selectedAdmin!!.id, newPassword)
                showResetPasswordDialog = false
                newPassword = ""
            }
        )
    }

    // Dialog untuk aktivasi/deaktivasi admin
    if (showToggleActiveDialog && selectedAdmin != null) {
        ToggleActiveDialog(
            admin = selectedAdmin!!,
            onDismiss = { showToggleActiveDialog = false },
            onConfirm = {
                // Menggunakan fungsi update untuk mengubah status aktif
                viewModel.updateAdmin(
                    id = selectedAdmin!!.id,
                    isActive = !selectedAdmin!!.isActive
                )
                showToggleActiveDialog = false
            }
        )
    }
}

@Composable
fun DeleteConfirmationDialog(
    adminName: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = colorScheme.onPrimary,
        title = {
            Text(
                text = "Hap极 Admin",
                fontFamily = ProductSans,
                fontWeight = FontWeight.Bold,
                color = colorScheme.onTertiary
            )
        },
        text = {
            Text(
                text = "Apakah Anda yakin ingin menghapus admin '$adminName'? Tindakan ini tidak dapat dibatalkan.",
                fontFamily = ProductSans,
                color = colorScheme.onTertiary
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.surface
                )
            ) {
                Text(
                    "Hapus",
                    fontFamily = ProductSans,
                    color = colorScheme.onPrimary
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.tertiary
                )
            ) {
                Text(
                    "Batal",
                    fontFamily = ProductSans,
                    color = colorScheme.onPrimary
                )
            }
        }
    )
}

@Composable
fun ResetPasswordDialog(
    adminName: String,
    password: String,
    onPasswordChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = colorScheme.onPrimary,
        title = {
            Text(
                text = "Reset Password Admin",
                fontFamily = ProductSans,
                fontWeight = FontWeight.Bold,
                color = colorScheme.onTertiary
            )
        },
        text = {
            Column {
                Text(
                    text = "Masukkan password baru untuk admin '$adminName'",
                    fontFamily = ProductSans,
                    color = colorScheme.onTertiary
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = onPasswordChange,
                    label = { Text("Password Baru") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                enabled = password.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.primary
                )
            ) {
                Text(
                    "Reset Password",
                    fontFamily = ProductSans,
                    color = colorScheme.onPrimary
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.tertiary
                )
            ) {
                Text(
                    "Batal",
                    fontFamily = ProductSans,
                    color = colorScheme.onPrimary
                )
            }
        }
    )
}

@Composable
fun ToggleActiveDialog(
    admin: User, // Pastikan ini adalah User
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val isActive = admin.isActive
    val adminName = admin.fullName ?: admin.username ?: admin.email

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = colorScheme.onPrimary,
        title = {
            Text(
                text = if (isActive) "Nonaktifkan Admin" else "Aktifkan Admin",
                fontFamily = ProductSans,
                fontWeight = FontWeight.Bold,
                color = colorScheme.onTertiary
            )
        },
        text = {
            Text(
                text = if (isActive)
                    "Apakah Anda yakin ingin menonaktifkan admin '$adminName'?"
                else
                    "Apakah Anda yakin ingin mengaktifkan kembali admin '$adminName'?",
                fontFamily = ProductSans,
                color = colorScheme.onTertiary
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isActive) Color(0xFFE57373) else Color(0xFF4CAF50)
                )
            ) {
                Text(
                    if (isActive) "Nonaktifkan" else "Aktifkan",
                    fontFamily = ProductSans,
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.tertiary
                )
            ) {
                Text(
                    "Batal",
                    fontFamily = ProductSans,
                    color = colorScheme.onPrimary
                )
            }
        }
    )
}

