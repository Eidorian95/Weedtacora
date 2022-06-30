package com.eidorian.weedtacora.bussinesslogic.viewmodel

import CoroutinesTestRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eidorian.weedtacora.bussinesslogic.usecase.CreateGrowthUseCase
import com.eidorian.weedtacora.data.entities.Growth
import com.eidorian.weedtacora.presentation.events.UiEvent
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
class CreatorViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: CreatorViewModel

    private lateinit var useCase: CreateGrowthUseCase

    @Before
    fun setup() {
        useCase = mockk()
        viewModel = CreatorViewModel(useCase, Dispatchers.Main)
    }

    @After
    fun tearDown() {

    }


    @Test
    fun `create a new growth successfully`() {
        givenUseCase(true)

        viewModel.onCreateNewGrowth("Primer cultivo", "15/03/1995", "sin notas")

        coroutinesTestRule.runBlockingTest {
            assertEquals(UiEvent.Success, viewModel.uiEvents.first())
        }
    }

    @Test
    fun `create a new growth failed`() {
        givenUseCase(false)

        viewModel.onCreateNewGrowth("Primer cultivo", "15/03/1995", "sin notas")

        coroutinesTestRule.runBlockingTest {
            assertEquals(UiEvent.Failure, viewModel.uiEvents.first())
        }
    }

    private fun givenUseCase(isSuccessfully: Boolean) {
        coEvery { useCase(Growth(initialDate = "15/03/1995", notes = "sin notas", name = "Primer cultivo")) } returns isSuccessfully
    }
}