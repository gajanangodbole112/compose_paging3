package com.gajanan.compose_paging3.data.mappers

import com.gajanan.compose_paging3.data.localDataSource.modals.BeerEntity
import com.gajanan.compose_paging3.data.remote.dto.BeersDto
import com.gajanan.compose_paging3.domain.modals.Beer

fun BeersDto.toBeerEntity(): BeerEntity = BeerEntity(
    id = id!!,
    name = name!!,
    description = description!!,
    imageUrl = image_url,
    firsBrewed = first_brewed!!,
    tagline = tagline!!
)

fun BeerEntity.toBeer(): Beer = Beer(
    id = id,
    name = name,
    description = description,
    image_url = imageUrl,
    first_brewed = firsBrewed,
    tagline = tagline
)