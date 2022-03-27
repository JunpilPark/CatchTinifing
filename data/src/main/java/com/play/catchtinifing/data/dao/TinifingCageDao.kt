package com.play.catchtinifing.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.play.catchtinifing.data.entity.TinifingCage
import kotlinx.coroutines.flow.Flow

@Dao
interface TinifingCageDao {
    @Insert(onConflict = REPLACE)
    fun insertToCage(tinifingCage: TinifingCage)

    @Query("SELECT * FROM tinifing_cage")
    fun loadTinifingsInCage(): Flow<List<TinifingCage>>

    @Query("DELETE FROM tinifing_cage")
    fun deleteAll()
}