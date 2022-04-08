package com.play.catchtinifing.data

import com.play.catchtinifing.data.db.TinifingDatabase
import com.play.catchtinifing.data.entity.TinifingCage
import com.play.catchtinifing.domain.repository.TinifingRepository
import com.play.catchtinifing.domain.tinifing.model.CatchingTinifing
import com.play.catchtinifing.domain.tinifing.model.Tinifing
import com.play.catchtinifing.mapper.toData
import com.play.catchtinifing.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.*
import javax.inject.Inject

internal class TinifingRepositoryImpl @Inject constructor(
    private val database: TinifingDatabase,
) : TinifingRepository {
    private val tinifingDao = database.getTinifingDao()
    private val tinifingCageDao = database.getTinifingCageDao()

    override fun findTinifing(id: Int): Flow<Tinifing> =
        tinifingDao.loadTinifing(id).map { it.toDomain() }
    
    override suspend fun insert(tinifings: List<Tinifing>) {
        tinifings.map { it.toData() }
            .run {
                tinifingDao.insertTinifings(this)
            }
    }

    override suspend fun catch(tinifing: Tinifing) {
        tinifingCageDao.insertToCage(
            TinifingCage(
                tinifing = tinifing.toData(),
                catchDateTime = createNowDateTime().toString()
            )
        )
    }

    private fun createNowDateTime(): LocalDateTime {
        val currentMoment: Instant = Clock.System.now()
        return currentMoment.toLocalDateTime(TimeZone.UTC)
    }

    override fun loadCatchingTinifings(): Flow<List<CatchingTinifing>> =
        tinifingCageDao.loadTinifingsInCage().map { tinifingCages ->
            tinifingCages.map { it.toDomain() }
        }

    override suspend fun deleteCatchingTinifingAll() =
        tinifingCageDao.deleteAll()

}