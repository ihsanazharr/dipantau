package com.example.dipantau.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.dipantau.R

val ProductSans = FontFamily(
    Font(R.font.productsansregular, FontWeight.Normal),
    Font(R.font.productsansitalic, FontWeight.SemiBold),
    Font(R.font.productsansbold, FontWeight.Bold)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = ProductSans,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp
    ),
    titleLarge = TextStyle(
        fontFamily = ProductSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = ProductSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)
