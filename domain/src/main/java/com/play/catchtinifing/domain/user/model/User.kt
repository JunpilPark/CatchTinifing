package com.play.catchtinifing.domain.user.model

import com.play.catchtinifing.domain.game.GamePiece
import com.play.catchtinifing.domain.game.Player

class User(override val id: Int, val name: String) : Player {
    private var gamePiece: GamePiece = GamePiece.Rock

    override fun getPiece(): GamePiece = gamePiece

    override fun play(gamePiece: GamePiece) {
        this.gamePiece = gamePiece
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        return result
    }
}