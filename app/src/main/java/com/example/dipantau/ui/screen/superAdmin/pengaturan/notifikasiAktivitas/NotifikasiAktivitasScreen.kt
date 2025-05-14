package com.example.dipantau.ui.screen.superAdmin.pengaturan.notifikasiAktivitas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dipantau.ui.theme.ProductSans

@Composable
fun NotifikasiAktivitasScreen(
    onBackPressed: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Notifikasi", "Aktivitas")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
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
                text = "Notifikasi & Aktivitas",
                color = colorScheme.onTertiary,
                fontFamily = ProductSans,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tabs
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = colorScheme.onBackground,
            contentColor = colorScheme.tertiary,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = colorScheme.tertiary
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontFamily = ProductSans,
                            fontSize = 16.sp
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedTab) {
            0 -> NotificationsContent(colorScheme)
            1 -> ActivityContent(colorScheme)
        }
    }
}

@Composable
private fun NotificationsContent(colorScheme: ColorScheme) {
    var notificationSettings by remember { mutableStateOf(
        mapOf(
            "Pemberitahuan Email" to true,
            "Pemberitahuan Push" to true,
            "Pemberitahuan Login" to false,
            "Pemberitahuan Aktivitas Tim" to true
        )
    )}

    Column {
        // Settings Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorScheme.onBackground
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Pengaturan Notifikasi",
                    color = colorScheme.onTertiary,
                    fontFamily = ProductSans,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                notificationSettings.forEach { (setting, isEnabled) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = setting,
                            color = colorScheme.onTertiary,
                            fontFamily = ProductSans,
                            fontSize = 16.sp
                        )
                        Switch(
                            checked = isEnabled,
                            onCheckedChange = { newValue ->
                                notificationSettings = notificationSettings.toMutableMap().apply {
                                    put(setting, newValue)
                                }
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = colorScheme.tertiary,
                                checkedTrackColor = colorScheme.tertiary.copy(alpha = 0.5f)
                            )
                        )
                    }
                }
            }
        }

        // Recent Notifications
        Text(
            text = "Notifikasi Terbaru",
            color = colorScheme.onTertiary,
            fontFamily = ProductSans,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(sampleNotifications) { notification ->
                NotificationItem(notification, colorScheme)
            }
        }
    }
}

@Composable
private fun ActivityContent(colorScheme: ColorScheme) {
    LazyColumn {
        items(sampleActivities) { activity ->
            ActivityItem(activity, colorScheme)
        }
    }
}

data class Notification(
    val title: String,
    val description: String,
    val time: String,
    val type: NotificationType
)

enum class NotificationType {
    INFO, WARNING, SUCCESS
}

private val sampleNotifications = listOf(
    Notification(
        "Login Baru Terdeteksi",
        "Login baru terdeteksi dari perangkat Windows",
        "2 menit yang lalu",
        NotificationType.WARNING
    ),
    Notification(
        "Laporan Mingguan",
        "Laporan aktivitas mingguan telah tersedia",
        "1 jam yang lalu",
        NotificationType.INFO
    ),
    Notification(
        "Update Sistem",
        "Sistem berhasil diperbarui ke versi terbaru",
        "2 jam yang lalu",
        NotificationType.SUCCESS
    )
)

data class Activity(
    val user: String,
    val action: String,
    val time: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

private val sampleActivities = listOf(
    Activity(
        "John Doe",
        "menambahkan anggota baru ke tim",
        "10:30 AM",
        Icons.Default.AccountCircle
    ),
    Activity(
        "Jane Smith",
        "mengubah pengaturan organisasi",
        "09:15 AM",
        Icons.Default.Settings
    ),
    Activity(
        "Mark Johnson",
        "mengunggah dokumen baru",
        "Yesterday",
        Icons.Default.Add
    )
)

@Composable
private fun NotificationItem(
    notification: Notification,
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
            Icon(
                imageVector = when (notification.type) {
                    NotificationType.INFO -> Icons.Default.Info
                    NotificationType.WARNING -> Icons.Default.Warning
                    NotificationType.SUCCESS -> Icons.Default.CheckCircle
                },
                contentDescription = null,
                tint = when (notification.type) {
                    NotificationType.INFO -> colorScheme.tertiary
                    NotificationType.WARNING -> colorScheme.error
                    NotificationType.SUCCESS -> colorScheme.primary
                },
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = notification.title,
                    color = colorScheme.onTertiary,
                    fontFamily = ProductSans,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = notification.description,
                    color = colorScheme.onSurface,
                    fontFamily = ProductSans,
                    fontSize = 14.sp
                )
                Text(
                    text = notification.time,
                    color = colorScheme.onSurface.copy(alpha = 0.7f),
                    fontFamily = ProductSans,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun ActivityItem(
    activity: Activity,
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
            Icon(
                imageVector = activity.icon,
                contentDescription = null,
                tint = colorScheme.tertiary,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Row {
                    Text(
                        text = activity.user,
                        color = colorScheme.tertiary,
                        fontFamily = ProductSans,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = " ${activity.action}",
                        color = colorScheme.onTertiary,
                        fontFamily = ProductSans,
                        fontSize = 16.sp
                    )
                }
                Text(
                    text = activity.time,
                    color = colorScheme.onSurface.copy(alpha = 0.7f),
                    fontFamily = ProductSans,
                    fontSize = 12.sp
                )
            }
        }
    }
}