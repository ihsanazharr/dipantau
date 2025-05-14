package com.example.dipantau.ui.screen.superAdmin.kelolaHimpunan

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
import com.example.dipantau.model.superAdmin.HimpunanData
import com.example.dipantau.model.superAdmin.availableLogoColors
import com.example.dipantau.model.superAdmin.dummyHimpunanData
import com.example.dipantau.ui.component.card.HimpunanCard
import com.example.dipantau.ui.theme.ProductSans
import com.example.dipantau.viewmodel.HimpunanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KelolaHimpunanScreen(
    navController: NavHostController? = null,
    viewModel: HimpunanViewModel = hiltViewModel()
) {
    val colorScheme = MaterialTheme.colorScheme
    var searchQuery by remember { mutableStateOf("") }
    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }
    var selectedHimpunan by remember { mutableStateOf<HimpunanData?>(null) }

    val updateState by viewModel.updateHimpunanResult.collectAsState()
    val deleteState by viewModel.deleteHimpunanResult.collectAsState()
    val himpunanState by viewModel.himpunanList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllHimpunan()
    }

    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    LaunchedEffect(updateState) {
        updateState?.let {
            when (it) {
                is Resource.Success -> {
                    snackbarMessage = "Himpunan berhasil diperbarui"
                    showSnackbar = true
                    viewModel.resetUpdateResult()
                }
                is Resource.Error -> {
                    snackbarMessage = it.message ?: "Gagal memperbarui himpunan"
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
                    snackbarMessage = "Himpunan berhasil dihapus"
                    showSnackbar = true
                    viewModel.resetDeleteResult()
                }
                is Resource.Error -> {
                    snackbarMessage = it.message ?: "Gagal menghapus himpunan"
                    showSnackbar = true
                    viewModel.resetDeleteResult()
                }
                else -> {}
            }
        }
    }

    val himpunanList = remember(himpunanState) {
        when (val state = himpunanState) {
            is Resource.Success -> state.data.data.map { himpunan ->
                HimpunanData(
                    id = himpunan.id.toString(),
                    nama = himpunan.name,
                    aka = himpunan.aka,
                    deskripsi = himpunan.description ?: "",
                    jumlahAnggota = 0,
                    logoColor = availableLogoColors.random()
                )
            }
            else -> dummyHimpunanData
        }
    }

    val filteredHimpunan = if (searchQuery.isEmpty()) {
        himpunanList
    } else {
        himpunanList.filter {
            it.nama.contains(searchQuery, ignoreCase = true) ||
                    it.aka.contains(searchQuery, ignoreCase = true)
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
                        viewModel.getAllHimpunan(search = it)
                    },
                    placeholder = {
                        Text(
                            text = "Cari Himpunan",
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
                            navController?.navigate("himpunan/add")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Tambah Himpunan",
                        tint = colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tampilkan loading atau error jika ada
            when (val state = himpunanState) {
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
                        Button(onClick = { viewModel.getAllHimpunan() }) {
                            Text("Coba Lagi")
                        }
                    }
                }
                else -> {}
            }

            // Jika tidak ada data yang sesuai dengan pencarian
            if (filteredHimpunan.isEmpty() && himpunanState !is Resource.Loading) {
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
                        text = "Silahkan gunakan kata kunci himpunan pada kolom pencarian atau tambahkan himpunan baru",
                        color = colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        fontFamily = ProductSans
                    )
                }
            } else if (himpunanState !is Resource.Loading) {
                // Daftar Himpunan
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(filteredHimpunan) { himpunan ->
                        HimpunanCard(
                            himpunan = himpunan,
                            onEditClick = {
                                // Navigasi ke halaman edit
                                navController?.navigate("himpunan/edit/${himpunan.id}")
                            },
                            onDeleteClick = {
                                selectedHimpunan = himpunan
                                showDeleteConfirmationDialog = true
                            }
                        )
                    }
                }
            }
        }
    }

    // Dialog konfirmasi hapus
    if (showDeleteConfirmationDialog && selectedHimpunan != null) {
        DeleteConfirmationDialog(
            himpunanName = selectedHimpunan!!.nama,
            onDismiss = { showDeleteConfirmationDialog = false },
            onConfirm = {
                viewModel.deleteHimpunan(selectedHimpunan!!.id.toInt())
                showDeleteConfirmationDialog = false
            }
        )
    }
}

@Composable
fun DeleteConfirmationDialog(
    himpunanName: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = colorScheme.onPrimary,
        title = {
            Text(
                text = "Hapus Himpunan",
                fontFamily = ProductSans,
                fontWeight = FontWeight.Bold,
                color = colorScheme.onTertiary
            )
        },
        text = {
            Text(
                text = "Apakah Anda yakin ingin menghapus himpunan '$himpunanName'? Tindakan ini tidak dapat dibatalkan.",
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