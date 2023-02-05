package com.eidorian.weedtacora.bussinesslogic.usecase

import com.eidorian.weedtacora.data.entities.Growth
import com.eidorian.weedtacora.presentation.uimodel.GrowthUiModel

interface GetAllGrowthsUseCase {
    suspend operator fun invoke():List<GrowthUiModel>
}