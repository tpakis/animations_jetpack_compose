package com.example.androiddevchallenge

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.ui.models.TimeUiModel
import com.example.androiddevchallenge.ui.models.TimerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.Exception

class MainViewModel: ViewModel() {

    private val _timerStateFlow =
        MutableStateFlow<TimerState>(TimerState.Initial(TimeUiModel(totalSeconds = 60)))
    val timerStateFlow: StateFlow<TimerState> = _timerStateFlow.asStateFlow()

    fun secondsClicked() {
        if (canClickOnDigit) {
            increaseSecondsBy(1)
        }
    }

    fun tensOfSecondsClicked() {
        if (canClickOnDigit) {
            increaseSecondsBy(10)
        }
    }

    fun minutesClicked() {
        if (canClickOnDigit) {
            increaseSecondsBy(60)
        }
    }

    fun tensOfMinutesClicked() {
        if (canClickOnDigit) {
            increaseSecondsBy(600)
        }
    }

    fun increaseSecondsBy(extraSeconds: Int) {
        try {
            val currentValue = _timerStateFlow.value.time.totalSeconds
            val newState = TimerState.Initial(TimeUiModel(currentValue + extraSeconds))
            _timerStateFlow.value = newState
        } catch (e: Exception) {
            Log.e("MainViewModel", "can't increase by $extraSeconds limit is 3600")
        }
    }

    private val canClickOnDigit: Boolean
        get() = _timerStateFlow.value is TimerState.Initial
}