package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.ui.components.Digit
import com.example.androiddevchallenge.ui.models.Time
import com.example.androiddevchallenge.ui.models.TimerState

@Composable
fun TimerScreen(viewModel: MainViewModel) {
    val timerState = viewModel.timerStateFlow.collectAsState().value
    Scaffold(
        content = {
            when (timerState) {
               is TimerState.Initial,
               is TimerState.Started -> {

               }
                else -> Text(text = "finished")
            }

        }
    )
}

@Composable
@Preview
fun DigitsRow(time: Time = Time(682)) {
    Row {
        Digit(time.tensOfMinutes)
        Digit(time.minutes)
        Text(text = ":", fontSize = 48.sp, modifier = Modifier.fillMaxHeight(), style = TextStyle(textAlign = TextAlign.Center))
        Digit(time.tensOfSeconds)
        Digit(time.seconds)
    }
}