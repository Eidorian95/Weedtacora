package com.eidorian.weedtacora.presentation

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.eidorian.weedtacora.bussinesslogic.viewmodel.GrowthViewModel
import com.eidorian.weedtacora.data.entities.Growth
import com.eidorian.weedtacora.presentation.components.Footer
import com.eidorian.weedtacora.presentation.navigation.Screen
import com.eidorian.weedtacora.presentation.uimodel.GrowthUiModel

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
            GrowthsList(growthList.collectAsState(initial = emptyList()).value) {
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
fun GrowthsList(growthItems: List<GrowthUiModel>, onClick: (Int) -> Unit) {
    LazyColumn {
        items(
            items = growthItems,
            key = { item: GrowthUiModel -> item.id }
        ) {
            GrowthCard(growth = it) {
                onClick(it.id)
            }
        }
    }
}

@Composable
fun GrowthCard(growth: GrowthUiModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() },
        border = BorderStroke(width = 2.dp, Color.Magenta),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = growth.name.uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                letterSpacing = 1.sp,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            )
            Row(modifier = Modifier.padding(top = 8.dp, start = 8.dp)) {
                Text(
                    growth.initialDate,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.padding(horizontal = 8.dp))

                Text(
                    growth.notes,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "growth card")
@Composable
private fun GrowthCardPreview() {
    GrowthCard(
        growth = GrowthUiModel(
            0,
            "15/03/1995",
            "sin notas",
            "The Flower",
            "45",
            "VEG"
        ),
        onClick = { }
    )
}

@Preview(showBackground = true, name = "growth card long text")
@Composable
private fun GrowthCardLongTextPreview() {
    GrowthCard(
        growth = GrowthUiModel(
            0,
            "15/03/1995",
            "sin notas",
            "Silver River Cri-Tropical Haze",
            "45",
            "VEG"
        ),
        onClick = { }
    )
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
