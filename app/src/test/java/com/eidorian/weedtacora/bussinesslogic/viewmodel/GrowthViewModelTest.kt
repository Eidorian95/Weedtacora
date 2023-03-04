package com.eidorian.weedtacora.bussinesslogic.viewmodel

import CoroutinesTestRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eidorian.weedtacora.bussinesslogic.usecase.DeleteGrowthUseCase
import com.eidorian.weedtacora.bussinesslogic.usecase.GetAllGrowthsUseCase
import com.eidorian.weedtacora.presentation.uimodel.GrowthUiModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.*

@ExperimentalCoroutinesApi
class GrowthViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: GrowthViewModel

    private lateinit var getAllGrowths: GetAllGrowthsUseCase
    private lateinit var deleteGrowth: DeleteGrowthUseCase

    @Before
    fun setup() {
        getAllGrowths = mockk()
        deleteGrowth = mockk()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `fetch user growths and get empty list`() = runTest {
        coEvery { getAllGrowths.invoke() } returns emptyList<GrowthUiModel>()

        viewModel = GrowthViewModel(getAllGrowths,deleteGrowth, Dispatchers.Main)

        assertEquals(emptyList<GrowthUiModel>(), viewModel.userGrowths.first())
    }

    @Test
    fun `fetch all user growths`() = runTest {
        coEvery { getAllGrowths.invoke() } returns listOf(BASE_GROWTH)

        viewModel = GrowthViewModel(getAllGrowths,deleteGrowth, Dispatchers.Main)


        assertEquals(listOf(BASE_GROWTH), viewModel.userGrowths.first())

    }


    @Test
    fun `delete growth`() = runTest {
        coEvery { deleteGrowth(0) } returns true
        coEvery { getAllGrowths.invoke() } returns LIST_GROWTHS
        viewModel = GrowthViewModel(getAllGrowths, deleteGrowth, Dispatchers.Main)

        viewModel.onDeleteGrowth(0)

        coVerify { deleteGrowth(0) }
    }


    companion object {
        val BASE_GROWTH = GrowthUiModel(
            id = 0,
            initialDate = "23/3/21",
            notes = "sin notas",
            name = "primer cultivo",
            "45",
            "VEG"
        )

        private val DIFFERENT_GROWTH = GrowthUiModel(
            id = 1,
            initialDate = "23/3/21",
            notes = "sin notas",
            name = "primer cultivo",
            "45",
            "VEG"
        )

        val LIST_GROWTHS = listOf<GrowthUiModel>(
            BASE_GROWTH,
            DIFFERENT_GROWTH
        )
    }
}