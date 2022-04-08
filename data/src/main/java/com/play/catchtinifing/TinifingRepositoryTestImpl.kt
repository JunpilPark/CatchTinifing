package com.play.catchtinifing

import com.play.catchtinifing.domain.error.Errors
import com.play.catchtinifing.domain.repository.TinifingRepository
import com.play.catchtinifing.domain.tinifing.model.CatchingTinifing
import com.play.catchtinifing.domain.tinifing.model.Tinifing
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class TinifingRepositoryTestImpl: TinifingRepository {
    val list =  listOf(
        Tinifing(1, "좋아핑", "좋아 좋아 좋아", "httpqwe"),
        Tinifing(2, "시러핑", "시러 시러 시러", "httpqwe")
    )

    override fun findTinifing(id: Int): Flow<Tinifing> {
        return flow {
            val tinifing = list.find { tinifing -> tinifing.id == id }
            if(tinifing != null) {
                emit(tinifing)
            } else {
                throw Errors.InvalidCatchTinifingId(message = "$id ID 를 가진 티니필은 없습니다.")
            }
        }
    }

    override suspend fun insert(tinifings: List<Tinifing>) {
        TODO("Not yet implemented")
    }

    override suspend fun catch(tinifing: Tinifing) {
        TODO("Not yet implemented")
    }

    override fun loadCatchingTinifings(): Flow<List<CatchingTinifing>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCatchingTinifingAll() {
        TODO("Not yet implemented")
    }
}