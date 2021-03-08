/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.ui.models.TimeUiModel
import com.example.androiddevchallenge.ui.models.TimerState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

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
            val newState = TimerState.Started(TimeUiModel(currentValue - 1))
            _timerStateFlow.value = newState
        } catch (e: Exception) {
            countJob.cancel()
            _timerStateFlow.value = TimerState.Finished
        }
    }

    private val canClickOnDigit: Boolean
        get() = _timerStateFlow.value is TimerState.Initial
}
