package com.tribal.tribalphotos.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tribal.tribalphotos.model.Favorite
import org.jetbrains.annotations.NotNull

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert (favorite: Favorite)

    @Delete
    suspend fun delete(favorite: Favorite)

    @Query("SELECT * FROM favorite")
    fun getAllFavorites (): List<Favorite>

    @Query("SELECT * FROM favorite WHERE username = :username")
    fun getFavoritesByUsername(username: String) : List<Favorite>


}