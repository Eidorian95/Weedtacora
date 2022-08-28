package com.eidorian.weedtacora.bussinesslogic.usecase

import com.eidorian.weedtacora.data.entities.Binnacle
import com.eidorian.weedtacora.data.entities.GrowthWithBinnacle

interface GetGrowthDetailsUseCase {
    suspend fun invoke(growthId: Int):List<Binnacle>
}