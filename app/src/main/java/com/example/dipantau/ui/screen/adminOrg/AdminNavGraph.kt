package com.example.dipantau.ui.screen.adminOrg


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dipantau.ui.screen.adminOrg.dashboardAdmin.AdminDashboardScreen

@Composable
fun AdminNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "admin_dashboard") {
        // Dashboard
        composable("admin_dashboard") {
            AdminDashboardScreen(navController)
        }

        // Himpunan Management
//        composable("admin_himpunan") {
//            AdminHimpunanDetailScreen(navController)
//        }
//
//        // Member Management
//        composable("admin_members") {
//            AdminMemberListScreen(navController)
//        }
//        composable(
//            "admin_member_detail/{memberId}",
//            arguments = listOf(navArgument("memberId") { type = NavType.IntType })
//        ) { backStackEntry ->
//            val memberId = backStackEntry.arguments?.getInt("memberId") ?: 0
//            AdminMemberDetailScreen(
//                memberId = memberId,
//                onNavigateBack = { navController.popBackStack() }
//            )
//        }
//
//        // Task Management
//        composable("admin_tasks") {
//            AdminTaskListScreen(navController)
//        }
//        composable(
//            "admin_task_detail/{taskId}",
//            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
//        ) { backStackEntry ->
//            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0
//            AdminTaskDetailScreen(
//                taskId = taskId,
//                navController = navController
//            )
//        }
//        composable("admin_create_task") {
//            AdminCreateTaskScreen(
//                onNavigateBack = { navController.popBackStack() }
//            )
//        }
//
//        // Activity Management
//        composable("admin_activities") {
//            AdminActivityListScreen(navController)
//        }
//        composable(
//            "admin_activity_detail/{activityId}",
//            arguments = listOf(navArgument("activityId") { type = NavType.IntType })
//        ) { backStackEntry ->
//            val activityId = backStackEntry.arguments?.getInt("activityId") ?: 0
//            AdminActivityDetailScreen(
//                activityId = activityId,
//                onNavigateBack = { navController.popBackStack() }
//            )
//        }

        // Profile Management
//        composable("admin_profile") {
//            AdminProfileScreen(navController)
//        }
//        composable("admin_edit_profile") {
//            AdminEditProfileScreen(
//                onNavigateBack = { navController.popBackStack() }
//            )
//        }
    }
}