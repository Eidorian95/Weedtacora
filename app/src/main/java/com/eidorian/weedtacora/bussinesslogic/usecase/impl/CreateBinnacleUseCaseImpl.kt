package com.eidorian.weedtacora.bussinesslogic.usecase.impl

import com.eidorian.weedtacora.bussinesslogic.usecase.CreateBinnacleUseCase
import com.eidorian.weedtacora.data.entities.Binnacle
import com.eidorian.weedtacora.data.repository.BinnacleRepository
import javax.inject.Inject

class CreateBinnacleUseCaseImpl  @Inject constructor(
    private val repository: BinnacleRepository
): CreateBinnacleUseCase {
    override suspend fun invoke(binnacle: Binnacle): Boolean {
        return repository.insertBinnacle(binnacle) != 0L
    }
}