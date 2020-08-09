package com.tribal.tribalphotos.model

data class Photo (
    val author: String? = null,
    val name: String? = null,
    val photos: List<String>?
)