package com.play.catchtinifing.domain.game

enum class GamePiece {
    Rock,
    Paper,
    Scissor;

    fun fight(enemyPiece: GamePiece): OneToOneGameResult {
        return when(this) {
            Rock -> {
                when(enemyPiece) {
                    Rock -> OneToOneGameResult.Tie
                    Paper -> OneToOneGameResult.Loss
                    Scissor -> OneToOneGameResult.Win
                }
            }
            Paper -> {
                when(enemyPiece) {
                    Rock -> OneToOneGameResult.Win
                    Paper -> OneToOneGameResult.Tie
                    Scissor -> OneToOneGameResult.Loss
                }
            }
            Scissor -> {
                when(enemyPiece) {
                    Rock -> OneToOneGameResult.Loss
                    Paper -> OneToOneGameResult.Win
                    Scissor -> OneToOneGameResult.Tie
                }
            }
        }
    }
}