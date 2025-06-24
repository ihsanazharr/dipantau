package com.example.dipantau.ui.screen.member.dashboardMember


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
fun MemberDashboardScreen(
    navController: NavController,
    membershipViewModel: MembershipViewModel = hiltViewModel(),
    taskViewModel: TaskViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        membershipViewModel.getMyMembership()
        taskViewModel.getCurrentUserTasks()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") }
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
                    text = "Selamat Datang",
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            item {
                MembershipStatusCard(navController)
            }

            item {
                ScoreCard(navController)
            }

            item {
                MyTasksCard(navController)
            }

            item {
                UpcomingActivitiesCard(navController)
            }
        }
    }
}

@Composable
fun MembershipStatusCard(navController: NavController) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Status Keanggotaan",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Add membership status info here
            Button(
                onClick = { navController.navigate("member_himpunan") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Lihat Detail Himpunan")
            }
        }
    }
}

@Composable
fun ScoreCard(navController: NavController) {
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
                    text = "Skor Saya",
                    style = MaterialTheme.typography.titleMedium
                )
                TextButton(
                    onClick = { navController.navigate("member_score") }
                ) {
                    Text("Lihat Detail")
                }
            }
            Text(
                text = "150", // Get from ViewModel
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "Total Skor",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun MyTasksCard(navController: NavController) {
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
                    text = "Task Saya",
                    style = MaterialTheme.typography.titleMedium
                )
                TextButton(
                    onClick = { navController.navigate("member_tasks") }
                ) {
                    Text("Lihat Semua")
                }
            }
            // Add task list here
        }
    }
}

@Composable
fun UpcomingActivitiesCard(navController: NavController) {
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
                    text = "Kegiatan Mendatang",
                    style = MaterialTheme.typography.titleMedium
                )
                TextButton(
                    onClick = { navController.navigate("member_activities") }
                ) {
                    Text("Lihat Semua")
                }
            }
            // Add activity list here
        }
    }
}
