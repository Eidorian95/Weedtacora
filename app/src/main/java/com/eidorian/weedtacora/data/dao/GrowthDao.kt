package com.eidorian.weedtacora.data.dao

import androidx.room.*
import com.eidorian.weedtacora.data.dto.GrowthDTO
import com.eidorian.weedtacora.data.dto.GrowthWithBinnacle
import com.eidorian.weedtacora.data.entities.Binnacle

@Dao
interface GrowthDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGrowth(growth: GrowthDTO): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBinnacle(binnacle: Binnacle): Long

    @Transaction
    @Query("SELECT * FROM GrowthDTO ORDER BY initial_date ASC")
    fun getAllGrowths(): List<GrowthDTO>

    @Transaction
    @Query("SELECT * FROM GrowthDTO ORDER BY initial_date ASC")
    fun getAllBinnacles(): List<GrowthWithBinnacle>

    @Transaction
    @Query("SELECT * FROM Binnacle WHERE fk_growth_id = :growthId")
    fun getGrowthDetails(growthId:Int): List<Binnacle>
}