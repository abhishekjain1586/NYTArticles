package com.nytarticles.viewmodel

import androidx.lifecycle.ViewModel
import com.nytarticles.db.entities.ArticlesEntity
import com.nytarticles.repository.ArticlesRepository

class ArticlesActivityViewModel : ViewModel(), ArticlesRepository.OnFetchArticlesListener {

    private val errorMsg = SingleLiveEvent<String>()
    private val lvArticlesLst = SingleLiveEvent<ArrayList<ArticlesEntity>>()
    private val articlesRepository = ArticlesRepository()
    private var selectedArticle: ArticlesEntity? = null
    private var articlesLst = ArrayList<ArticlesEntity>()

    fun showError() : SingleLiveEvent<String> {
        return errorMsg
    }

    fun getArticles(): SingleLiveEvent<ArrayList<ArticlesEntity>> {
        return lvArticlesLst
    }

    fun loadArticles() {
        if (articlesLst.isNotEmpty()) {
            lvArticlesLst.value = articlesLst
            return
        }
        articlesRepository.setListener(this)
        articlesRepository.getArticles()
    }

    fun setSelectedArticle(obj: ArticlesEntity) {
        selectedArticle = obj
    }

    fun getSelectedArticle(): ArticlesEntity? {
        return selectedArticle
    }

    override fun onFetchArticlesSuccess(lst: ArrayList<ArticlesEntity>) {
        articlesLst.clear()
        articlesLst.addAll(lst)
        lvArticlesLst.value = lst
    }

    override fun onFetchArticlesFailure(errorMsg: String) {
        this.errorMsg.value = errorMsg
    }
}