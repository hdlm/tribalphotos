package com.tribal.tribalphotos.model.unsplash

data class Photo (
    val id: String? = null,
    val description: String? = null,
    val alt_description: String? = null,
    val urls: Urls? = null,
    val likes: Int = 0,
    val user: Username? = null,
)

data class Urls (
    val thumb: String? = null,
    val full: String? = null,
    val regular: String? = null
)

data class Username (
    val id: String? = null,
    val name: String? = null,
    val bio: String? = null,
    val portfolio_url: String? = null,
    val profile_image: ProfileImage? = null,
    val total_photos: Int? = 0,
    val total_likes: Int? = 0
)


data class ProfileImage (
    val small: String? = null,
    val medium: String? = null,
    val large: String? = null
)