package com.eidorian.weedtacora.data.repository.impl

import com.eidorian.weedtacora.data.dao.GrowthDao
import com.eidorian.weedtacora.data.dto.GrowthDTO
import com.eidorian.weedtacora.data.entities.Growth
import com.eidorian.weedtacora.data.repository.GrowthRepository
import javax.inject.Inject

class GrowthRepositoryImpl @Inject constructor(
    private val growthDao: GrowthDao
) : GrowthRepository {
    override suspend fun insertGrowth(growth: Growth): Long {
        return growthDao.insertGrowth(GrowthDTO(growthId = 0, initialDate = "", notes = "", name = ""))
    }

    override suspend fun getAllGrowths(): List<Growth> {
        return growthDao.getAllGrowths().map {
            Growth(
                growthId = it.growthId,
                initialDate = it.initialDate,
                notes = it.notes,
                name = it.name
            )
        }
    }
}