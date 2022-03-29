package com.play.catchtinifing.data

import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.play.catchtinifing.domain.user.model.User
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test


class PreferenceUserRepositoryTest {
    private lateinit var userRepository: PreferenceUserRepository

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        userRepository = PreferenceUserRepository(context)
    }

    @After
    fun tearDown() = runBlocking {
        userRepository.removeAll()
    }

    @Test
    fun `repository_에_저장된_USER_정보가_있을_때_저장된_USER를_반환한다`() {
        val savedUser = User(123, "TESTER")
        runBlocking {
            userRepository.insertUser(savedUser)
        }

        val actual = runBlocking {
            userRepository.getUser()
        }

        assertThat(actual).isEqualTo(User(123, "TESTER"))
    }

    @Test
    fun `repository_에_저장된_USER_정보가_없을_때_NULL을_반환한다`() {
        val actual = runBlocking {
            userRepository.getUser()
        }

        assertThat(actual).isNull()
    }

    @Test
    fun `repository_에_정상적으로__USER가_저장된다`() {

        val actual = runBlocking {
            userRepository.insertUser(User(123, "TESTER"))
            userRepository.getUser()
        }

        assertThat(actual).isEqualTo(User(123, "TESTER"))
    }
}