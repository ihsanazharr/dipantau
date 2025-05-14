package com.example.dipantau.ui.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dipantau.ui.theme.ProductSans

@Composable
fun ConfirmationDialog(
    showDialog: Boolean,
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    confirmText: String = "Ya",
    dismissText: String = "Tidak"
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            val colorScheme = MaterialTheme.colorScheme

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = colorScheme.onPrimary)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = colorScheme.onTertiary,
                        fontFamily = ProductSans,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Message
                    Text(
                        text = message,
                        fontSize = 14.sp,
                        color = colorScheme.onTertiary.copy(alpha = 0.8f),
                        fontFamily = ProductSans,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // No Button
                        OutlinedButton(
                            onClick = onDismiss,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = colorScheme.error
                            )
                        ) {
                            Text(
                                text = dismissText,
                                fontFamily = ProductSans
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // Yes Button
                        Button(
                            onClick = onConfirm,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorScheme.primary
                            )
                        ) {
                            Text(
                                text = confirmText,
                                fontFamily = ProductSans
                            )
                        }
                    }
                }
            }
        }
    }
}