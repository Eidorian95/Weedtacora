package com.eidorian.weedtacora.bussinesslogic.usecase

import com.eidorian.weedtacora.data.entities.Binnacle

interface CreateBinnacleUseCase {

    suspend operator fun invoke(binnacle: Binnacle): Boolean
}