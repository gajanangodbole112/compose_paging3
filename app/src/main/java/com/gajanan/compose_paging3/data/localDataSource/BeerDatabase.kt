package com.gajanan.compose_paging3.data.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gajanan.compose_paging3.data.localDataSource.modals.BeerEntity

@Database(entities = [BeerEntity::class], version = 1)
abstract class BeerDatabase  : RoomDatabase(){
    abstract fun beerDatabase() : BeerDao
}