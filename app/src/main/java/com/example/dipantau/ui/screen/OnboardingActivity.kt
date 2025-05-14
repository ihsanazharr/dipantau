package com.example.dipantau.ui.screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.edit
import com.example.dipantau.MainActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.example.dipantau.R
import com.example.dipantau.ui.LoginActivity
import com.example.dipantau.ui.theme.DiPantauTheme
import com.example.dipantau.ui.theme.ProductSans

class OnboardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("onboarding_pref", Context.MODE_PRIVATE)
        val isCompleted = sharedPref.getBoolean("isOnboardingCompleted", false)

        if (isCompleted) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            setContent {
                DiPantauTheme {
                    OnboardingScreen(
                        onFinish = {
                            sharedPref.edit {
                                putBoolean("isOnboardingCompleted", true)
                            }
                            startActivity(Intent(this@OnboardingActivity, LoginActivity::class.java))
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val pages = listOf(
        Triple(
            R.raw.lottie1,
            "Selamat Datang ke DiPantau!",
            "Tetap terhubung dengan himpunan dan pantau setiap kehadiran, tugas, serta pencapaianmu dalam satu aplikasi. Dengan sistem yang terstruktur, kamu bisa mengelola aktivitas organisasi dengan lebih efisien."
        ),
        Triple(R.raw.lottie2, "Evaluasi & tingkatkan kinerja.", "Dapatkan feedback langsung dari pengurus, ketahui progres KPI-mu, dan raih pencapaian terbaik dalam organisasi. Dengan sistem evaluasi yang objektif, setiap usaha dan kontribusi akan dihargai."),
        Triple(R.raw.lottie3, "Bersinergi mencapai tujuan!", "Kolaborasi jadi lebih efektif dengan fitur tugas yang terorganisir, sistem apresiasi yang transparan, serta pemantauan progres yang real-time.")
    )

    var pageIndex by remember { mutableStateOf(0) }

    val (lottieRes, title, description) = pages[pageIndex]
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieRes))
    val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)

    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier
                    .height(240.dp)
                    .fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorScheme.primary,
                    shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                )
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = ProductSans,
                color = colorScheme.onPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                fontSize = 15.sp,
                color = colorScheme.onPrimary,
                fontFamily = ProductSans,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                pages.indices.forEach { i ->
                    val color = if (i == pageIndex) colorScheme.onPrimary else colorScheme.onSurface
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .padding(horizontal = 4.dp)
                            .background(color = color, shape = RoundedCornerShape(100))
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        if (pageIndex == 0) {
                            onFinish()
                        } else {
                            pageIndex--
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = if (pageIndex == 0) "Lewati" else "Kembali",
                        fontFamily = ProductSans,
                        color = colorScheme.onPrimary
                    )
                }

                Button(
                    onClick = {
                        if (pageIndex < pages.lastIndex) {
                            pageIndex++
                        } else {
                            onFinish()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.secondary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = if (pageIndex == pages.lastIndex) "Mulai" else "Lanjut >",
                        fontFamily = ProductSans,
                        color = colorScheme.onPrimary
                    )
                }
            }
        }
    }
}


