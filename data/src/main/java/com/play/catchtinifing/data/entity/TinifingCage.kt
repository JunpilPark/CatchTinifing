package com.play.catchtinifing.data.entity

import androidx.room.*

@Entity(tableName = "tinifing_cage")
data class TinifingCage(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cage_id") val cageId: Int? = null,
    @Embedded val tinifing: Tinifing,
    @ColumnInfo(name = "catch_date_time") val catchDateTime: String
)