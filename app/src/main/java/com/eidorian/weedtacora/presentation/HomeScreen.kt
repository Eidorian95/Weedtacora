package com.eidorian.weedtacora.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eidorian.weedtacora.presentation.components.Footer
import com.eidorian.weedtacora.presentation.navigation.Screen
import com.eidorian.weedtacora.ui.theme.WeedtacoraTheme

@Composable
fun HomeScreen(navController: NavController) {
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
            Footer("Nuevo cultivo"){
                navController.navigate(Screen.CreateGrowthScreen.route)
            }
        }
    }

}

@Composable
fun GrowthsList() {
    LazyColumn {
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

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    WeedtacoraTheme {
        HomeScreen(rememberNavController())
    }
}
