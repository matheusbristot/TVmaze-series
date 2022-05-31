package com.bristot.tvmaze.series.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.bristot.tvmaze.series.presentation.nav.Nav
import com.bristot.tvmaze.series.presentation.theme.Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme { Nav(rememberNavController()) }
        }
    }
}
