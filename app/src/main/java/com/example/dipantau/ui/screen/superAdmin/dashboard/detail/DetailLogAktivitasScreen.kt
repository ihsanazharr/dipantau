package com.example.dipantau.ui.screen.superAdmin.dashboard.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dipantau.ui.component.card.CardLogAktivitas
import com.example.dipantau.ui.theme.ProductSans
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailLogAktivitasScreen(navController: NavHostController) {
    val colorScheme = MaterialTheme.colorScheme
    var searchQuery by remember { mutableStateOf("") }
    var showFilterDialog by remember { mutableStateOf(false) }
    var selectedHimpunan by remember { mutableStateOf<String?>(null) }
    var selectedStartDate by remember { mutableStateOf<Date?>(null) }
    var selectedEndDate by remember { mutableStateOf<Date?>(null) }

    // Data log aktivitas contoh
    val logAktivitas = remember {
        listOf(
            LogAktivitasData(
                "Update Data Pengurus",
                "Ahmad Rizky",
                "HIMAKOM",
                "12 April 2025, 10:30 WIB",
                Calendar.getInstance().apply { add(Calendar.HOUR, -2) }.time
            ),
            LogAktivitasData(
                "Tambah Anggota Baru",
                "Budi Santoso",
                "HMAN",
                "12 April 2025, 09:15 WIB",
                Calendar.getInstance().apply { add(Calendar.HOUR, -3) }.time
            ),
            LogAktivitasData(
                "Hapus Data Anggota",
                "Cindy Paramita",
                "HIMAKAPS",
                "11 April 2025, 16:45 WIB",
                Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -1) }.time
            ),
            LogAktivitasData(
                "Edit Profil Himpunan",
                "Deni Kurniawan",
                "IMT-AERO",
                "11 April 2025, 14:20 WIB",
                Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -1) }.time
            ),
            LogAktivitasData(
                "Login Sistem",
                "Eka Putri",
                "HIMAKOM",
                "10 April 2025, 08:30 WIB",
                Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -2) }.time
            ),
            LogAktivitasData(
                "Export Data Anggota",
                "Faisal Rahman",
                "HMAN",
                "10 April 2025, 11:50 WIB",
                Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -2) }.time
            ),
            LogAktivitasData(
                "Perbarui Foto Profil",
                "Gita Nirmala",
                "HIMAKAPS",
                "9 April 2025, 13:25 WIB",
                Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -3) }.time
            ),
            LogAktivitasData(
                "Tambah Event Baru",
                "Hadi Wijaya",
                "IMT-AERO",
                "9 April 2025, 15:40 WIB",
                Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -3) }.time
            )
        )
    }

    val himpunanList = remember {
        listOf("HIMAKOM", "HMAN", "HIMAKAPS", "IMT-AERO")
    }

    val filteredLog = logAktivitas.filter { log ->
        val matchesSearch = searchQuery.isEmpty() ||
                log.judulAktivitas.contains(searchQuery, ignoreCase = true) ||
                log.user.contains(searchQuery, ignoreCase = true)

        val matchesHimpunan = selectedHimpunan == null || log.himpunan == selectedHimpunan

        val matchesDateRange = (selectedStartDate == null || log.timestamp.after(selectedStartDate) ||
                log.timestamp.equals(selectedStartDate)) &&
                (selectedEndDate == null || log.timestamp.before(selectedEndDate) ||
                        log.timestamp.equals(selectedEndDate))

        matchesSearch && matchesHimpunan && matchesDateRange
    }

    // Format tanggal untuk menampilkan header grup
    val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

    // Grup log berdasarkan tanggal
    val groupedLog = filteredLog.groupBy { log ->
        dateFormat.format(log.timestamp)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
    ) {
        // Top App Bar
        SmallTopAppBar(
            title = {
                Text(
                    text = "Detail Log Aktivitas",
                    fontFamily = ProductSans,
                    color = colorScheme.onTertiary
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Kembali",
                        tint = colorScheme.onTertiary
                    )
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = colorScheme.background
            )
        )

        // Search Bar dan Filter
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Cari aktivitas...", fontFamily = ProductSans) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = colorScheme.secondary
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorScheme.primary,
                    unfocusedBorderColor = colorScheme.outline,
                    cursorColor = colorScheme.primary,
                    containerColor = colorScheme.onPrimary
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Filter Button
            Button(
                onClick = { showFilterDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = colorScheme.onPrimary),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Filter",
                    tint = colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Filter",
                    fontFamily = ProductSans,
                    color = colorScheme.secondary
                )
            }
        }

        // Log Aktivitas List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            groupedLog.forEach { (date, logs) ->
                // Tanggal sebagai header
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Tanggal",
                            tint = colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = date,
                            fontWeight = FontWeight.Bold,
                            fontFamily = ProductSans,
                            color = colorScheme.onTertiary
                        )
                    }
                    Divider(color = colorScheme.primary.copy(alpha = 0.5f))
                }

                // Log aktivitas dalam grup tanggal
                items(logs) { log ->
                    CardLogAktivitas(
                        judulAktivitas = log.judulAktivitas,
                        user = log.user,
                        himpunan = log.himpunan,
                        waktu = log.waktu,
                        onDetailClick = { /* Implementasi detail */ }
                    )
                }
            }
        }
    }

    if (showFilterDialog) {
        AlertDialog(
            onDismissRequest = { showFilterDialog = false },
            title = {
                Text(
                    text = "Filter Log Aktivitas",
                    fontFamily = ProductSans,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onTertiary
                )
            },
            text = {
                Column {
                    Text(
                        text = "Himpunan",
                        fontWeight = FontWeight.Medium,
                        fontFamily = ProductSans,
                        color = colorScheme.onTertiary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        himpunanList.forEach { himpunan ->
                            FilterChip(
                                selected = selectedHimpunan == himpunan,
                                onClick = {
                                    selectedHimpunan = if (selectedHimpunan == himpunan) null else himpunan
                                },
                                label = {
                                    Text(
                                        text = himpunan,
                                        fontFamily = ProductSans
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = colorScheme.primary,
                                    selectedLabelColor = colorScheme.onPrimary
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Tanggal",
                        fontWeight = FontWeight.Medium,
                        fontFamily = ProductSans,
                        color = colorScheme.onTertiary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                // Implementasi date picker dari tanggal
                                selectedStartDate = Calendar.getInstance().apply {
                                    add(Calendar.DAY_OF_MONTH, -7)
                                }.time
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = colorScheme.onPrimary),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = if (selectedStartDate != null)
                                    dateFormat.format(selectedStartDate!!)
                                else
                                    "Dari Tanggal",
                                color = colorScheme.secondary,
                                fontFamily = ProductSans
                            )
                        }

                        Text(
                            text = "-",
                            fontFamily = ProductSans,
                            color = colorScheme.onTertiary,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        Button(
                            onClick = {
                                // Implementasi date picker sampai tanggal
                                selectedEndDate = Calendar.getInstance().time
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = colorScheme.onPrimary),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = if (selectedEndDate != null)
                                    dateFormat.format(selectedEndDate!!)
                                else
                                    "Sampai Tanggal",
                                color = colorScheme.secondary,
                                fontFamily = ProductSans
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showFilterDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary)
                ) {
                    Text(
                        text = "Terapkan",
                        fontFamily = ProductSans
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        selectedHimpunan = null
                        selectedStartDate = null
                        selectedEndDate = null
                        showFilterDialog = false
                    }
                ) {
                    Text(
                        text = "Reset",
                        fontFamily = ProductSans,
                        color = colorScheme.error
                    )
                }
            }
        )
    }
}

data class LogAktivitasData(
    val judulAktivitas: String,
    val user: String,
    val himpunan: String,
    val waktu: String,
    val timestamp: Date // untuk keperluan filtering
)