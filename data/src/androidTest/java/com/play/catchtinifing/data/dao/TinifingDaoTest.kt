package com.play.catchtinifing.data.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.play.catchtinifing.data.db.TinifingDatabase
import com.play.catchtinifing.data.entity.Tinifing
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class TinifingDaoTest {
    private lateinit var database: TinifingDatabase
    private lateinit var tinifingDao: TinifingDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, TinifingDatabase::class.java).build()
        tinifingDao = database.getTinifingDao()
    }


    @After
    fun teatDown() {
        database.close()
    }

    @Test
    fun `모든_Tinifing이_정상적으로_조회된다`() {
        tinifingDao.insertTinifings(
            listOf(
                Tinifing(11232, "조아핑", "블라 블라", "a.png"),
                Tinifing(21231, "시러핑", "시러 시러", "b.png"),
                Tinifing(31241, "공주핑", "블라 블라 블라", "c.png")
            )
        )

        val flow = tinifingDao.loadTinifings()
        val actual = runBlocking {
            flow.first()
        }

        assertThat(actual).containsExactly(
            Tinifing(11232, "조아핑", "블라 블라", "a.png"),
            Tinifing(21231, "시러핑", "시러 시러", "b.png"),
            Tinifing(31241, "공주핑", "블라 블라 블라", "c.png")
        )
    }

    @Test
    fun `티니핑의_ID_로_정상적으로_조회된다`() {
        tinifingDao.insertTinifings(
            listOf(
                Tinifing(11232, "조아핑", "블라 블라", "a.png"),
                Tinifing(21231, "시러핑", "시러 시러", "b.png"),
                Tinifing(31241, "공주핑", "블라 블라 블라", "c.png")
            )
        )

        val flow = tinifingDao.loadTinifing(11232)
        val actual = runBlocking {
            flow.first()
        }

        assertThat(actual).isEqualTo(
            Tinifing(11232, "조아핑", "블라 블라", "a.png"),
        )
    }

    @Test
    fun `티니핑이_정상적으로_Insert_된다`() {
        val flow = tinifingDao.loadTinifings()

        tinifingDao.insertTinifings(
            listOf(
                Tinifing(11232, "조아핑", "블라 블라", "a.png"),
            )
        )
        val actual = runBlocking {
            flow.first()
        }

        assertThat(actual).containsExactly(
            Tinifing(11232, "조아핑", "블라 블라", "a.png"),
        )
    }
}