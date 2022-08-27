package com.eidorian.weedtacora.data.repository

import com.eidorian.weedtacora.data.entities.Binnacle
import com.eidorian.weedtacora.data.entities.GrowthWithBinnacle

interface BinnacleRepository {
    suspend fun insertBinnacle(binnacle: Binnacle): Long
    suspend fun getAllBinnacles(): List<GrowthWithBinnacle>
}