package com.example.dipantau.ui.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dipantau.model.superAdmin.AdminData
import com.example.dipantau.model.superAdmin.availableAvatarColors
import com.example.dipantau.model.superAdmin.himpunanList
import com.example.dipantau.model.superAdmin.jabatanList
import com.example.dipantau.model.superAdmin.statusList
import com.example.dipantau.ui.theme.ProductSans

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAdminDialog(
    onDismiss: () -> Unit,
    onAddAdmin: (AdminData) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var himpunan by remember { mutableStateOf("") }
    var jabatan by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Aktif") }

    var expandedHimpunan by remember { mutableStateOf(false) }
    var expandedJabatan by remember { mutableStateOf(false) }
    var expandedStatus by remember { mutableStateOf(false) }

    var namaError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var himpunanError by remember { mutableStateOf<String?>(null) }
    var jabatanError by remember { mutableStateOf<String?>(null) }

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
            ) {
                Text(
                    text = "Tambah Admin Baru",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = colorScheme.onTertiary,
                    fontFamily = ProductSans
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = nama,
                    onValueChange = {
                        nama = it
                        namaError = if (it.isBlank()) "Nama tidak boleh kosong" else null
                    },
                    label = { Text("Nama Admin", fontFamily = ProductSans) },
                    placeholder = { Text("Masukkan nama admin", fontFamily = ProductSans) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = namaError != null,
                    supportingText = { namaError?.let { Text(it, fontFamily = ProductSans) } },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorScheme.primary,
                        unfocusedBorderColor = colorScheme.outline,
                        errorBorderColor = colorScheme.error
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = when {
                            it.isBlank() -> "Email tidak boleh kosong"
                            !it.contains("@") -> "Email tidak valid"
                            else -> null
                        }
                    },
                    label = { Text("Email", fontFamily = ProductSans) },
                    placeholder = { Text("Masukkan email admin", fontFamily = ProductSans) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = emailError != null,
                    supportingText = { emailError?.let { Text(it, fontFamily = ProductSans) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorScheme.primary,
                        unfocusedBorderColor = colorScheme.outline,
                        errorBorderColor = colorScheme.error
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                ExposedDropdownMenuBox(
                    expanded = expandedHimpunan,
                    onExpandedChange = { expandedHimpunan = it }
                ) {
                    OutlinedTextField(
                        value = himpunan,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Himpunan", fontFamily = ProductSans) },
                        placeholder = { Text("Pilih himpunan", fontFamily = ProductSans) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedHimpunan) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        isError = himpunanError != null,
                        supportingText = { himpunanError?.let { Text(it, fontFamily = ProductSans) } },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorScheme.primary,
                            unfocusedBorderColor = colorScheme.outline,
                            errorBorderColor = colorScheme.error
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expandedHimpunan,
                        onDismissRequest = { expandedHimpunan = false }
                    ) {
                        himpunanList.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item, fontFamily = ProductSans) },
                                onClick = {
                                    himpunan = item
                                    himpunanError = null
                                    expandedHimpunan = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Dropdown Jabatan
                ExposedDropdownMenuBox(
                    expanded = expandedJabatan,
                    onExpandedChange = { expandedJabatan = it }
                ) {
                    OutlinedTextField(
                        value = jabatan,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Jabatan", fontFamily = ProductSans) },
                        placeholder = { Text("Pilih jabatan", fontFamily = ProductSans) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedJabatan) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        isError = jabatanError != null,
                        supportingText = { jabatanError?.let { Text(it, fontFamily = ProductSans) } },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorScheme.primary,
                            unfocusedBorderColor = colorScheme.outline,
                            errorBorderColor = colorScheme.error
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expandedJabatan,
                        onDismissRequest = { expandedJabatan = false }
                    ) {
                        jabatanList.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item, fontFamily = ProductSans) },
                                onClick = {
                                    jabatan = item
                                    jabatanError = null
                                    expandedJabatan = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                ExposedDropdownMenuBox(
                    expanded = expandedStatus,
                    onExpandedChange = { expandedStatus = it }
                ) {
                    OutlinedTextField(
                        value = status,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Status", fontFamily = ProductSans) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedStatus) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorScheme.primary,
                            unfocusedBorderColor = colorScheme.outline
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expandedStatus,
                        onDismissRequest = { expandedStatus = false }
                    ) {
                        statusList.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item, fontFamily = ProductSans) },
                                onClick = {
                                    status = item
                                    expandedStatus = false
                                }
                            )
                        }
                    }
                }

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
                            namaError = if (nama.isBlank()) "Nama tidak boleh kosong" else null
                            emailError = when {
                                email.isBlank() -> "Email tidak boleh kosong"
                                !email.contains("@") -> "Email tidak valid"
                                else -> null
                            }
                            himpunanError = if (himpunan.isBlank()) "Himpunan harus dipilih" else null
                            jabatanError = if (jabatan.isBlank()) "Jabatan harus dipilih" else null

                            val isValid = namaError == null && emailError == null &&
                                    himpunanError == null && jabatanError == null

                            if (isValid) {
                                val newId = System.currentTimeMillis().toString()

                                val newAdmin = AdminData(
                                    id = newId,
                                    nama = nama,
                                    email = email,
                                    himpunan = himpunan,
                                    jabatan = jabatan,
                                    status = status,
                                    avatarColor = availableAvatarColors.random()
                                )

                                onAddAdmin(newAdmin)
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