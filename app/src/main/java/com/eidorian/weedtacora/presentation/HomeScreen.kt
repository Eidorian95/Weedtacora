package com.eidorian.weedtacora.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import com.eidorian.weedtacora.presentation.navigation.Screen
import com.eidorian.weedtacora.presentation.uimodel.GrowthUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: GrowthViewModel
) {
    val lifecycleEvent = rememberLifecycleEvent()
    val growthList by viewModel.userGrowths.collectAsState(initial = emptyList())
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val isCollapsed by remember { derivedStateOf { scrollBehavior.state.collapsedFraction > 0.5 } }
    val topAppBarElementColor = if (isCollapsed) {
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.onPrimary
    }
    val collapsed = 16
    val expanded = 32

    val topAppBarTextSize = (collapsed + (expanded - collapsed)*(1-scrollBehavior.state.collapsedFraction)).sp
    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            viewModel.fetchUserGrowths()
        }
    }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Tus Cultivos", fontSize = topAppBarTextSize ) },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = topAppBarElementColor,
                    titleContentColor = topAppBarElementColor,
                    actionIconContentColor= topAppBarElementColor,
                )
            )
        },
        floatingActionButton = {

        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { value ->
        LazyColumn(modifier = Modifier.padding(value)) {
            items(
                items = growthList,
                key = { item: GrowthUiModel -> item.id }
            ) {
                GrowthCard(growth = it) {
                    navController.navigate(Screen.GrowthDetailsScreen.route.plus("/$it"))
                }
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
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
