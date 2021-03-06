package com.example.androiddevchallenge

import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.ui.models.TimerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {

    private val _timerFlow =
        MutableStateFlow<TimerState>(TimerState.Finished)
    val timerStateFlow: StateFlow<TimerState> = _timerFlow.asStateFlow()

}