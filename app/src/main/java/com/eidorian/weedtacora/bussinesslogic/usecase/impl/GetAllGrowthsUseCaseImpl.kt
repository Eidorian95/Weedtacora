package com.eidorian.weedtacora.bussinesslogic.usecase.impl

import com.eidorian.weedtacora.bussinesslogic.usecase.GetAllGrowthsUseCase
import com.eidorian.weedtacora.data.repository.GrowthRepository
import com.eidorian.weedtacora.presentation.uimodel.GrowthUiModel
import javax.inject.Inject

class GetAllGrowthsUseCaseImpl @Inject constructor(
    private val repository: GrowthRepository
) : GetAllGrowthsUseCase {
    override suspend operator fun invoke(): List<GrowthUiModel> {
        return repository.getAllGrowths().map {
            GrowthUiModel(
                id = it.growthId,
                initialDate = it.initialDate,
                notes = it.notes,
                name = it.name,
                days = "45",
                stage = "VEG"
            )
        }
    }
}