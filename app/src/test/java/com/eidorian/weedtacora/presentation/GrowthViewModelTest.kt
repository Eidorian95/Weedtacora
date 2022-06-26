package com.eidorian.weedtacora.presentation

import CoroutinesTestRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eidorian.weedtacora.bussinesslogic.usecase.GetAllGrowthsUseCase
import com.eidorian.weedtacora.bussinesslogic.viewmodel.GrowthViewModel
import com.eidorian.weedtacora.data.entities.Growth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class GrowthViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: GrowthViewModel

    private lateinit var useCase: GetAllGrowthsUseCase

    @Before
    fun setup() {
        useCase = mockk()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `fetch user growths and get empty list`() {
        //dada la lista de cultido
        coEvery { useCase.invoke() } returns emptyList<Growth>()

        //cuando se inicializa el vm se busca la lista
        viewModel = GrowthViewModel(useCase, Dispatchers.Main)

        //entonces la lista es
        coroutinesTestRule.runBlockingTest {
            assertEquals(emptyList<Growth>(), viewModel.userGrowths.first())
        }
    }

    @Test
    fun `fetch all user growths`() {
        //dada la lista de cultido
        coEvery { useCase.invoke() } returns listOf(BASE_GROWTH)

        //cuando se inicializa el vm se busca la lista
        viewModel = GrowthViewModel(useCase, Dispatchers.Main)

        //entonces la lista es
        coroutinesTestRule.runBlockingTest {
            assertEquals(listOf(BASE_GROWTH), viewModel.userGrowths.first())
        }
    }


    companion object {
        val BASE_GROWTH = Growth(growthId = 0, initialDate = "23/3/21", notes = "sin notas", name = "primer cultivo")
    }
}