package com.nytarticles.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nytarticles.db.dao.ArticlesDao
import com.nytarticles.db.entities.ArticlesEntity

@Database(entities = arrayOf(ArticlesEntity::class), version = 1, exportSchema = false)
abstract class NYTDatabase : RoomDatabase() {

    abstract fun getArticlesDao() : ArticlesDao
}