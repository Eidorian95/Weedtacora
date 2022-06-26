package com.eidorian.weedtacora.data.entities

import androidx.room.*


@Entity
data class Growth(
    @ColumnInfo(name = "growth_id")
    @PrimaryKey(autoGenerate = true)
    val growthId: Int = 0,
    @ColumnInfo(name = "initial_date")
    val initialDate: String,
    val notes: String,
    val name: String
)


@Entity
data class Binnacle(
    @ColumnInfo(name = "binnacle_id")
    @PrimaryKey(autoGenerate = true)
    val binnacleId: Int = 0,
    @ColumnInfo(name = "fk_growth_id")
    val fkGrowthId: Int,
    val date: String,
    val observation: String
    //val photos: List<String>
)

data class GrowthWithBinnacle(
    @Embedded
    val growth: Growth,
    @Relation(parentColumn = "growth_id", entityColumn = "fk_growth_id")
    val binnacle: List<Binnacle>
)
