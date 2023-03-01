package com.eidorian.weedtacora.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.eidorian.weedtacora.presentation.uimodel.GrowthUiModel


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

@Preview
@Composable
private fun HomeScreenPreview() {
    val navController = rememberNavController()
    val list = mutableListOf<GrowthUiModel>().apply {
        repeat(5) {
            this.add(
                GrowthUiModel(
                    it,
                    "15/03/1995",
                    "sin notas",
                    "Silver River Cri-Tropical Haze",
                    "45",
                    "VEG"
                )
            )
        }
    }
    HomeScreenContent(growthList = list, navController = navController)
}

