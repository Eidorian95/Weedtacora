package com.eidorian.weedtacora.data.repository.impl

import com.eidorian.weedtacora.data.dao.GrowthDao
import com.eidorian.weedtacora.data.entities.Binnacle
import com.eidorian.weedtacora.data.repository.BinnacleRepository
import javax.inject.Inject

class BinnacleRepositoryImpl @Inject constructor(
    private val growthDao: GrowthDao
) : BinnacleRepository {
    override suspend fun insertBinnacle(binnacle: Binnacle): Long {
        return growthDao.insertBinnacle(binnacle)
    }

   /* override suspend fun getAllBinnacles(): List<GrowthWithBinnacle> {
        return growthDao.getAllBinnacles()
    }*/

    override suspend fun getGrowthDetails(growthId: Int): List<Binnacle> {
        return growthDao.getGrowthDetails(growthId)
    }
}