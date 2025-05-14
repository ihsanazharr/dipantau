package com.example.dipantau.ui.component.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dipantau.ui.theme.ProductSans

@Composable
fun CardLogAktivitas(
    judulAktivitas: String,
    user: String,
    himpunan: String,
    waktu: String,
    onDetailClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp,MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = judulAktivitas,
                fontFamily = ProductSans,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onTertiary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = waktu,
                fontFamily = ProductSans,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Oleh:\n $user dari $himpunan",
                fontFamily = ProductSans,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onTertiary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(
                    onClick = onDetailClick,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Detail>",
                        fontFamily = ProductSans,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        }
    }
}
