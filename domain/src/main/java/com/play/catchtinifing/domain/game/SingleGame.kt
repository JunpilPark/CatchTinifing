package com.play.catchtinifing.domain.game

class SingleGame(
    private val players: List<Player>,
) : Game {

    init {
        check(players.isNotEmpty()) {
            "player is empty"
        }
    }

    override fun start(): GameResult {
        val groupByPiece = players.groupBy { it.getPiece() }
        val countOfNotEmptyGroup = groupByPiece.filter { it.value.isNotEmpty() }.count()

        if (countOfNotEmptyGroup != 2) {
            return GameResult.Tie
        } else {
            val winPiece = groupByPiece.map { it.key }.reduce { gamePiece1, gamePiece2 ->
                when (gamePiece1.fight(gamePiece2)) {
                    OneToOneGameResult.Win -> gamePiece1
                    OneToOneGameResult.Tie -> return GameResult.Tie
                    OneToOneGameResult.Loss -> gamePiece2
                }
            }
            return GameResult.Winner(groupByPiece[winPiece]!!)
        }
    }
}

