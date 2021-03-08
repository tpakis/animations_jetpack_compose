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
