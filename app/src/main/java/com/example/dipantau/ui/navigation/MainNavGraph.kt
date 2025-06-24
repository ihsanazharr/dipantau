package com.example.dipantau.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dipantau.ui.auth.ForgotPasswordScreen
import com.example.dipantau.ui.auth.LoginScreen
import com.example.dipantau.ui.screen.adminOrg.AdminNavGraph
import com.example.dipantau.ui.screen.member.MemberNavGraph
import com.example.dipantau.ui.screen.superAdmin.SuperAdminNavGraph
import com.example.dipantau.viewmodel.AuthViewModel

@Composable
fun MainNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val navigationEvent by authViewModel.navigationEvent.observeAsState()
    val userProfile by authViewModel.userProfile.observeAsState()

    LaunchedEffect(navigationEvent) {
        navigationEvent?.let { route ->
            when (route) {
                "super_admin" -> navController.navigate("super_admin") {
                    popUpTo("login") { inclusive = true }
                }
                "admin" -> navController.navigate("admin") {
                    popUpTo("login") { inclusive = true }
                }
                "member" -> navController.navigate("member") {
                    popUpTo("login") { inclusive = true }
                }
                "logout" -> navController.navigate("login") {
                    popUpTo(0) { inclusive = true }
                }
            }
            authViewModel.clearNavigationEvent()
        }
    }

    // Check initial role on app start
    LaunchedEffect(Unit) {
        if (authViewModel.isLoggedIn()) {
            val role = authViewModel.checkUserRole()
            role?.let { authViewModel.navigateBasedOnRole(it) }
        }
    }

    NavHost(
        navController = navController,
        startDestination = if (authViewModel.isLoggedIn()) {
            when (authViewModel.checkUserRole()) {
                "super_admin" -> "super_admin"
                "admin" -> "admin"
                "member" -> "member"
                else -> "login"
            }
        } else "login"
    ) {
        composable("login") {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToForgotPassword = {
                    navController.navigate("forgot_password")
                },
                onLoginSuccess = { role ->
                    // Navigation akan ditangani oleh LaunchedEffect di atas
                    // berdasarkan navigationEvent dari AuthViewModel
                }
            )
        }

        composable("forgot_password") {
            ForgotPasswordScreen(
                onBackPressed = { navController.popBackStack() },
                onResetPasswordSuccess = { navController.popBackStack() }
            )
        }

        composable("super_admin") {
            SuperAdminNavGraph(navController)
        }

        composable("admin") {
            AdminNavGraph(navController)
        }

        composable("member") {
            MemberNavGraph(navController)
        }
    }
}