package com.example.dipantau.ui.auth


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    composable(route = "lupa_password") {
        LupaPasswordScreen(
            onBackPressed = { navController.popBackStack() },
            onResetPasswordSuccess = {
                // Optional: handle after password reset success
                navController.popBackStack()
            }
        )
    }
}