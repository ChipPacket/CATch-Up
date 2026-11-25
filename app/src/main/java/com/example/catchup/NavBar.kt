package com.example.catchup

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catchup.ui.screens.AppIcons
import com.example.catchup.ui.screens.StopwatchScreen
import com.example.catchup.ui.screens.RemindersScreen
import com.example.catchup.ui.screens.SettingsScreen
import com.example.catchup.ui.screens.StopwatchScreen
import com.example.catchup.viewModel.ReminderViewModel

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    STOPWATCH("stopwatch", "Stopwatch", AppIcons.FastForward,"Stopwatch"),
    REMINDERS("reminders", "Reminders", AppIcons.CheckBox,"Reminders"),
    SETTINGS("settings", "Settings", AppIcons.Settings,"Settings"),
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    viewModel: ReminderViewModel,
    modifier: Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.STOPWATCH -> StopwatchScreen(viewModel, modifier)
                    Destination.REMINDERS -> RemindersScreen(viewModel, modifier)
                    Destination.SETTINGS -> SettingsScreen(viewModel, modifier)
                }
            }
        }
    }
}

//@Preview()
@Composable
fun BottomNavBar(viewModel: ReminderViewModel){
    val navController = rememberNavController()
    val startDestination = Destination.REMINDERS
    var selectedDestination by rememberSaveable {mutableIntStateOf(startDestination.ordinal)}

    Scaffold(
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = {
                            Text(destination.label)
                        }
                    )
                }
            }
        }
    ) { contentPadding ->
        AppNavHost(
            navController, startDestination,
            viewModel,
            modifier = Modifier.padding(contentPadding))
    }
}
