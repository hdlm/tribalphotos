package com.tribal.tribalphotos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "favorite")
class Favorite {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    var id: Int = 0
    var username: String? = null
    var bio: String? = null
    @ColumnInfo(name ="link_profile")
    var linkProfile: String? = null
    @ColumnInfo(name = "profile_image")
    var profileImage: String? = null
    @ColumnInfo(name = "total_photos")
    var totalPhotos: Int = 0

}