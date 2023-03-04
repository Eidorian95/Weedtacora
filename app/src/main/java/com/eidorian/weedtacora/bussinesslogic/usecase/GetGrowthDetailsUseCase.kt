package com.eidorian.weedtacora.bussinesslogic.usecase

import com.eidorian.weedtacora.data.entities.Binnacle

interface GetGrowthDetailsUseCase {
    suspend fun invoke(growthId: Int):List<Binnacle>
}