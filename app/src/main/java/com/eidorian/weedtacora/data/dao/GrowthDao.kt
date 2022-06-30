package com.eidorian.weedtacora.data.dao

import androidx.room.*
import com.eidorian.weedtacora.data.entities.Growth
import com.eidorian.weedtacora.data.entities.GrowthWithBinnacle

@Dao
interface GrowthDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGrowth(growth: Growth): Long

    @Transaction
    @Query("SELECT * FROM growth ORDER BY initial_date ASC")
    fun getAllGrowths(): List<Growth>

    @Transaction
    @Query("SELECT * FROM growth")
    fun getAllBinnacles(): List<GrowthWithBinnacle>

}