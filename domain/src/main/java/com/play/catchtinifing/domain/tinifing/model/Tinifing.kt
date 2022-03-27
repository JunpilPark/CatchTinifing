package com.play.catchtinifing.domain.tinifing.model

import com.play.catchtinifing.domain.game.GamePiece
import com.play.catchtinifing.domain.game.Player
import kotlin.random.Random

data class Tinifing(
    override val id: Int,
    val name: String,
    val description: String,
    val imgSrc: String
) : Player {
    private var piece: GamePiece = GamePiece.Rock

    override fun getPiece(): GamePiece = piece

    override fun play(gamePiece: GamePiece) {
        piece = gamePiece
    }
}