package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R

@Composable
@Preview
fun Digit(number: Int = 0) {
    Card(
        backgroundColor = colorResource(id = R.color.white),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        modifier = Modifier.padding(PaddingValues(horizontal = 4.dp, vertical = 4.dp))
    ) {
        Text(text = "$number", fontSize = 48.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp))
    }
}
