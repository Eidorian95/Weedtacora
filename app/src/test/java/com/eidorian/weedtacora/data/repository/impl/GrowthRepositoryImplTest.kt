package com.eidorian.weedtacora.data.repository.impl

import com.eidorian.weedtacora.data.dao.GrowthDao
import com.eidorian.weedtacora.data.dto.GrowthDTO
import com.eidorian.weedtacora.data.entities.Growth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GrowthRepositoryImplTest {
    lateinit var repo:GrowthRepositoryImpl
    private val dao:GrowthDao = mockk()
    @Before
    fun setUp() {
        repo = GrowthRepositoryImpl(dao)
    }

    @Test
    fun `get growth from dao to entity model`(){
        runTest {
            coEvery { dao.getAllGrowths() } returns listOf(GrowthDTO(growthId = 0, initialDate = "15/15/1995", notes = "na", name = "name"),)

            val result = repo.getAllGrowths()

            assertEquals(listOf(Growth(growthId = 0, initialDate = "15/15/1995", notes = "na", name = "name")), result)
        }
    }
}