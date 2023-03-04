package com.eidorian.weedtacora.data.repository

import com.eidorian.weedtacora.data.entities.Growth

interface GrowthRepository {
    suspend fun insertGrowth(growth: Growth): Long
    suspend fun getAllGrowths(): List<Growth>
    suspend fun deleteGrowth(growthId:Int)
}