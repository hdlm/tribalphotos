package com.tribal.tribalphotos.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tribal.tribalphotos.model.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class FavoriteRoomDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteRoomDatabase? = null

        internal fun getDatabase(context: Context) : FavoriteRoomDatabase? {
            val tempInstance = INSTANCE
            if ( tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteRoomDatabase::class.java,
                    "tribalphotos_database"
                    ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}