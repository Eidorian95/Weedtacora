package com.eidorian.weedtacora.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eidorian.weedtacora.data.dao.GrowthDao
import com.eidorian.weedtacora.data.dto.GrowthDTO
import com.eidorian.weedtacora.data.entities.Binnacle
import com.eidorian.weedtacora.data.entities.Growth

@Database(entities = [GrowthDTO::class, Binnacle::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun growthDao(): GrowthDao
}