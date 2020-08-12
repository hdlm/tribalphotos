package com.tribal.tribalphotos.model

data class Photo (
    val id: String? = null,
    val description: String? = null,
    val alt_description: String? = null,
    val urls:Urls? = null,
    val likes: Int = 0,
    val user: Username? = null

)