package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material.icons.filled.Stop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.ui.components.Digit
import com.example.androiddevchallenge.ui.models.TimeUiModel
import com.example.androiddevchallenge.ui.models.TimerState

@Composable
fun TimerScreen(viewModel: MainViewModel) {
    val timerState = viewModel.timerStateFlow.collectAsState().value
    Scaffold(
        content = {
            when (timerState) {
                is TimerState.Initial -> {
                    Text(
                        text = "Click to digits to change the time",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    DigitsRow(timerState.timeToStart, viewModel)
                    ControlRowInitial(viewModel)
                }
                is TimerState.Started -> {
                    DigitsRow(timerState.timeRemaining, viewModel)
                    ControlRowStarted(viewModel)
                }
                else -> {
                    Text(
                        text = "Finished!",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    ControlRowFinished(viewModel)
                }
            }
        }
    )
}

@Composable
fun ControlRowInitial(viewModel: MainViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        FloatingActionButton(
            onClick = { viewModel.startCountDownClicked() },
            backgroundColor = Color.Green
        ) {
            Icon(Icons.Filled.PlayArrow, contentDescription = "Start timer")
        }
        Spacer(modifier = Modifier.width(16.dp))
        FloatingActionButton(
            onClick = { viewModel.resetTimerClicked() },
            backgroundColor = Color.Yellow
        ) {
            Icon(Icons.Filled.Restore, contentDescription = "Reset timer")
        }
    }
}

@Composable
fun ControlRowStarted(viewModel: MainViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        FloatingActionButton(
            onClick = { viewModel.stopCountDownClicked() },
            backgroundColor = Color.Red
        ) {
            Icon(Icons.Filled.Stop, contentDescription = "Stop timer")
        }
    }
}

@Composable
fun ControlRowFinished(viewModel: MainViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        FloatingActionButton(
            onClick = { viewModel.resetTimerClicked() },
            backgroundColor = Color.Yellow
        ) {
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
            modifier = Modifier.padding(PaddingValues(bottom = 12.dp))
        )

        Digit(time.tensOfSeconds) { viewModel.tensOfSecondsClicked() }
        Digit(time.seconds) { viewModel.secondsClicked() }
    }
}