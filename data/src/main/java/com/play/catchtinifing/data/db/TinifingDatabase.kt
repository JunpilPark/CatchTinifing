package com.play.catchtinifing.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.play.catchtinifing.data.dao.TinifingCageDao
import com.play.catchtinifing.data.dao.TinifingDao
import com.play.catchtinifing.data.entity.Tinifing
import com.play.catchtinifing.data.entity.TinifingCage


@Database(
    version = 1,
    entities = [
        Tinifing::class,
        TinifingCage::class
    ]
)
abstract class TinifingDatabase: RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "tinifing.db"

        fun buildDatabase(context: Context): TinifingDatabase {
            return Room.databaseBuilder(
                context,
                TinifingDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }

    abstract fun getTinifingDao(): TinifingDao
    abstract fun getTinifingCageDao(): TinifingCageDao
}