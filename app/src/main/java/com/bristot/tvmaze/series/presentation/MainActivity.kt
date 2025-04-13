package com.bristot.tvmaze.series.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.bristot.tvmaze.series.presentation.nav.Nav
import com.bristot.tvmaze.series.presentation.theme.TVmazeSeriesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TVmazeSeriesTheme { Nav(rememberNavController()) }
        }
    }
}
