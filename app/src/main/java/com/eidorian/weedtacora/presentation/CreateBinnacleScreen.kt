package com.eidorian.weedtacora.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eidorian.weedtacora.bussinesslogic.viewmodel.BinnacleFormViewModel
import com.eidorian.weedtacora.bussinesslogic.viewmodel.FormViewModel
import com.eidorian.weedtacora.presentation.components.DatePicker
import com.eidorian.weedtacora.presentation.components.Footer
import com.eidorian.weedtacora.ui.theme.WeedtacoraTheme

@Composable
fun CreateBinnacleScreen(
    viewModel: BinnacleFormViewModel = viewModel(),
    growthId: Int
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .weight(1f)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                DatePicker { viewModel.date = it }

                Spacer(modifier = Modifier.padding(vertical = 16.dp))

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(112.dp),
                    value = viewModel.observation,
                    onValueChange = { viewModel.observation = it },
                    placeholder = { Text(text = "Observaciones") }
                )
                Spacer(modifier = Modifier.padding(vertical = 16.dp))

                StageSelector { viewModel.stage = it }
            }
        }
        Row {
            Footer("Agregar") {
                viewModel.onCreateNewBinnacle(1)
                Log.d("LaunchedEffect", "viewModel.onCreateNewBinnacle(1)")
            }
        }
    }
}


@Composable
fun StageSelector(onSelectedStage: (String) -> Unit) {
    val stages = listOf("Seedling", "Early Veg", "Late Veg", "Flowering")
    var expanded by remember { mutableStateOf(false) }
    var selectedStage by remember { mutableStateOf("") }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Text(
        text = "Etapa de cultivo",
        modifier = Modifier
    )
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = selectedStage,
            modifier = Modifier.weight(1.0f),
            fontSize = 20.sp
        )
        IconButton(onClick = { expanded = true }) {
            Icon(icon, contentDescription = "Stage")
            DropdownMenu(modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                stages.forEach { stage ->
                    DropdownMenuItem(modifier = Modifier.fillMaxWidth(), onClick = {
                        selectedStage = stage
                        expanded = false
                        onSelectedStage(selectedStage)
                    }) {
                        Text(stage)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    WeedtacoraTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            CreateBinnacleScreen(growthId = 0)
        }
    }
}