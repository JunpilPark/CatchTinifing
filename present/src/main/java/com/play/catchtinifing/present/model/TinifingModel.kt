package com.play.catchtinifing.present.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TinifingModel(val id: Int,
                         val name: String,
                         val description: String,
                         val imgSrc: String): Parcelable
