package com.example.dipantau.ui.component.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalBarChart(
    data: List<Pair<String, Float>>,
    modifier: Modifier = Modifier
) {
    val maxValue = data.maxOfOrNull { it.second } ?: 1f
    val barHeight = 36.dp
    val spacing = 20.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            data.forEach { (label, value) ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = label,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelLarge
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(barHeight)
                            .background(
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        val barWidthRatio = value / maxValue
                        val barColor = MaterialTheme.colorScheme.secondary
                        val textColor = MaterialTheme.colorScheme.onSecondary

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(fraction = barWidthRatio)
                                .fillMaxHeight()
                                .background(barColor, RoundedCornerShape(8.dp))
                                .padding(start = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = value.toInt().toString(),
                                color = textColor,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(spacing))
            }
        }
    }
}
