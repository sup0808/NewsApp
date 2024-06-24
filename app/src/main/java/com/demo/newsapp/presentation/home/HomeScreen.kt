package com.demo.newsapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(contentAlignment = Alignment.Center) {
        Text(text ="Home Page", style = TextStyle(fontWeight = FontWeight.Bold))
    }
}