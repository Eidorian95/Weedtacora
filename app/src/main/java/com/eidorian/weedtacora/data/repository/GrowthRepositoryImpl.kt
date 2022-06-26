package com.eidorian.weedtacora.data.repository

import com.eidorian.weedtacora.data.dao.GrowthDao
import com.eidorian.weedtacora.data.entities.Growth
import javax.inject.Inject

class GrowthRepositoryImpl @Inject constructor(
    private val growthDao: GrowthDao
) : GrowthRepository {
    override suspend fun insertGrowth(growth: Growth) {
        growthDao.insertGrowth(growth)
    }

    override suspend fun getAllGrowths():List<Growth> {
        return growthDao.getAllGrowths()
    }
}