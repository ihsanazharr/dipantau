package com.example.dipantau.ui.screen.superAdmin.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dipantau.ui.component.card.CardLogAktivitas
import com.example.dipantau.ui.component.chart.HorizontalBarChart
import com.example.dipantau.ui.theme.ProductSans

@Composable
fun DashboardScreen(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = buildAnnotatedString {
                append("Halo, ")
                withStyle(
                    style = androidx.compose.ui.text.SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )
                ) {
                    append("\nSuper Admin!")
                }
            },
            fontSize = 22.sp,
            fontFamily = ProductSans,
            color = colorScheme.onTertiary,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(22.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Data Pengguna",
                fontFamily = ProductSans,
                color = colorScheme.onTertiary
            )

            Button(
                onClick = {
                    navController.navigate("detail_data_pengguna")
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorScheme.onPrimary),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Lihat >",
                    fontFamily = ProductSans,
                    color = colorScheme.secondary
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))
        HorizontalBarChart(
            data = listOf(
                "HIMAKOM" to 150f,
                "HMAN" to 246f,
                "HIMAKAPS" to 142f,
                "IMT-AERO" to 97f
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Spacer(modifier = Modifier.height(26.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Log Aktivitas",
                fontFamily = ProductSans,
                color = colorScheme.onTertiary
            )

            Button(
                onClick = {
                    navController.navigate("detail_log_aktivitas")
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorScheme.onPrimary),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Lihat >",
                    fontFamily = ProductSans,
                    color = colorScheme.secondary
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
//                .padding(16.dp)
        ) {
            CardLogAktivitas(
                judulAktivitas = "Update Data Pengurus",
                user = "Aznel",
                himpunan = "HIMAKOM",
                waktu = "12 April 2025, 10:30 WIB",
                onDetailClick = {

                }
            )
            CardLogAktivitas(
                judulAktivitas = "Update Data Pengurus",
                user = "Aznel",
                himpunan = "HIMAKOM",
                waktu = "12 April 2025, 10:30 WIB",
                onDetailClick = {

                }
            )
            CardLogAktivitas(
                judulAktivitas = "Update Data Pengurus",
                user = "Aznel",
                himpunan = "HIMAKOM",
                waktu = "12 April 2025, 10:30 WIB",
                onDetailClick = {

                }
            )
        }

    }
}