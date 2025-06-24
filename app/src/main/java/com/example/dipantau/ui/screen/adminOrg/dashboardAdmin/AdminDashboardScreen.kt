package com.example.dipantau.ui.screen.adminOrg.dashboardAdmin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dipantau.viewmodel.MembershipViewModel
import com.example.dipantau.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
    navController: NavController,
    membershipViewModel: MembershipViewModel = hiltViewModel(),
    taskViewModel: TaskViewModel = hiltViewModel(),
//    activityViewModel: ActivityViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        membershipViewModel.getMyMembership()
        taskViewModel.getCurrentUserTasks()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard Admin") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Dashboard Admin Himpunan",
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            item {
                DashboardStatsCard()
            }

            item {
                QuickActionsCard(navController)
            }

            item {
                RecentTasksCard(navController)
            }

            item {
                RecentActivitiesCard(navController)
            }
        }
    }
}

@Composable
fun DashboardStatsCard() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Statistik",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem("Total Anggota", "25")
                StatItem("Task Aktif", "10")
                StatItem("Kegiatan Bulan Ini", "3")
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun QuickActionsCard(navController: NavController) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Aksi Cepat",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { navController.navigate("admin_create_task") },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Buat Task")
                }
                Button(
                    onClick = { navController.navigate("admin_members") },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Kelola Anggota")
                }
            }
        }
    }
}

@Composable
fun RecentTasksCard(navController: NavController) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Task Terbaru",
                    style = MaterialTheme.typography.titleMedium
                )
                TextButton(
                    onClick = { navController.navigate("admin_tasks") }
                ) {
                    Text("Lihat Semua")
                }
            }
            // Add task list here
        }
    }
}

@Composable
fun RecentActivitiesCard(navController: NavController) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Kegiatan Terbaru",
                    style = MaterialTheme.typography.titleMedium
                )
                TextButton(
                    onClick = { navController.navigate("admin_activities") }
                ) {
                    Text("Lihat Semua")
                }
            }
            // Add activity list here
        }
    }
}