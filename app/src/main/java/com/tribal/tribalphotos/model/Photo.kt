package com.tribal.tribalphotos.model

data class Photo (
    var likes: Int = 0,
    val author: String? = null,
    val urlPhoto: String? = null,
    val urlProfle: String? = null,
    val descripImg: String? = null,
    val descripProfile: String? = null

)