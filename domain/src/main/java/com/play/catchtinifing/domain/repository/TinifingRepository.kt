package com.play.catchtinifing.domain.repository

import com.play.catchtinifing.domain.tinifing.model.CatchingTinifing
import com.play.catchtinifing.domain.tinifing.model.Tinifing
import kotlinx.coroutines.flow.Flow

interface TinifingRepository {
    fun findTinifing(id: Int): Flow<Tinifing>
    suspend fun insert(tinifings: List<Tinifing>)

    suspend fun catch(tinifing: Tinifing)
    fun loadCatchingTinifings(): Flow<List<CatchingTinifing>>
    suspend fun deleteCatchingTinifingAll()
}