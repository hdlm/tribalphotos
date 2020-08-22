package com.tribal.tribalphotos.repository

import android.content.Context
import com.tribal.tribalphotos.model.Favorite
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class FavoriteRepositoryImpl(context: Context) : FavoritesRepository, KoinComponent {

    private var favoriteDao : FavoriteDao? = null
    private var allFavorites : List<Favorite>? = null

    init {
        GlobalScope.launch {
            val db: FavoriteRoomDatabase? = FavoriteRoomDatabase.getDatabase(context)
            favoriteDao = db?.favoriteDao()
            allFavorites = favoriteDao?.getAllFavorites()
        }
    }

    override suspend fun getAllFavorites(): List<Favorite>? {
        return allFavorites
    }

    override suspend fun insert(favorite: Favorite) {
        favoriteDao?.let {
            insert(favorite)
        }
    }

    override suspend fun delete(favorite: Favorite) {
        favoriteDao.let {
            delete(favorite)
        }
    }

    override suspend fun getFavoritesByUsername(username: String): List<Favorite>? {
        return allFavorites?.let {
            it.filter { favorite -> favorite.username.equals(username, true) }
        } ?: run {
            null
        }
    }
}