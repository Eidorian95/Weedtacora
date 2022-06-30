package com.eidorian.weedtacora.bussinesslogic.usecase

import com.eidorian.weedtacora.data.entities.Growth

interface CreateGrowthUseCase {

    suspend operator fun invoke(growth: Growth): Boolean
}