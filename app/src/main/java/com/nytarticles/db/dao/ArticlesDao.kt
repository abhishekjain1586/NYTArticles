package com.nytarticles.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.nytarticles.db.entities.ArticlesEntity

@Dao
abstract class ArticlesDao : BaseDao<ArticlesEntity>() {

    @Query("select * from articles")
    abstract fun getAllArticles() : List<ArticlesEntity>?

}