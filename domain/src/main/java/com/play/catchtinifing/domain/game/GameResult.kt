package com.play.catchtinifing.domain.game

sealed class GameResult {
    class Winner(val players: List<Player>) : GameResult()
    object Tie : GameResult()
}
