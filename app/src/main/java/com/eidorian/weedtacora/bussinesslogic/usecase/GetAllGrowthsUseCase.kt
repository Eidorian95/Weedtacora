package com.eidorian.weedtacora.bussinesslogic.usecase

import com.eidorian.weedtacora.data.entities.Growth

interface GetAllGrowthsUseCase {
    suspend operator fun invoke():List<Growth>
}