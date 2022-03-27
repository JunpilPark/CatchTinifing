package com.play.catchtinifing.domain.tinifing.model

import kotlinx.datetime.LocalDateTime

data class CatchingTinifing (
    val tinifing: Tinifing,
    val createDateTime: LocalDateTime
)