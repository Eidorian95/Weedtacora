package com.eidorian.weedtacora.bussinesslogic.usecase

import com.eidorian.weedtacora.data.entities.Growth
import com.eidorian.weedtacora.data.repository.GrowthRepository
import javax.inject.Inject

class GetAllGrowthsUseCaseImpl @Inject constructor(
    private val repository: GrowthRepository
) : GetAllGrowthsUseCase {
    override suspend fun invoke(): List<Growth> {
        return repository.getAllGrowths()
    }
}