package com.jetpack.submission.movietvshow.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.model.TvShowModel

@Database(entities = [MovieModel::class, TvShowModel::class,], version = 2)
abstract class EntityDatabase : RoomDatabase(){

    abstract fun entityDao() : EntityDao

    companion object{
        @Volatile
        private var INSTANCE : EntityDatabase? = null

        fun getInstance(context: Context) : EntityDatabase {
            INSTANCE ?: synchronized(this){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    EntityDatabase::class.java,
                    "db_movie"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as EntityDatabase
        }
    }
}