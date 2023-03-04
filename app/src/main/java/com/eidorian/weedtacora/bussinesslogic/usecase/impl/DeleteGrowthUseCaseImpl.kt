package com.eidorian.weedtacora.bussinesslogic.usecase.impl

import com.eidorian.weedtacora.bussinesslogic.usecase.DeleteGrowthUseCase
import com.eidorian.weedtacora.data.repository.GrowthRepository
import javax.inject.Inject

class DeleteGrowthUseCaseImpl @Inject constructor(
    private val repository: GrowthRepository
): DeleteGrowthUseCase {
    override suspend fun invoke(growthId: Int) {
        repository.deleteGrowth(growthId)
    }
}