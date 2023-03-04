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
        //TODO: create an extension to map presentation to domain model
        return growthDao.insertGrowth(GrowthDTO(
            growthId = growth.growthId,
            initialDate = growth.initialDate,
            notes = growth.notes,
            name = growth.name
        ))
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

    override suspend fun deleteGrowth(growthId: Int) {
        growthDao.deleteGrowth(growthId)
    }
}