package com.play.catchtinifing.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.play.catchtinifing.data.entity.Tinifing
import kotlinx.coroutines.flow.Flow

@Dao
interface TinifingDao {
    @Query("SELECT * FROM tinifing")
    fun loadTinifings(): Flow<List<Tinifing>>

    @Query("SELECT * FROM TINIFING WHERE id = :tinifingId")
    fun loadTinifing(tinifingId: Int): Flow<Tinifing>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTinifings(tinifings: List<Tinifing>)
}