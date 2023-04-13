package com.gajanan.compose_paging3.data.remote

import com.gajanan.compose_paging3.data.remote.dto.BeersDto
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {

    @GET("beers")
    suspend fun getBeer(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): List<BeersDto>

}