package com.eidorian.weedtacora.bussinesslogic.usecase.impl

import com.eidorian.weedtacora.bussinesslogic.usecase.CreateGrowthUseCase
import com.eidorian.weedtacora.data.entities.Growth
import com.eidorian.weedtacora.data.repository.GrowthRepository
import javax.inject.Inject

class CreateGrowthUseCaseImpl @Inject constructor(
    private val repository: GrowthRepository
) : CreateGrowthUseCase {
    override suspend fun invoke(growth: Growth): Boolean {
        return repository.insertGrowth(growth = growth) != 0L
    }
}