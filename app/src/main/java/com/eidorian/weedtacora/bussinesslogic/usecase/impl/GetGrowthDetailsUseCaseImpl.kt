package com.eidorian.weedtacora.bussinesslogic.usecase.impl

import com.eidorian.weedtacora.bussinesslogic.usecase.GetGrowthDetailsUseCase
import com.eidorian.weedtacora.data.entities.Binnacle
import com.eidorian.weedtacora.data.entities.GrowthWithBinnacle
import com.eidorian.weedtacora.data.repository.BinnacleRepository
import com.eidorian.weedtacora.data.repository.GrowthRepository
import javax.inject.Inject

class GetGrowthDetailsUseCaseImpl @Inject constructor(
    private val repository: BinnacleRepository
) : GetGrowthDetailsUseCase {
    override suspend fun invoke(growthId: Int): List<GrowthWithBinnacle> {
        return repository.getAllBinnacles()
    }
}