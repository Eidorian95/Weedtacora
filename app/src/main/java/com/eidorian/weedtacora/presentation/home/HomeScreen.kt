package com.eidorian.weedtacora.presentation.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.eidorian.weedtacora.R
import com.eidorian.weedtacora.bussinesslogic.viewmodel.GrowthViewModel
import com.eidorian.weedtacora.presentation.navigation.Screen
import com.eidorian.weedtacora.presentation.uimodel.GrowthUiModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: GrowthViewModel
) {
    val lifecycleEvent = rememberLifecycleEvent()
    val growthList by viewModel.userGrowths.collectAsState(initial = emptyList())

    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            viewModel.fetchUserGrowths()
        }
    }

    HomeScreenContent(growthList, navController)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(growthList: List<GrowthUiModel>, navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val isCollapsed by remember { derivedStateOf { scrollBehavior.state.collapsedFraction > 0.5 } }

    val topAppBarElementColor = if (isCollapsed) {
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.onPrimary
    }
    val collapsed = 16
    val expanded = 32

    val topAppBarTextSize =
        (collapsed + (expanded - collapsed) * (1 - scrollBehavior.state.collapsedFraction)).sp

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = "Tus Cultivos",
                        fontSize = topAppBarTextSize,
                        fontWeight = FontWeight.Bold
                    )
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = topAppBarElementColor,
                    titleContentColor = topAppBarElementColor,
                    actionIconContentColor = topAppBarElementColor,
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.CreateGrowthScreen.route)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_flower_stage),
                    contentDescription = ""
                )
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { value ->
        LazyColumn(modifier = Modifier.padding(value)) {
            items(
                items = growthList,
                key = { item: GrowthUiModel -> item.id }
            ) { growth ->
                GrowthCard(growth = growth) {
                    navController.navigate(Screen.GrowthDetailsScreen.route.plus("/${growth.id}"))
                }
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GrowthCard(growth: GrowthUiModel, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = {}
            )
            .padding(vertical = 8.dp),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = growth.name.uppercase(),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 18.sp,
                letterSpacing = 1.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp)
            )
            CardDetail()
        }
    }
}

@Composable
fun CardDetail() {
    Row(
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
    ) {
        CardDetailItem(drawable = R.drawable.ic_calendar_init_date, info = "15/03/95")
        Spacer(modifier = Modifier.weight(1f))
        CardDetailItem(drawable = R.drawable.ic_calendar, info = "45 dias")
        Spacer(modifier = Modifier.weight(1f))
        CardDetailItem(drawable = R.drawable.ic_flower_stage, info = "VEG")
    }
}

@Composable
fun CardDetailItem(@DrawableRes drawable: Int, info: String) {
    Icon(
        modifier = Modifier
            .width(16.dp)
            .height(16.dp),
        painter = painterResource(id = drawable),
        contentDescription = ""
    )
    Text(
        modifier = Modifier.padding(start = 4.dp),
        text = info,
        fontSize = 12.sp,
        letterSpacing = 1.sp,
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
