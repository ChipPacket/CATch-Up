package com.example.catchup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.catchup.ui.screens.BottomNavBar
import com.example.catchup.ui.theme.CATchUpTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // https://developer.android.com/training/data-storage/room
        // https://developer.android.com/training/data-storage/room/referencing-data


        setContent {
            CATchUpTheme {
                BottomNavBar() // run UI
            }
        }
    }
}