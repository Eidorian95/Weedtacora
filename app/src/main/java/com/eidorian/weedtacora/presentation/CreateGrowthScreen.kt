package com.eidorian.weedtacora.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eidorian.weedtacora.presentation.components.Footer
import com.eidorian.weedtacora.presentation.navigation.Screen
import com.eidorian.weedtacora.ui.theme.WeedtacoraTheme

@Composable
fun CreateGrowthScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .weight(1f)
        ) {
            ColumnForm()
        }
        Row {
            Footer(navController, Screen.HomeScreen, "Agregar")
        }
    }
}

@Composable
private fun RowForm(rowTitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        TextField(
            value = TextFieldValue(""),
            onValueChange = {},
            label = { Text(text = rowTitle) },
            placeholder = { Text(text = "Ingresa algo") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun ColumnForm() {
    Column {
        RowForm(rowTitle = "Nombre")
        Spacer(modifier = Modifier.height(16.dp))
        RowForm(rowTitle = "Fecha")
        Spacer(modifier = Modifier.height(16.dp))
        RowForm(rowTitle = "Descripción")
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    WeedtacoraTheme {
        CreateGrowthScreen(rememberNavController())
    }
}