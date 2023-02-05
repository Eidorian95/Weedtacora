package com.eidorian.weedtacora.presentation.uimodel

data class GrowthUiModel(
    val id:Int,
    val initialDate: String,
    val notes: String,
    val name: String,
    val days:String,
    val stage: String
)