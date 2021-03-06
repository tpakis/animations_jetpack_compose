package com.example.androiddevchallenge.ui.models

sealed class TimerState {
    class Initial(val timeToStart: Time): TimerState()
    class Started(val timeRemaining: Time): TimerState()
    object Finished: TimerState()
}