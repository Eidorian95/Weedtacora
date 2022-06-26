package com.eidorian.weedtacora.bussinesslogic.usecase

import com.eidorian.weedtacora.data.entities.Growth

interface GetAllGrowthsUseCase {
    suspend fun invoke():List<Growth>
}