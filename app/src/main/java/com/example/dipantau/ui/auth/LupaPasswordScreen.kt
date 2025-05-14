package com.example.dipantau.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dipantau.R
import com.example.dipantau.ui.theme.ProductSans

@Composable
fun LupaPasswordScreen(
    onBackPressed: () -> Unit,
    onResetPasswordSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var isEmailSent by remember { mutableStateOf(false) }
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Kembali",
                    tint = colorScheme.onTertiary
                )
            }
            Text(
                text = "Lupa Password",
                color = colorScheme.onTertiary,
                fontFamily = ProductSans,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.logodipantau),
            contentDescription = "Logo",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (!isEmailSent) {
            Text(
                text = "Masukkan alamat email yang terdaftar. Kami akan mengirimkan link untuk mengatur ulang password Anda.",
                color = colorScheme.onTertiary,
                fontFamily = ProductSans,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Email Input Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorScheme.onBackground
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Email",
                        color = colorScheme.onTertiary,
                        fontFamily = ProductSans,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = colorScheme.secondary,
                            unfocusedBorderColor = colorScheme.onTertiary.copy(alpha = 0.5f)
                        ),
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email",
                                tint = colorScheme.onTertiary
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Masukkan email Anda",
                                fontFamily = ProductSans,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { isEmailSent = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.secondary
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Kirim Link Reset",
                    fontFamily = ProductSans,
                    fontSize = 16.sp
                )
            }
        } else {
            // Success State
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email Sent",
                tint = colorScheme.secondary,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Link Reset Password Terkirim!",
                color = colorScheme.onTertiary,
                fontFamily = ProductSans,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Silakan cek email Anda untuk instruksi selanjutnya. Link akan kadaluarsa dalam 1 jam.",
                color = colorScheme.onTertiary,
                fontFamily = ProductSans,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedButton(
                onClick = onBackPressed,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = colorScheme.secondary
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = SolidColor(colorScheme.secondary)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Kembali ke Login",
                    fontFamily = ProductSans,
                    fontSize = 16.sp
                )
            }
        }
    }
}