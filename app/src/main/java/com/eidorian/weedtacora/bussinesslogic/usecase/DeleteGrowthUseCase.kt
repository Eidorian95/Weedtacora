package com.eidorian.weedtacora.bussinesslogic.usecase

interface DeleteGrowthUseCase {
    suspend operator fun invoke(growthId:Int)
}