package com.eidorian.weedtacora.data.dto

import androidx.room.*
import com.eidorian.weedtacora.data.entities.Binnacle

@Entity
data class GrowthDTO(
    @ColumnInfo(name = "growth_id")
    @PrimaryKey(autoGenerate = true)
    val growthId: Int = 0,
    @ColumnInfo(name = "initial_date")
    val initialDate: String,
    val notes: String,
    val name: String
)

data class GrowthWithBinnacle(
    @Embedded
    val growth: GrowthDTO,
    @Relation(parentColumn = "growth_id", entityColumn = "fk_growth_id", entity = Binnacle::class)
    val binnacle: List<Binnacle>
)
