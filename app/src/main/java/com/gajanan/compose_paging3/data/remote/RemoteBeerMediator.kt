@file:OptIn(ExperimentalPagingApi::class)

package com.gajanan.compose_paging3.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.gajanan.compose_paging3.data.localDataSource.BeerDatabase
import com.gajanan.compose_paging3.data.localDataSource.modals.BeerEntity
import com.gajanan.compose_paging3.data.mappers.toBeerEntity
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import kotlin.time.seconds

class RemoteBeerMediator (
    private val beerDB : BeerDatabase,
    private val beerApi: BeerApi
        ) : RemoteMediator<Int, BeerEntity>(){
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {

        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) 1 else (lastItem.id/state.config.pageSize)+1
                }
            }
            delay(2000L)
            val beers = beerApi.getBeer(
                page = loadKey,
                pageSize = state.config.pageSize
            )
            beerDB.withTransaction {
                if (loadType == LoadType.REFRESH){
                    beerDB.beerDatabase().clearAll()
                }
                val beerEntities = beers.map { it.toBeerEntity() }
                beerDB.beerDatabase().upsertAll(beerEntities)
            }
            MediatorResult.Success(
                 endOfPaginationReached = beers.isEmpty()
            )
        }catch (e:IOException){
            MediatorResult.Error(e)
        }catch (e:HttpException){
            MediatorResult.Error(e)
        }
    }


}