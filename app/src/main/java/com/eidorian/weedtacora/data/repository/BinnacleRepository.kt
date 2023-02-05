package com.eidorian.weedtacora.data.repository

import com.eidorian.weedtacora.data.dto.GrowthWithBinnacle
import com.eidorian.weedtacora.data.entities.Binnacle

interface BinnacleRepository {
    suspend fun insertBinnacle(binnacle: Binnacle): Long
    //suspend fun getAllBinnacles(): List<GrowthWithBinnacle>
    suspend fun getGrowthDetails(growthId: Int): List<Binnacle>
}