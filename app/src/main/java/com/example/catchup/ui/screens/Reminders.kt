package com.example.catchup.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersScreen(modifier: Modifier = Modifier) {
    // title banner thing
    CenterAlignedTopAppBar(
        title = {
            Text("My Reminders ₍^..^₎⟆ ")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        //windowInsets =
        //contentPadding =
        scrollBehavior = pinnedScrollBehavior()
    )

    Spacer(modifier = Modifier.height(200.dp))
    Spacer(modifier = Modifier.height(200.dp))

    LazyColumn{
        items(5){ index->

            Column(
                Modifier.fillMaxSize(),
                //verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // reminder cards
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    modifier = Modifier
                        .size(width = 350.dp, height = 100.dp)
                        .wrapContentHeight()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        //verticalAlignment = Alignment.CenterVertically,
                    ) {
                        var checked by remember { mutableStateOf(true) }

                        Column{
                            Switch(
                                checked = checked,
                                onCheckedChange = {
                                    checked = it
                                },
                            )
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        Column {
                            Text("Swag number: $index")
                            //HorizontalDivider(thickness = 2.dp)
                            Text("Reminder at 22:00")
                            Text("Will Take 20 Minutes")
                        }
                    }
                }
            }




        }
    }
}