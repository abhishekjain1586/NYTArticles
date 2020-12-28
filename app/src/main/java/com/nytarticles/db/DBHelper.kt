package com.nytarticles.db

import androidx.room.Room
import com.nytarticles.service.MyApp

object DBHelper {

    private var dbInstance_ : NYTDatabase? = null

    fun getInstance() : NYTDatabase {
        if (dbInstance_ == null) {
            dbInstance_ = Room.databaseBuilder(
                MyApp._INSTANCE.applicationContext,
                NYTDatabase::class.java,
                "nytarticles_db.db")
                .allowMainThreadQueries()
                .build()
        }
        return dbInstance_ as NYTDatabase
    }
}