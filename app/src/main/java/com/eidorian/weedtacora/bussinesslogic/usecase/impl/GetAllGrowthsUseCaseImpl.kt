package com.eidorian.weedtacora.bussinesslogic.usecase.impl

import com.eidorian.weedtacora.bussinesslogic.usecase.GetAllGrowthsUseCase
import com.eidorian.weedtacora.data.entities.Growth
import com.eidorian.weedtacora.data.repository.GrowthRepository
import javax.inject.Inject

class GetAllGrowthsUseCaseImpl @Inject constructor(
    private val repository: GrowthRepository
) : GetAllGrowthsUseCase {
    override suspend operator fun invoke(): List<Growth> {
        return repository.getAllGrowths()
    }
}