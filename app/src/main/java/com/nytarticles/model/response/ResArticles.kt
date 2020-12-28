package com.nytarticles.model.response

import com.google.gson.annotations.SerializedName
import com.nytarticles.db.entities.ArticlesEntity

data class ResArticles(@SerializedName("results") val results: ArrayList<ArticlesEntity>?)

@SerializedName("status")
val status:String? = null

@SerializedName("num_results")
val num_results:Int? = null
