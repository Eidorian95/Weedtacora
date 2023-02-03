package com.eidorian.weedtacora.bussinesslogic.viewmodel

import CoroutinesTestRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.eidorian.weedtacora.bussinesslogic.usecase.GetGrowthDetailsUseCase
import com.eidorian.weedtacora.data.entities.Binnacle
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GrowthDetailsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: GrowthDetailViewModel

    private lateinit var useCase: GetGrowthDetailsUseCase

    private var growthDetailsObserver: Observer<List<Binnacle>> = mockk()

    @Before
    fun setup() {
        useCase = mockk()
        viewModel = GrowthDetailViewModel(useCase, Dispatchers.Main)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `fetch growth details and get empty list`() {
        viewModel.growthDetails.observeForever(growthDetailsObserver)
        coEvery { useCase.invoke(1) } returns emptyList<Binnacle>()

        viewModel.fetchGrowthDetails(1)

        verify { growthDetailsObserver.onChanged(emptyList()) }
    }

    @Test
    fun `fetch growth details`() {
        viewModel.growthDetails.observeForever(growthDetailsObserver)
        coEvery { useCase.invoke(1) } returns listOf(BASE_BINNACLE)

        viewModel.fetchGrowthDetails(1)

        verify { growthDetailsObserver.onChanged(listOf(BASE_BINNACLE)) }
    }


    companion object {
        val BASE_BINNACLE = Binnacle(binnacleId = 0, fkGrowthId = 1, date = "15/03/1995", observation = "sin observaciones", stage = "Seedling")
    }
}