package com.eidorian.weedtacora.bussinesslogic.usecase

import com.eidorian.weedtacora.bussinesslogic.usecase.impl.GetAllGrowthsUseCaseImpl
import com.eidorian.weedtacora.data.entities.Growth
import com.eidorian.weedtacora.data.repository.impl.GrowthRepositoryImpl
import com.eidorian.weedtacora.presentation.uimodel.GrowthUiModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetAllGrowthsUseCaseImplTest {
    lateinit var useCase: GetAllGrowthsUseCase
    private var repo: GrowthRepositoryImpl = mockk()

    @Before
    fun setUp() {
        useCase = GetAllGrowthsUseCaseImpl(repo)
    }

    @Test
    fun `get growth from dao to entity model`() {
        runTest {
            coEvery { repo.getAllGrowths() } returns listOf(Growth(growthId = 0, initialDate = "15/15/1995", notes = "na", name = "name"))

            val result = useCase.invoke()

            assertEquals(listOf(GrowthUiModel(
                id = 0,
                initialDate = "15/15/1995",
                notes = "na",
                name = "name",
                days = "45",
                stage = "VEG"
            )), result)
        }
    }
}