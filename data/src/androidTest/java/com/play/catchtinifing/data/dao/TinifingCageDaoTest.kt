package com.play.catchtinifing.data.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.play.catchtinifing.data.db.TinifingDatabase
import com.play.catchtinifing.data.entity.Tinifing
import com.play.catchtinifing.data.entity.TinifingCage
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class TinifingCageDaoTest {
    lateinit var database: TinifingDatabase
    lateinit var tinifingCageDao: TinifingCageDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, TinifingDatabase::class.java).build()
        tinifingCageDao = database.getTinifingCageDao()
        tinifingCageDao.insertToCage(
            TinifingCage(
                tinifing = Tinifing(11232, "조아핑", "블라 블라", "a.png"),
                catchDateTime = "2022-03-27T15:28:28.906"
            )
        )
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `Tinifing_Cage_안의_모든_티니핑이_정상적으로_조회된다`() {
        val flow = tinifingCageDao.loadTinifingsInCage()
        val actual = runBlocking {
            flow.first()
        }

        assertThat(actual).containsExactly(
                TinifingCage(1,
                    Tinifing(11232, "조아핑", "블라 블라", "a.png"),
                    "2022-03-27T15:28:28.906"
                )
        )
    }

    @Test
    fun `Tinifing_Cage_안의_모든_티니핑이_삭제된다`() {
        val flow = tinifingCageDao.loadTinifingsInCage()

        tinifingCageDao.deleteAll()
        val actual = runBlocking {
            flow.first()
        }

        assertThat(actual).isEmpty()
    }

    @Test
    fun `Tinifing_Cage_안에_티니핑이_정상적으로_추가된다`() {
        val flow = tinifingCageDao.loadTinifingsInCage()

        tinifingCageDao.insertToCage(
            TinifingCage(2,
                Tinifing(21232, "시러핑", "시러 블라 블라", "b.png"),
                "2022-03-27T15:28:28.906"
            )
        )
        val actual = runBlocking {
            flow.first()
        }

        assertThat(actual).containsExactly(
            TinifingCage(1,
                Tinifing(11232, "조아핑", "블라 블라", "a.png"),
                "2022-03-27T15:28:28.906"
            ),
            TinifingCage(2,
                Tinifing(21232, "시러핑", "시러 블라 블라", "b.png"),
                "2022-03-27T15:28:28.906"
            )
        )
    }
}