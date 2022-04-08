package com.play.catchtinifing.data.di

import com.play.catchtinifing.TinifingRepositoryTestImpl
import com.play.catchtinifing.domain.repository.TinifingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class TinifingRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTinifingRepository(tinifingRepositoryImpl: TinifingRepositoryTestImpl): TinifingRepository
}
