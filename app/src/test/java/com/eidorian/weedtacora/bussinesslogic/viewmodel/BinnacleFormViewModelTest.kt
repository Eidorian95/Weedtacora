package com.eidorian.weedtacora.bussinesslogic.viewmodel

import CoroutinesTestRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eidorian.weedtacora.bussinesslogic.usecase.CreateBinnacleUseCase
import com.eidorian.weedtacora.presentation.events.UiState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BinnacleFormViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: BinnacleFormViewModel

    private lateinit var useCase: CreateBinnacleUseCase

    @Before
    fun setup() {
        useCase = mockk()
        viewModel = BinnacleFormViewModel(useCase, Dispatchers.Main)
        viewModel.stage = "Seedling"
        viewModel.observation = "nuevo cultivo test"
        viewModel.date = "15/03/1995"
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }


    @Test
    fun `create a new growth successfully`() {
        givenUseCase(true)

        viewModel.onCreateNewBinnacle(1)

        coroutinesTestRule.runBlockingTest {
            assertEquals(UiState.Success, viewModel.uiEvents.first())
        }
    }

    @Test
    fun `create a new growth failed`() {
        givenUseCase(false)

        viewModel.onCreateNewBinnacle(1)

        coroutinesTestRule.runBlockingTest {
            assertEquals(UiState.Failure, viewModel.uiEvents.first())
        }
    }

    private fun givenUseCase(isSuccessfully: Boolean) {
        coEvery {
            useCase(any())
        } returns isSuccessfully
    }
}