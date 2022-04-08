package com.play.catchtinifing.present.mapper

import com.play.catchtinifing.domain.tinifing.model.Tinifing
import com.play.catchtinifing.present.model.TinifingModel

fun Tinifing.toModel(): TinifingModel = TinifingModel(this.id, this.name, this.description, this.imgSrc)
fun TinifingModel.toDomain() = Tinifing(this.id, this.name, this.description, this.imgSrc);
