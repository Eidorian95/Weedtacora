package com.eidorian.weedtacora.data.repository

import com.eidorian.weedtacora.data.entities.Growth

interface GrowthRepository {
     suspend fun insertGrowth(growth:Growth)
     suspend fun getAllGrowths():List<Growth>
}