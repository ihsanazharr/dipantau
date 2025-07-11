package com.example.dipantau

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.dipantau.ui.navigation.MainNavGraph
import com.example.dipantau.ui.theme.DiPantauTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiPantauTheme {
                val navController = rememberNavController()
                MainNavGraph(navController = navController)
            }
        }
    }
}
