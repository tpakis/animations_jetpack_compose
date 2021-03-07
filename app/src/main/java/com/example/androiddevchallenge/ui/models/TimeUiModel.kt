package com.example.androiddevchallenge.ui.models

class TimeUiModel(val totalSeconds: Int) {
    init {
        require(totalSeconds in (0..3599))
    }
    val tensOfMinutes: Int = totalSeconds / 600
    val minutes: Int = (totalSeconds - (tensOfMinutes * 600)) / 60
    val tensOfSeconds: Int = (totalSeconds - tensOfMinutes * 600 - minutes * 60) / 10
    val seconds: Int = totalSeconds - tensOfMinutes * 600 - minutes * 60 - tensOfSeconds * 10
}