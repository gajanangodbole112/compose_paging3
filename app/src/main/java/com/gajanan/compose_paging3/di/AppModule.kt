package com.gajanan.compose_paging3.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.gajanan.compose_paging3.common.Constants
import com.gajanan.compose_paging3.data.localDataSource.BeerDatabase
import com.gajanan.compose_paging3.data.localDataSource.modals.BeerEntity
import com.gajanan.compose_paging3.data.remote.BeerApi
import com.gajanan.compose_paging3.data.remote.RemoteBeerMediator
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext context: Context): BeerDatabase {
        return Room.databaseBuilder(
            context,
            BeerDatabase::class.java,
            "beer.db",
            ).build()
    }

    @Provides
    @Singleton
    fun provideBeerApi() : BeerApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeerApi::class.java)
    }

    @Provides
    @Singleton
    fun providePager(beerDb : BeerDatabase, beerApi: BeerApi) : Pager<Int, BeerEntity>{
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = RemoteBeerMediator(
                beerDB = beerDb,
                beerApi = beerApi
            ),
            pagingSourceFactory = {
                beerDb.beerDatabase().pagingSource()
            }
        )
    }
}