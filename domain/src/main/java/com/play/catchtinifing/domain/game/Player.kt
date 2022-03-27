package com.play.catchtinifing.domain.game

interface Player {
    val id: Int
    fun getPiece(): GamePiece
    fun play(gamePiece: GamePiece)
}