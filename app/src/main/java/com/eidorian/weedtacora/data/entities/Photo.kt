package com.eidorian.weedtacora.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    @ColumnInfo(name = "photo_id")
    @PrimaryKey(autoGenerate = true)
    val photoId: Int = 0,
    @ColumnInfo(name = "fk_binnacle_id")
    val fkBinnacleId: Int,
    val imageUrl:String
)