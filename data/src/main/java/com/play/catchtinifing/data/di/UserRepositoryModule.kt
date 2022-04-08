package com.play.catchtinifing.data.di

import com.play.catchtinifing.data.PreferenceUserRepository
import com.play.catchtinifing.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserRepository(preferenceUserRepository: PreferenceUserRepository): UserRepository
}