package com.eidorian.weedtacora.presentation

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.eidorian.weedtacora.bussinesslogic.viewmodel.GrowthDetailViewModel
import com.eidorian.weedtacora.data.entities.Binnacle
import com.eidorian.weedtacora.presentation.components.Footer
import com.eidorian.weedtacora.presentation.navigation.Screen
import com.eidorian.weedtacora.ui.theme.WeedtacoraTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun GrowthDetailsScreen(
    navController: NavController,
    viewModel: GrowthDetailViewModel
) {

    val details = viewModel.growthDetails.collectAsStateWithLifecycle(initialValue = emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchGrowthDetails(1)
        Log.d("LaunchedEffect", "viewModel.fetchGrowthDetails(1)")
    }

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

            BinnacleList(details.value)

        }
        Row {
            Footer("Informar") {
                navController.navigate(Screen.CreateBinnacleScreen.route)
            }
        }
    }

}

@Composable
fun BinnacleList(items: List<Binnacle>) {
    LazyColumn {
        items(
            items = items,
            key = { item: Binnacle -> item.binnacleId }
        ) {
            BinnacleCard(binnacle = it) {

            }
        }
    }
}

@Composable
fun BinnacleCard(binnacle: Binnacle, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        border = BorderStroke(width = 4.dp, getStageColor(binnacle.stage)),
        elevation = 6.dp
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                "Fecha: ${binnacle.date}",
                Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                "Etapa: ${binnacle.stage}",
                Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                "Observaciones: ${binnacle.observation}",
                Modifier
                    .fillMaxWidth()
            )
        }
    }
}

/*@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    WeedtacoraTheme {
        HomeScreen(rememberNavController(), viewModel())
    }
}*/
private fun getStageColor(stage: String) = when (stage) {
    "Seedling" -> Color.Green
    "Early Veg" -> Color.Cyan
    "Late Veg" -> Color.Blue
    "Flowering" -> Color.Magenta
    else -> Color.Transparent
}

@Preview(showBackground = true, name = "growth card")
@Composable
private fun GrowthCardPreview() {
    WeedtacoraTheme {
        Column {
            BinnacleCard(
                binnacle = Binnacle(
                    fkGrowthId = 1,
                    date = "15/03/1995",
                    observation = "Riego con 500ml compartido entre todas las plantas. Apago humidificador, hay mucha HR hoy.",
                    stage = "Seedling"
                ),
                onClick = { }
            )
            BinnacleCard(
                binnacle = Binnacle(
                    fkGrowthId = 1,
                    date = "15/03/1995",
                    observation = "Riego con 500ml compartido entre todas las plantas. Apago humidificador, hay mucha HR hoy.",
                    stage = "Early Veg"
                ),
                onClick = { }
            )
            BinnacleCard(
                binnacle = Binnacle(
                    fkGrowthId = 1,
                    date = "15/03/1995",
                    observation = "Riego con 500ml compartido entre todas las plantas. Apago humidificador, hay mucha HR hoy.",
                    stage = "Late Veg"
                ),
                onClick = { }
            )
            BinnacleCard(
                binnacle = Binnacle(
                    fkGrowthId = 1,
                    date = "15/03/1995",
                    observation = "Riego con 500ml compartido entre todas las plantas. Apago humidificador, hay mucha HR hoy.",
                    stage = "Flowering"
                ),
                onClick = { }
            )
        }
    }
}