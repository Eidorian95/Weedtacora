package com.eidorian.weedtacora.data.entities

import androidx.room.*

data class Growth(
    val growthId: Int = 0,
    val initialDate: String,
    val notes: String,
    val name: String
)
