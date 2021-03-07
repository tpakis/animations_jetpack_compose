package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Restore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.ui.components.Digit
import com.example.androiddevchallenge.ui.models.TimeUiModel
import com.example.androiddevchallenge.ui.models.TimerState
import androidx.compose.material.FloatingActionButton

@Composable
fun TimerScreen(viewModel: MainViewModel) {
    val timerState = viewModel.timerStateFlow.collectAsState().value
    Scaffold(
        content = {
            when (timerState) {
                is TimerState.Initial -> DigitsRow(timerState.timeToStart, viewModel)
                is TimerState.Started -> DigitsRow(timerState.timeRemaining, viewModel)
                else -> Text(text = "finished")
            }
            ControlRow()
        }
    )
}

@Composable
fun ControlRow() {
    Row {
        FloatingActionButton(onClick = { /*do something*/ }) {
            Icon(Icons.Filled.PlayArrow, contentDescription = "Start timer")
        }
        FloatingActionButton(onClick = { /*do something*/ }) {
            Icon(Icons.Filled.Restore, contentDescription = "Reset timer")
        }
    }
}

@Composable
fun DigitsRow(time: TimeUiModel, viewModel: MainViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Digit(time.tensOfMinutes) { viewModel.tensOfMinutesClicked() }
        Digit(time.minutes) { viewModel.minutesClicked() }
        Text(
            text = ":",
            color = Color.White,
            fontSize = 48.sp,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        Digit(time.tensOfSeconds) { viewModel.tensOfSecondsClicked() }
        Digit(time.seconds) { viewModel.secondsClicked() }
    }
}