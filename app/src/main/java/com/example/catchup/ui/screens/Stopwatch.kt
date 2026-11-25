package com.example.catchup.ui.screens

import android.os.SystemClock
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.catchup.viewModel.ReminderViewModel
import kotlinx.coroutines.delay
import java.util.Locale
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopwatchScreen(
    viewModel: ReminderViewModel,
    modifier: Modifier
) {
    // SET UP STOPWATCH VARIABLES HERE
    var elapsedMillis by remember { mutableLongStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            var lastTick = SystemClock.elapsedRealtime()
            while (true) {
                delay(16L.milliseconds)
                val now = SystemClock.elapsedRealtime()
                elapsedMillis += now - lastTick
                lastTick = now
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "StopCATch! ₍^..^₎⟆",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = formatStopwatchTime(elapsedMillis),
                    fontSize = 70.sp,
                )

                Row (
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    Button(onClick = { isRunning = !isRunning }) {
                        Text(
                            when {
                                isRunning -> "Pause"
                                elapsedMillis > 0L -> "Resume"
                                else -> "Start"
                            }
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            isRunning = false
                            elapsedMillis = 0L
                        },
                        enabled = elapsedMillis > 0L || isRunning
                    ) {
                        Text("Reset")
                    }
                }

            }
        }
    )
}

private fun formatStopwatchTime(totalMillis: Long): String{
    val totalCentis = totalMillis / 10L
    val centis = totalCentis % 100L
    val totalSeconds = totalCentis / 100L
    val seconds = totalSeconds % 60L
    val minutes = (totalSeconds / 60L) % 60L
    val hours = totalSeconds / 3600L

    return if (hours > 0L) {
        String.format(Locale.getDefault(), "%02d:%02d:%02d.%02d", hours, minutes, seconds, centis)
    } else {
        String.format(Locale.getDefault(), "%02d:%02d.%02d", minutes, seconds, centis)
    }
}