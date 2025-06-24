package com.example.dipantau.ui.screen.member

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dipantau.ui.screen.member.dashboardMember.MemberDashboardScreen

@Composable
fun MemberNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "member_dashboard") {
        // Dashboard
        composable("member_dashboard") {
            MemberDashboardScreen(navController)
        }

        // Task Management
//        composable("member_tasks") {
//            MemberTaskListScreen(navController)
//        }
//        composable(
//            "member_task_detail/{taskId}",
//            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
//        ) { backStackEntry ->
//            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0
//            MemberTaskDetailScreen(
//                taskId = taskId,
//                onNavigateBack = { navController.popBackStack() }
//            )
//        }
//
//        // Activity
//        composable("member_activities") {
//            MemberActivityListScreen(navController)
//        }
//        composable(
//            "member_activity_detail/{activityId}",
//            arguments = listOf(navArgument("activityId") { type = NavType.IntType })
//        ) { backStackEntry ->
//            val activityId = backStackEntry.arguments?.getInt("activityId") ?: 0
//            MemberActivityDetailScreen(
//                activityId = activityId,
//                onNavigateBack = { navController.popBackStack() }
//            )
//        }
//
//        // Himpunan
//        composable("member_himpunan") {
//            MemberHimpunanScreen(navController)
//        }
//        composable("member_join_himpunan") {
//            MemberJoinHimpunanScreen(
//                onNavigateBack = { navController.popBackStack() }
//            )
//        }
//
//        // Score & Leaderboard
//        composable("member_score") {
//            MemberScoreScreen(navController)
//        }
//
//        // Profile
//        composable("member_profile") {
//            MemberProfileScreen(navController)
//        }
//        composable("member_edit_profile") {
//            MemberEditProfileScreen(
//                onNavigateBack = { navController.popBackStack() }
//            )
//        }
    }
}