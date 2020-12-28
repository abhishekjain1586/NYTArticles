package com.nytarticles.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "articles")
data class ArticlesEntity(@SerializedName("title") var title: String?) {

    @ColumnInfo(name = "id")
    @PrimaryKey
    @SerializedName("id")
    var id: Long?= null

    @ColumnInfo(name = "byline")
    @SerializedName("byline")
    var byline: String?= null

    @ColumnInfo(name = "published_date")
    @SerializedName("published_date")
    var published_date: String?= null

    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url: String?= null
}