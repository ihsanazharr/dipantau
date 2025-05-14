package com.example.dipantau.ui.screen.superAdmin

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dipantau.ui.screen.superAdmin.dashboard.DashboardScreen
import com.example.dipantau.ui.screen.superAdmin.dashboard.detail.DetailDataPenggunaScreen
import com.example.dipantau.ui.screen.superAdmin.dashboard.detail.DetailLogAktivitasScreen
import com.example.dipantau.ui.screen.superAdmin.kelolaAdmin.KelolaAdminScreen
import com.example.dipantau.ui.screen.superAdmin.kelolaHimpunan.AddHimpunanScreen
import com.example.dipantau.ui.screen.superAdmin.kelolaHimpunan.EditHimpunanScreen
import com.example.dipantau.ui.screen.superAdmin.kelolaHimpunan.KelolaHimpunanScreen
import com.example.dipantau.ui.screen.superAdmin.pengaturan.*
import com.example.dipantau.ui.screen.superAdmin.pengaturan.akunKeamanan.AkunKeamananScreen
import com.example.dipantau.ui.screen.superAdmin.pengaturan.akunKeamanan.EditProfileScreen
import com.example.dipantau.ui.screen.superAdmin.pengaturan.notifikasiAktivitas.NotifikasiAktivitasScreen

@Composable
fun SuperAdminNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            DashboardScreen(navController)
        }
        composable("himpunan") {
            KelolaHimpunanScreen(navController)
        }
        composable(
            "himpunan/edit/{himpunanId}",
            arguments = listOf(navArgument("himpunanId") { type = NavType.StringType })
        ) { backStackEntry ->
            val himpunanId = backStackEntry.arguments?.getString("himpunanId")?.toIntOrNull() ?: 0
            EditHimpunanScreen(
                himpunanId = himpunanId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("himpunan/add") {
            AddHimpunanScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("admin") {
            KelolaAdminScreen()
        }
        composable("pengaturan") {
            PengaturanScreen(
                onNavigateToAkunKeamanan = {
                    navController.navigate("pengaturan/akun_keamanan")
                },
                onNavigateToPengaturanOrganisasi = {
                    navController.navigate("pengaturan/organisasi")
                },
                onNavigateToPengaturanAdmin = {
                    navController.navigate("pengaturan/admin_settings")
                },
                onNavigateToNotifikasi = {
                    navController.navigate("pengaturan/notifikasi")
                },
                onLogout = {

                }
            )
        }
        composable("pengaturan/akun_keamanan") {
            AkunKeamananScreen(
                onBackPressed = { navController.popBackStack() },
                onNavigateToEditProfile = { navController.navigate("pengaturan/edit_profile") },
                onNavigateToChangePassword = { /* Navigate to change password */ },
                onNavigateToTwoFactor = { /* Navigate to 2FA */ },
                onNavigateToLoginHistory = { /* Navigate to login history */ }
            )
        }

        composable("pengaturan/edit_profile") {
            EditProfileScreen(
                onBackPressed = { navController.popBackStack() },
                onSaveProfile = {
                    // Implement save profile logic here
                    navController.popBackStack()
                }
            )
        }
        composable("pengaturan/organisasi") {
            PengaturanOrganisasiScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable("pengaturan/admin_settings") {
            PengaturanAdminScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable("pengaturan/notifikasi") {
            NotifikasiAktivitasScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable("detail_data_pengguna") {
            DetailDataPenggunaScreen(navController)
        }
        composable("detail_log_aktivitas") {
            DetailLogAktivitasScreen(navController)
        }
    }
}