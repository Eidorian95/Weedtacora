package com.eidorian.weedtacora.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eidorian.weedtacora.bussinesslogic.viewmodel.FormViewModel
import com.eidorian.weedtacora.presentation.components.DatePicker
import com.eidorian.weedtacora.presentation.components.Footer
import com.eidorian.weedtacora.ui.theme.WeedtacoraTheme

@Composable
fun CreateGrowthScreen(
    viewModel: FormViewModel = viewModel()
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
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.name,
                    onValueChange = { viewModel.name = it },
                    placeholder = { Text(text = "Nombre") }
                )
                Spacer(modifier = Modifier.padding(vertical = 16.dp))

                DatePicker{ viewModel.date = it }

                Spacer(modifier = Modifier.padding(vertical = 16.dp))

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(112.dp),
                    value = viewModel.description,
                    onValueChange = { viewModel.description = it },
                    placeholder = { Text(text = "Descripción") }
                )
            }
        }
        Row {
            Footer("Agregar") {
                viewModel.onCreateNewGrowth()
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
        ){
            CreateGrowthScreen()
        }
    }
}