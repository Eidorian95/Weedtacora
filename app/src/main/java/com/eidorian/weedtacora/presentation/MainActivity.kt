package com.eidorian.weedtacora.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eidorian.weedtacora.ui.theme.WeedtacoraTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeedtacoraTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MyHomeScreen()
                }
            }
        }
    }
}

@Composable
fun MyHomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            GrowthsList()
        }
        Row {
            Footer()
        }
    }

}

@Composable
fun GrowthsList() {
    LazyColumn{
        items(15) { i ->
            Text(
                "Row $i",
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun Footer() {
    Button(
        modifier = Modifier.fillMaxWidth().height(64.dp),
        shape = RectangleShape,
        onClick = {},
    ) {
        Text(text = "Agregar cultivo")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeedtacoraTheme {
        MyHomeScreen()
    }
}