package com.eidorian.weedtacora.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eidorian.weedtacora.presentation.navigation.Screen


@Composable
fun Footer(buttonText: String, onClick:()->Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        shape = RectangleShape,
        onClick = {
           onClick()
        },
    ) {
        Text(text = buttonText)
    }
}
