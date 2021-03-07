package com.example.androiddevchallenge.ui.models

sealed class TimerState(val time: TimeUiModel) {
    class Initial(val timeToStart: TimeUiModel): TimerState(timeToStart)
    class Started(val timeRemaining: TimeUiModel): TimerState(timeRemaining)
    object Finished: TimerState(TimeUiModel(0))
}