package com.gajanan.compose_paging3.data.localDataSource.modals

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beer_table")
data class BeerEntity(
    @PrimaryKey
    val id: Int,
    val description: String,
    val firsBrewed: String,
    val imageUrl: String?,
    val name: String,
    val tagline: String,
)
