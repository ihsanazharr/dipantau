package com.example.dipantau.ui.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: String = "login"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authNavGraph(navController)
        // Tambahkan graph lain jika diperlukan
    }
}