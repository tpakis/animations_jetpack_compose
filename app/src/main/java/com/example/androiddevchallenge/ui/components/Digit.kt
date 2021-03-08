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
package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R

@Composable
@Preview
fun Digit(number: Int = 0, onClick: () -> Unit = {}) {
    val rotation = remember { Animatable(0f) }
    val text = remember { mutableStateOf(number.toString()) }

    Card(
        backgroundColor = colorResource(id = R.color.white),
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .graphicsLayer(
                rotationX = rotation.value
            ).clickable {
                onClick()
            }
    ) {
        Text(
            text = text.value,
            fontSize = 56.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
        )
    }

    LaunchedEffect(number) {
        rotation.animateTo(0f, animationSpec = snap())
        rotation.animateTo(-90f, animationSpec = tween(200, easing = LinearOutSlowInEasing))
        text.value = number.toString()
        rotation.animateTo(90f, animationSpec = snap())
        rotation.animateTo(0f, animationSpec = tween(200, easing = LinearOutSlowInEasing))
    }
}
