package com.gajanan.compose_paging3.domain.modals

data class Beer(
    val id: Int,
    val description: String,
    val first_brewed: String,
    val image_url: String?,
    val name: String,
    val tagline: String,
)
