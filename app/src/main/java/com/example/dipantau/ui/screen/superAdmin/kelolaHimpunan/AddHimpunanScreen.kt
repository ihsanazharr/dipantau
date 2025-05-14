package com.example.dipantau.ui.screen.superAdmin.kelolaHimpunan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dipantau.model.Resource
import com.example.dipantau.model.superAdmin.HimpunanData
import com.example.dipantau.model.superAdmin.availableLogoColors
import com.example.dipantau.ui.theme.ProductSans
import com.example.dipantau.viewmodel.HimpunanViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHimpunanScreen(
    onNavigateBack: () -> Unit,
    viewModel: HimpunanViewModel = hiltViewModel()
) {
    val colorScheme = MaterialTheme.colorScheme
    val scrollState = rememberScrollState()

    val createState by viewModel.createHimpunanResult.collectAsState()

    LaunchedEffect(createState) {
        if (createState is Resource.Success) {
            viewModel.resetCreateResult()
            onNavigateBack()
        }
    }

    var newHimpunanName by remember { mutableStateOf("") }
    var newHimpunanSingkatan by remember { mutableStateOf("") }
    var newHimpunanDeskripsi by remember { mutableStateOf("") }

    var newHimpunanContactEmail by remember { mutableStateOf("") }
    var newHimpunanContactPhone by remember { mutableStateOf("") }
    var newHimpunanAddress by remember { mutableStateOf("") }
    var newHimpunanFoundedDate by remember { mutableStateOf("") }
    var newHimpunanStatus by remember { mutableStateOf("active") }

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
                        text = "Tambah Himpunan Baru",
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
                when (val state = createState) {
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
                    value = newHimpunanName,
                    onValueChange = {
                        newHimpunanName = it
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
                    value = newHimpunanSingkatan,
                    onValueChange = {
                        newHimpunanSingkatan = it
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
                    value = newHimpunanDeskripsi,
                    onValueChange = { newHimpunanDeskripsi = it },
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
                    value = newHimpunanFoundedDate,
                    onValueChange = {
                        newHimpunanFoundedDate = it
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
                    value = newHimpunanContactEmail,
                    onValueChange = {
                        newHimpunanContactEmail = it
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
                    value = newHimpunanContactPhone,
                    onValueChange = {
                        newHimpunanContactPhone = it
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
                    value = newHimpunanAddress,
                    onValueChange = { newHimpunanAddress = it },
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
                        val isNameValid = newHimpunanName.isNotBlank()
                        val isSingkatanValid = newHimpunanSingkatan.isNotBlank()
                        val isEmailValid = newHimpunanContactEmail.isBlank() ||
                                android.util.Patterns.EMAIL_ADDRESS.matcher(newHimpunanContactEmail).matches()
                        val isPhoneValid = newHimpunanContactPhone.isBlank() ||
                                newHimpunanContactPhone.matches(Regex("^[0-9+\\-\\s()]{6,15}$"))
                        val isDateValid = try {
                            newHimpunanFoundedDate.isBlank() ||
                                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(newHimpunanFoundedDate) != null
                        } catch (e: Exception) { false }

                        // Set error messages
                        nameError = if (!isNameValid) "Nama himpunan tidak boleh kosong" else null
                        singkatanError = if (!isSingkatanValid) "Singkatan tidak boleh kosong" else null
                        emailError = if (!isEmailValid) "Format email tidak valid" else null
                        phoneError = if (!isPhoneValid) "Format nomor telepon tidak valid" else null
                        dateError = if (!isDateValid) "Format tanggal harus YYYY-MM-DD" else null

                        if (isNameValid && isSingkatanValid && isEmailValid && isPhoneValid && isDateValid) {
                            viewModel.createHimpunan(
                                name = newHimpunanName,
                                aka = newHimpunanSingkatan,
                                description = newHimpunanDeskripsi,
                                foundedDate = if (newHimpunanFoundedDate.isBlank()) null else newHimpunanFoundedDate,
                                contactEmail = if (newHimpunanContactEmail.isBlank()) null else newHimpunanContactEmail,
                                contactPhone = if (newHimpunanContactPhone.isBlank()) null else newHimpunanContactPhone,
                                address = if (newHimpunanAddress.isBlank()) null else newHimpunanAddress
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary)
                ) {
                    Text(
                        text = "Simpan Himpunan",
                        fontFamily = ProductSans,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}