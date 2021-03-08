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
    Scaffold(
        content = {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Header(viewModel)
                DigitsRow(viewModel)
                ControlRow(viewModel)
            }
        }
    )
}

@Composable
fun Header(viewModel: MainViewModel) {
    val state = viewModel.timerStateFlow.collectAsState().value

    Text(
        text = when (state) {
            is TimerState.Initial -> "Click digits to change the time"
            is TimerState.Finished -> "Finished!"
            else -> ""
        },
        color = Color.White,
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
    )
}

@Composable
fun DigitsRow(viewModel: MainViewModel) {
    val time = viewModel.timerStateFlow.collectAsState().value.time
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Digit(time.tensOfMinutes) { viewModel.tensOfMinutesClicked() }
        Digit(time.minutes) { viewModel.minutesClicked() }
        Text(
            text = ":",
            color = Color.White,
            fontSize = 48.sp,
            modifier = Modifier.padding(PaddingValues(top = 24.dp))
        )

        Digit(time.tensOfSeconds) { viewModel.tensOfSecondsClicked() }
        Digit(time.seconds) { viewModel.secondsClicked() }
    }
}

@Composable
fun ControlRow(viewModel: MainViewModel) {
    val state = viewModel.timerStateFlow.collectAsState().value

    Row(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        when (state) {
            is TimerState.Initial -> {
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
            is TimerState.Started -> {
                FloatingActionButton(
                    onClick = { viewModel.stopCountDownClicked() },
                    backgroundColor = Color.Red
                ) {
                    Icon(Icons.Filled.Stop, contentDescription = "Stop timer")
                }
            }
            is TimerState.Finished -> {
                FloatingActionButton(
                    onClick = { viewModel.resetTimerClicked() },
                    backgroundColor = Color.Yellow
                ) {
                    Icon(Icons.Filled.Restore, contentDescription = "Reset timer")
                }
            }
        }
    }
}