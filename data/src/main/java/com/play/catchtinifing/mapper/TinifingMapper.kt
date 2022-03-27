package com.play.catchtinifing.mapper

import com.play.catchtinifing.data.entity.Tinifing
import com.play.catchtinifing.data.entity.TinifingCage
import com.play.catchtinifing.domain.tinifing.model.CatchingTinifing
import kotlinx.datetime.toLocalDateTime
import com.play.catchtinifing.domain.tinifing.model.Tinifing as DomainTinifing

fun Tinifing.toDomain(): DomainTinifing {
    return DomainTinifing(
        this.id,
        this.name,
        this.description,
        this.imgSrc
    )
}

fun DomainTinifing.toData(): Tinifing {
    return Tinifing(
        this.id,
        this.name,
        this.description,
        this.imgSrc
    )
}

fun TinifingCage.toDomain(): CatchingTinifing {
    return CatchingTinifing(
        this.tinifing.toDomain(),
        this.catchDateTime.toLocalDateTime()
    )
}