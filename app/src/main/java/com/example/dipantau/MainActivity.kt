package com.example.dipantau

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dipantau.ui.screen.superAdmin.SuperAdminMainScreen
import com.example.dipantau.ui.screen.superAdmin.dashboard.DashboardScreen
import com.example.dipantau.ui.screen.superAdmin.kelolaAdmin.KelolaAdminScreen
import com.example.dipantau.ui.screen.superAdmin.kelolaHimpunan.KelolaHimpunanScreen
import com.example.dipantau.ui.screen.superAdmin.pengaturan.PengaturanScreen
import com.example.dipantau.ui.theme.DiPantauTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiPantauTheme {
                SuperAdminMainScreen()
            }
        }
    }
}
