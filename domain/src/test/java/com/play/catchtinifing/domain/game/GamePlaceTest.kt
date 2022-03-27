package com.play.catchtinifing.domain.game

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GamePlaceTest {

    @DisplayName("가위 VS 가위 일 경우 무승부가 된다")
    @Test
    fun scissorVerseScissorFightTest() {
        val piece = GamePiece.Scissor

        val actual = piece.fight(GamePiece.Scissor)

        assertThat(actual).isEqualTo(OneToOneGameResult.Tie)
    }

    @DisplayName("가위 VS 바위 일 경우 진다")
    @Test
    fun scissorVerseRockFightTest() {
        val piece = GamePiece.Scissor

        val actual = piece.fight(GamePiece.Rock)

        assertThat(actual).isEqualTo(OneToOneGameResult.Loss)
    }

    @DisplayName("가위 VS 보 일 경우 이긴다")
    @Test
    fun scissorVersePaperFightTest() {
        val piece = GamePiece.Scissor

        val actual = piece.fight(GamePiece.Paper)

        assertThat(actual).isEqualTo(OneToOneGameResult.Win)
    }

    @DisplayName("바위 VS 가위 일 경우 이긴다")
    @Test
    fun rockVerseScissorFightTest() {
        val piece = GamePiece.Rock

        val actual = piece.fight(GamePiece.Scissor)

        assertThat(actual).isEqualTo(OneToOneGameResult.Win)
    }

    @DisplayName("바위 VS 바위 일 경우 무승부가 된다")
    @Test
    fun rockVerseRockFightTest() {
        val piece = GamePiece.Rock

        val actual = piece.fight(GamePiece.Rock)

        assertThat(actual).isEqualTo(OneToOneGameResult.Tie)
    }

    @DisplayName("바위 VS 보 일 경우 진다")
    @Test
    fun rockVersePaperFightTest() {
        val piece = GamePiece.Rock

        val actual = piece.fight(GamePiece.Paper)

        assertThat(actual).isEqualTo(OneToOneGameResult.Loss)
    }

    @DisplayName("보 VS 가위 일 경우 진다")
    @Test
    fun paperVerseScissorFightTest() {
        val piece = GamePiece.Paper

        val actual = piece.fight(GamePiece.Scissor)

        assertThat(actual).isEqualTo(OneToOneGameResult.Loss)
    }

    @DisplayName("보 VS 바위 일 경우 이긴다")
    @Test
    fun paperVerseRockFightTest() {
        val piece = GamePiece.Paper

        val actual = piece.fight(GamePiece.Rock)

        assertThat(actual).isEqualTo(OneToOneGameResult.Win)
    }

    @DisplayName("보 VS 보 일 경우 무승부가 된다")
    @Test
    fun paperVersePaperFightTest() {
        val piece = GamePiece.Paper

        val actual = piece.fight(GamePiece.Paper)

        assertThat(actual).isEqualTo(OneToOneGameResult.Tie)
    }
}