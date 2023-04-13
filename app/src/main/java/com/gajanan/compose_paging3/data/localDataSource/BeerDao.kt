package com.gajanan.compose_paging3.data.localDataSource

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gajanan.compose_paging3.data.localDataSource.modals.BeerEntity

@Dao
interface BeerDao {
    @Insert
    suspend fun upsertAll(beerEntity: List<BeerEntity>)

    @Query("SELECT * FROM beer_table")
    fun pagingSource() : PagingSource<Int,BeerEntity>

    @Query("DELETE FROM beer_table")
    suspend fun clearAll()
}