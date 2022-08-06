package com.eidorian.weedtacora.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity
data class Binnacle(
    @ColumnInfo(name = "binnacle_id")
    @PrimaryKey(autoGenerate = true)
    val binnacleId: Int = 0,
    @ColumnInfo(name = "fk_growth_id")
    val fkGrowthId: Int,
    val date: String,
    val observation: String,
    val stage: String
)

data class BinnacleWithPhoto(
    @Embedded
    val binnacle: Binnacle,
    @Relation(parentColumn = "binnacle_id", entityColumn = "fk_binnacle_id")
    val photos: List<Photo>
)
