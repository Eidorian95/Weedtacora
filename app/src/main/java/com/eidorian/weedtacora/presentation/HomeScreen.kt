package com.eidorian.weedtacora.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    viewModel: GrowthViewModel = viewModel()
) {
    val lifecycleEvent = rememberLifecycleEvent()
    val growthList = viewModel.userGrowths

    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            viewModel.fetchUserGrowths()
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
            GrowthsList(growthList.collectAsState(initial = emptyList()).value)
        }
        Row {
            Footer("Nuevo cultivo") {
                navController.navigate(Screen.CreateGrowthScreen.route)
            }
        }
    }

}

@Composable
fun GrowthsList(growthItems: List<Growth>) {
    LazyColumn {
        items(
            items = growthItems,
            key = { item: Growth -> item.growthId }
        ) {
            Column {
                Text(
                    "Cultivo: ${it.name} - ${it.initialDate}",
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
                Text(
                    "Decripción: ${it.notes}",
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Divider()
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

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    WeedtacoraTheme {
        HomeScreen(rememberNavController(), viewModel())
    }
}
