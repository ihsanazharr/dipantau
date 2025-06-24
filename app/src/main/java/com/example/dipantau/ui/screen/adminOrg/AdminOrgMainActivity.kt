package com.example.dipantau.ui.screen.adminOrg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dipantau.ui.theme.DiPantauTheme
import com.example.dipantau.ui.screen.superAdmin.SuperAdminMainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminOrgMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiPantauTheme {
                AdminOrgMainScreen()
            }
        }
    }
}
