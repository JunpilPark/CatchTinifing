package com.play.catchtinifing.data.di

import android.content.Context
import com.play.catchtinifing.data.db.TinifingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TinifingDatabase {
        return TinifingDatabase.buildDatabase(context)
    }
}