package com.eidorian.weedtacora.presentation

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eidorian.weedtacora.bussinesslogic.viewmodel.GrowthViewModel
import com.eidorian.weedtacora.data.entities.Growth
import com.eidorian.weedtacora.presentation.components.Footer
import com.eidorian.weedtacora.presentation.navigation.Screen
import com.eidorian.weedtacora.ui.theme.WeedtacoraTheme

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: GrowthViewModel
) {
    val lifecycleEvent = rememberLifecycleEvent()
    val growthList = viewModel.userGrowths

    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            viewModel.fetchUserGrowths()
            Log.d("LaunchedEffect", "viewModel.fetchUserGrowths()")
        }
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
            GrowthsList(growthList.collectAsState(initial = emptyList()).value){
                navController.navigate(Screen.GrowthDetailsScreen.route.plus("/$it"))
            }
        }
        Row {
            Footer("Nuevo cultivo") {
                navController.navigate(Screen.CreateGrowthScreen.route)
            }
        }
    }

}

@Composable
fun GrowthsList(growthItems: List<Growth>, onClick: (Int) -> Unit) {
    LazyColumn {
        items(
            items = growthItems,
            key = { item: Growth -> item.growthId }
        ) {
            GrowthCard(growth = it) {
                onClick(it.growthId)
            }
        }
    }
}


@Composable
fun rememberLifecycleEvent(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current): Lifecycle.Event {
    var state by remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            state = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    return state
}

@Composable
fun GrowthCard(growth: Growth, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        border = BorderStroke(width = 2.dp, Color.Magenta),
        elevation = 6.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                "Cultivo: ${growth.name}",
                Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                "Fecha: ${growth.initialDate}",
                Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                "Decripción: ${growth.notes}",
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
        GrowthItem(item = Growth(
            0,"15/03/1995", "notas iniciales","The Flower"
        ))
    }
}*/

@Preview(showBackground = true, name = "growth card")
@Composable
private fun GrowthCardPreview() {
    GrowthCard(
        growth = Growth(
            0,
            "15/03/1995",
            "sin notas",
            "cultivo prueba"
        ),
        onClick = { }
    )
}