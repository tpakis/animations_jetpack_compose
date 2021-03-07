package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R

@Composable
@Preview
fun Digit(number: Int = 0, onClick: () -> Unit = {} ) {
    val numRotation = remember { Animatable(0f) }
    val numText = remember { mutableStateOf(number.toString()) }

    LaunchedEffect(number) {
        numRotation.animateTo(0f, animationSpec = snap())
        numRotation.animateTo(-90f, animationSpec = tween(200, easing = LinearEasing))
        numText.value = number.toString()
        numRotation.animateTo(90f, animationSpec = snap())
        numRotation.animateTo(0f, animationSpec = tween(200, easing = LinearEasing))
    }
    Card(
        backgroundColor = colorResource(id = R.color.white),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        modifier = Modifier
            .padding(PaddingValues(horizontal = 4.dp, vertical = 4.dp))
            .graphicsLayer(
                transformOrigin = TransformOrigin(0.5f, 0.5f),
                rotationX = numRotation.value
            ).clickable {
                onClick()
            }
    ) {
        Text(
            text = numText.value,
            fontSize = 48.sp,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
        )
    }
}
