package com.play.catchtinifing.data.di

import com.play.catchtinifing.TinifingRepositoryMemory
import com.play.catchtinifing.domain.repository.TinifingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class TinifingRepositoryModule {

    @Provides
    fun provideTinifingRepository(): TinifingRepository {
        return TinifingRepositoryMemory()
    }
}
