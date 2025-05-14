package com.example.dipantau.ui.screen.superAdmin.kelolaHimpunan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dipantau.model.Himpunan
import com.example.dipantau.model.Resource
import com.example.dipantau.ui.theme.ProductSans
import com.example.dipantau.viewmodel.HimpunanViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditHimpunanScreen(
    himpunanId: Int,
    onNavigateBack: () -> Unit,
    viewModel: HimpunanViewModel = hiltViewModel()
) {
    val colorScheme = MaterialTheme.colorScheme
    val scrollState = rememberScrollState()

    val updateState by viewModel.updateHimpunanResult.collectAsState()
    val himpunanListState by viewModel.himpunanList.collectAsState()

    val currentHimpunan = remember(himpunanListState) {
        if (himpunanListState is Resource.Success) {
            val pagedResponse = (himpunanListState as Resource.Success<*>).data
            val dataList = (pagedResponse as? com.example.dipantau.model.PagedResponse<Himpunan>)?.data
            dataList?.find { it.id == himpunanId }
        } else null
    }

    LaunchedEffect(Unit) {
        viewModel.getAllHimpunan()
    }

    LaunchedEffect(updateState) {
        if (updateState is Resource.Success) {
            viewModel.resetUpdateResult()
            onNavigateBack()
        }
    }

    // State untuk form
    var himpunanName by remember { mutableStateOf("") }
    var himpunanSingkatan by remember { mutableStateOf("") } // aka
    var himpunanDeskripsi by remember { mutableStateOf("") } // description
    var himpunanContactEmail by remember { mutableStateOf("") }
    var himpunanContactPhone by remember { mutableStateOf("") }
    var himpunanAddress by remember { mutableStateOf("") }
    var himpunanFoundedDate by remember { mutableStateOf("") }

    LaunchedEffect(currentHimpunan) {
        currentHimpunan?.let {
            himpunanName = it.name
            himpunanSingkatan = it.aka
            himpunanDeskripsi = it.description ?: ""
            himpunanContactEmail = it.contactEmail ?: ""
            himpunanContactPhone = it.contactPhone ?: ""
            himpunanAddress = it.address ?: ""
            himpunanFoundedDate = it.foundedDate ?: ""
        }
    }

    var nameError by remember { mutableStateOf<String?>(null) }
    var singkatanError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var dateError by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Edit Himpunan",
                        fontFamily = ProductSans,
                        color = colorScheme.onPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali",
                            tint = colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Menampilkan loading atau error
                when (val state = updateState) {
                    is Resource.Loading -> {
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    is Resource.Error -> {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = colorScheme.errorContainer)
                        ) {
                            Text(
                                text = state.message ?: "Terjadi kesalahan",
                                color = colorScheme.onErrorContainer,
                                modifier = Modifier.padding(16.dp),
                                fontFamily = ProductSans
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    else -> {}
                }

                Text(
                    text = "Informasi Dasar",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = colorScheme.primary,
                    fontFamily = ProductSans
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Wajib diisi
                OutlinedTextField(
                    value = himpunanName,
                    onValueChange = {
                        himpunanName = it
                        nameError = if (it.isBlank()) "Nama himpunan tidak boleh kosong" else null
                    },
                    label = { Text("Nama Himpunan *", fontFamily = ProductSans) },
                    placeholder = { Text("Masukkan nama himpunan", fontFamily = ProductSans) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = nameError != null,
                    supportingText = { nameError?.let { Text(it, fontFamily = ProductSans) } },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorScheme.primary,
                        unfocusedBorderColor = colorScheme.outline,
                        errorBorderColor = colorScheme.error
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = himpunanSingkatan,
                    onValueChange = {
                        himpunanSingkatan = it
                        singkatanError = if (it.isBlank()) "Singkatan tidak boleh kosong" else null
                    },
                    label = { Text("Singkatan/Akronim (AKA) *", fontFamily = ProductSans) },
                    placeholder = { Text("Masukkan singkatan himpunan", fontFamily = ProductSans) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = singkatanError != null,
                    supportingText = { singkatanError?.let { Text(it, fontFamily = ProductSans) } },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorScheme.primary,
                        unfocusedBorderColor = colorScheme.outline,
                        errorBorderColor = colorScheme.error
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = himpunanDeskripsi,
                    onValueChange = { himpunanDeskripsi = it },
                    label = { Text("Deskripsi", fontFamily = ProductSans) },
                    placeholder = { Text("Masukkan deskripsi himpunan", fontFamily = ProductSans) },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    maxLines = 3,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorScheme.primary,
                        unfocusedBorderColor = colorScheme.outline
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Informasi Tambahan",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = colorScheme.primary,
                    fontFamily = ProductSans
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = himpunanFoundedDate,
                    onValueChange = {
                        himpunanFoundedDate = it
                        // Validasi format tanggal (YYYY-MM-DD)
                        dateError = try {
                            if (it.isNotBlank()) {
                                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                                sdf.isLenient = false
                                sdf.parse(it)
                                null
                            } else null
                        } catch (e: Exception) {
                            "Format tanggal harus YYYY-MM-DD"
                        }
                    },
                    label = { Text("Tanggal Pendirian", fontFamily = ProductSans) },
                    placeholder = { Text("YYYY-MM-DD", fontFamily = ProductSans) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = dateError != null,
                    supportingText = { dateError?.let { Text(it, fontFamily = ProductSans) } },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorScheme.primary,
                        unfocusedBorderColor = colorScheme.outline,
                        errorBorderColor = colorScheme.error
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = himpunanContactEmail,
                    onValueChange = {
                        himpunanContactEmail = it
                        // Validasi format email
                        emailError = if (it.isNotBlank() && !android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()) {
                            "Format email tidak valid"
                        } else null
                    },
                    label = { Text("Email Kontak", fontFamily = ProductSans) },
                    placeholder = { Text("contoh@mail.com", fontFamily = ProductSans) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    isError = emailError != null,
                    supportingText = { emailError?.let { Text(it, fontFamily = ProductSans) } },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorScheme.primary,
                        unfocusedBorderColor = colorScheme.outline,
                        errorBorderColor = colorScheme.error
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = himpunanContactPhone,
                    onValueChange = {
                        himpunanContactPhone = it
                        // Validasi format nomor telepon
                        phoneError = if (it.isNotBlank() && !it.matches(Regex("^[0-9+\\-\\s()]{6,15}$"))) {
                            "Format nomor telepon tidak valid"
                        } else null
                    },
                    label = { Text("Nomor Telepon", fontFamily = ProductSans) },
                    placeholder = { Text("08xxxxxxxxxx", fontFamily = ProductSans) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    isError = phoneError != null,
                    supportingText = { phoneError?.let { Text(it, fontFamily = ProductSans) } },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorScheme.primary,
                        unfocusedBorderColor = colorScheme.outline,
                        errorBorderColor = colorScheme.error
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = himpunanAddress,
                    onValueChange = { himpunanAddress = it },
                    label = { Text("Alamat", fontFamily = ProductSans) },
                    placeholder = { Text("Masukkan alamat himpunan", fontFamily = ProductSans) },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2,
                    maxLines = 2,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorScheme.primary,
                        unfocusedBorderColor = colorScheme.outline
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        // Validasi input wajib
                        val isNameValid = himpunanName.isNotBlank()
                        val isSingkatanValid = himpunanSingkatan.isNotBlank()
                        val isEmailValid = himpunanContactEmail.isBlank() ||
                                android.util.Patterns.EMAIL_ADDRESS.matcher(himpunanContactEmail).matches()
                        val isPhoneValid = himpunanContactPhone.isBlank() ||
                                himpunanContactPhone.matches(Regex("^[0-9+\\-\\s()]{6,15}$"))
                        val isDateValid = try {
                            himpunanFoundedDate.isBlank() ||
                                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(himpunanFoundedDate) != null
                        } catch (e: Exception) { false }

                        // Set error messages
                        nameError = if (!isNameValid) "Nama himpunan tidak boleh kosong" else null
                        singkatanError = if (!isSingkatanValid) "Singkatan tidak boleh kosong" else null
                        emailError = if (!isEmailValid) "Format email tidak valid" else null
                        phoneError = if (!isPhoneValid) "Format nomor telepon tidak valid" else null
                        dateError = if (!isDateValid) "Format tanggal harus YYYY-MM-DD" else null

                        if (isNameValid && isSingkatanValid && isEmailValid && isPhoneValid && isDateValid) {
                            viewModel.updateHimpunan(
                                id = himpunanId,
                                name = himpunanName,
                                aka = himpunanSingkatan,
                                description = himpunanDeskripsi.ifBlank { null },
                                foundedDate = himpunanFoundedDate.ifBlank { null },
                                contactEmail = himpunanContactEmail.ifBlank { null },
                                contactPhone = himpunanContactPhone.ifBlank { null },
                                address = himpunanAddress.ifBlank { null }
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary)
                ) {
                    Text(
                        text = "Simpan Perubahan",
                        fontFamily = ProductSans,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}