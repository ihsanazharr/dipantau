package com.example.dipantau.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dipantau.R
import com.example.dipantau.ui.theme.DiPantauTheme
import com.example.dipantau.ui.theme.ProductSans
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dipantau.MainActivity
import com.example.dipantau.model.Resource
import com.example.dipantau.ui.auth.LupaPasswordScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dipantau.model.AuthResponse
import com.example.dipantau.ui.screen.superAdmin.SuperAdminMainActivity
import com.example.dipantau.ui.screen.superAdmin.SuperAdminMainScreen
import com.example.dipantau.utils.Constants
import com.example.dipantau.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiPantauTheme {
                val navController = rememberNavController()
                val context = LocalContext.current

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    composable("login") {
                        LoginScreen(
                            onNavigateToForgotPassword = {
                                navController.navigate("lupa_password")
                            },
                            onLoginSuccess = { role ->
                                when (role) {
                                    Constants.ROLE_SUPER_ADMIN -> {
                                        val intent = Intent(context, SuperAdminMainActivity::class.java)
                                        context.startActivity(intent)
                                        (context as? Activity)?.finish()
                                    }
//                                    Constants.ROLE_ADMIN -> {
//                                        // Buat AdminMainActivity jika belum ada
//                                        val intent = Intent(context, AdminMainActivity::class.java)
//                                        context.startActivity(intent)
//                                        (context as? Activity)?.finish()
//                                    }
//                                    Constants.ROLE_MEMBER -> {
//                                        // Buat MemberMainActivity jika belum ada
//                                        val intent = Intent(context, MemberMainActivity::class.java)
//                                        context.startActivity(intent)
//                                        (context as? Activity)?.finish()
//                                    }
                                    else -> {
                                        Toast.makeText(context, "Role tidak dikenali", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        )
                    }
                    composable("lupa_password") {
                        LupaPasswordScreen(
                            onBackPressed = { navController.popBackStack() },
                            onResetPasswordSuccess = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    onNavigateToForgotPassword: () -> Unit,
    onLoginSuccess: (String) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    val loginState by authViewModel.loginResult.observeAsState()

    LaunchedEffect(loginState) {
        when (loginState) {
            is Resource.Success -> {
                // Ambil role dari response login
                val role = (loginState as? Resource.Success<AuthResponse>)?.data?.role ?: ""
                onLoginSuccess(role)
            }
            else -> {} // Tidak perlu apa-apa untuk state lainnya
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.primary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.performance),
                contentDescription = "Performance",
                modifier = Modifier.size(200.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorScheme.onPrimary,
                    shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                )
                .padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = buildAnnotatedString {
                    append("Masuk Ke ")
                    withStyle(
                        style = androidx.compose.ui.text.SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                        )
                    ) {
                        append("DiPantau")
                    }
                },
                fontSize = 22.sp,
                fontFamily = ProductSans,
                color = colorScheme.onTertiary,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Email",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = ProductSans,
                color = colorScheme.onTertiary
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Masukkan Email Anda",
                        fontFamily = ProductSans,
                        color = colorScheme.onSurface
                    )
                },
                textStyle = LocalTextStyle.current.copy(
                    color = colorScheme.onTertiary,
                    fontFamily = ProductSans
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Password",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = ProductSans,
                color = colorScheme.onTertiary
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Masukkan Password Anda",
                        fontFamily = ProductSans,
                        color = colorScheme.onSurface
                    )
                },
                textStyle = LocalTextStyle.current.copy(
                    color = colorScheme.onTertiary,
                    fontFamily = ProductSans
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Lupa Password?",
                fontSize = 13.sp,
                fontFamily = ProductSans,
                color = colorScheme.primary,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable {
                        onNavigateToForgotPassword()
                    }
            )

            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = {
                    authViewModel.login(email, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorScheme.secondary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Masuk",
                    fontSize = 16.sp,
                    fontFamily = ProductSans,
                    color = colorScheme.onPrimary
                )
            }

            // Tampilkan loading dan pesan error
            when (val state = loginState) {
                is Resource.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(top = 16.dp)
//                    .align(Alignment.CenterHorizontally)
                    )
                }

                is Resource.Error -> {
                    Text(
                        text = state.message ?: "Terjadi kesalahan",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                else -> Unit
            }
        }
    }
}
