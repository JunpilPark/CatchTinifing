package com.play.catchtinifing.domain.game

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.lang.RuntimeException

internal class SingleGameTest {

    private lateinit var players: List<Player>

    @BeforeEach
    fun setUp() {
        players = listOf(mockk(), mockk(), mockk())
    }

    @DisplayName("가위 바위 보 시에 모두 다른 수를 내서 비겼을 때 GameResult.Tie 가 나온다")
    @Test
    fun tieGameTest1() {
        every { players[0].getPiece() } returns GamePiece.Rock
        every { players[1].getPiece() } returns GamePiece.Paper
        every { players[2].getPiece() } returns GamePiece.Scissor

        val result = SingleGame(players).start()

        assertThat(result).isEqualTo(GameResult.Tie)
    }

    @DisplayName("가위 바위 보 시에 모두 같은 수를 내서 비겼을 때 GameResult.Tie 가 나온다")
    @Test
    fun tieGameTest2() {
        every { players[0].getPiece() } returns GamePiece.Rock
        every { players[1].getPiece() } returns GamePiece.Rock
        every { players[2].getPiece() } returns GamePiece.Rock

        val result = SingleGame(players).start()

        assertThat(result).isEqualTo(GameResult.Tie)
    }


    @DisplayName("가위 바위 보 시에 이겼을 때 GameResult.Winner 로 이긴 플레이어들이 나온다")
    @Test
    fun winGameTest() {
        every { players[0].getPiece() } returns GamePiece.Rock
        every { players[1].getPiece() } returns GamePiece.Rock
        every { players[2].getPiece() } returns GamePiece.Scissor

        val result = SingleGame(players).start() as GameResult.Winner

        assertThat(result.players).containsExactly(players[0], players[1])
    }

    @DisplayName("가위 바위 보 게임 참여 인원이 없을 때 IllegalStateException 이 발생한다")
    @Test
    fun emptyPlayerGameTest() {
        val exception = Assertions.assertThrows(IllegalStateException::class.java) {
            SingleGame(emptyList()).start()
        }

        assertThat(exception).isInstanceOf(IllegalStateException::class.java)
        assertThat(exception.message).isEqualTo("player is empty")
    }
}