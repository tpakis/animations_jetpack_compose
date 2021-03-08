package com.example.androiddevchallenge

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.ui.models.TimeUiModel
import com.example.androiddevchallenge.ui.models.TimerState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.Exception

class MainViewModel: ViewModel() {

    private val _timerStateFlow =
        MutableStateFlow<TimerState>(TimerState.Initial(TimeUiModel(totalSeconds = 10)))
    val timerStateFlow: StateFlow<TimerState> = _timerStateFlow.asStateFlow()

    var countJob: Job = Job()

    fun secondsClicked() {
        if (canClickOnDigit) {
            increaseInitialSecondsBy(1)
        }
    }

    fun tensOfSecondsClicked() {
        if (canClickOnDigit) {
            increaseInitialSecondsBy(10)
        }
    }

    fun minutesClicked() {
        if (canClickOnDigit) {
            increaseInitialSecondsBy(60)
        }
    }

    fun tensOfMinutesClicked() {
        if (canClickOnDigit) {
            increaseInitialSecondsBy(600)
        }
    }

    fun startCountDownClicked() {
        countJob = viewModelScope.launch {
            while (isActive) {
                delay(1000)
                tick()
            }
        }
    }

    fun stopCountDownClicked() {
        countJob.cancel()
        val currentValue = _timerStateFlow.value.time.totalSeconds
        val newState = TimerState.Initial(TimeUiModel(currentValue))
        _timerStateFlow.value = newState
    }

    fun resetTimerClicked() {
        _timerStateFlow.value = TimerState.Initial(TimeUiModel(totalSeconds = 10))
    }

    private fun increaseInitialSecondsBy(extraSeconds: Int) {
        try {
            val currentValue = _timerStateFlow.value.time.totalSeconds
            val newState = TimerState.Initial(TimeUiModel(currentValue + extraSeconds))
            _timerStateFlow.value = newState
        } catch (e: Exception) {
            Log.e("MainViewModel", "can't increase by $extraSeconds limit is 3600")
        }
    }

    private fun tick() {
        try {
            val currentValue = _timerStateFlow.value.time.totalSeconds
            val newState = TimerState.Started(TimeUiModel(currentValue -1))
            _timerStateFlow.value = newState
        } catch (e: Exception) {
            countJob.cancel()
            _timerStateFlow.value = TimerState.Finished
        }
    }

    private val canClickOnDigit: Boolean
        get() = _timerStateFlow.value is TimerState.Initial
}