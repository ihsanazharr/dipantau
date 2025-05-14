package com.example.dipantau.ui.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dipantau.model.superAdmin.HimpunanData
import com.example.dipantau.model.superAdmin.availableLogoColors
import com.example.dipantau.ui.theme.ProductSans
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHimpunanDialog(
    onDismiss: () -> Unit,
    onAddHimpunan: (HimpunanData) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val scrollState = rememberScrollState()

    // Fields wajib
    var newHimpunanName by remember { mutableStateOf("") }
    var newHimpunanSingkatan by remember { mutableStateOf("") } // aka
    var newHimpunanDeskripsi by remember { mutableStateOf("") } // description

    // Fields opsional
    var newHimpunanContactEmail by remember { mutableStateOf("") }
    var newHimpunanContactPhone by remember { mutableStateOf("") }
    var newHimpunanAddress by remember { mutableStateOf("") }
    var newHimpunanFoundedDate by remember { mutableStateOf("") }
    var newHimpunanStatus by remember { mutableStateOf("active") }

    // Untuk tampilan UI
    var newHimpunanJumlahAnggota by remember { mutableStateOf("0") }

    // Validasi
    var nameError by remember { mutableStateOf<String?>(null) }
    var singkatanError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var dateError by remember { mutableStateOf<String?>(null) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(scrollState)
            ) {
                Text(
                    text = "Tambah Himpunan Baru",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = colorScheme.onTertiary,
                    fontFamily = ProductSans
                )

                Spacer(modifier = Modifier.height(20.dp))

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

                Spacer(modifier = Modifier.height(12.dp))

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

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text("Batal", fontFamily = ProductSans, color = colorScheme.error)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

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

                            // Jika semua validasi berhasil
                            if (isNameValid && isSingkatanValid && isEmailValid && isPhoneValid && isDateValid) {
                                // Generate ID baru (gunakan timestamp untuk memastikan keunikan)
                                val newId = System.currentTimeMillis().toString()

                                // Tambahkan himpunan baru
                                val newHimpunan = HimpunanData(
                                    id = newId,
                                    nama = newHimpunanName,
                                    aka = newHimpunanSingkatan,
                                    deskripsi = newHimpunanDeskripsi,
                                    jumlahAnggota = 0,
                                    logoColor = availableLogoColors.random()
                                )

                                onAddHimpunan(newHimpunan)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary)
                    ) {
                        Text("Simpan", fontFamily = ProductSans)
                    }
                }
            }
        }
    }
}