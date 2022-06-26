package com.eidorian.weedtacora.bussinesslogic.usecase

import com.eidorian.weedtacora.data.entities.Growth
import javax.inject.Inject

class GetAllGrowthsUseCaseImpl @Inject constructor(): GetAllGrowthsUseCase {
    override suspend fun invoke(): List<Growth> {
        return emptyList()
    }
}