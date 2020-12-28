package com.nytarticles.repository

import com.nytarticles.R
import com.nytarticles.db.DBHelper
import com.nytarticles.db.entities.ArticlesEntity
import com.nytarticles.model.response.ResArticles
import com.nytarticles.service.MyApp
import com.nytarticles.service.NetworkUtil
import com.nytarticles.service.ServiceHelper
import com.nytarticles.service.ServiceUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesRepository {

    companion object {
        const val periodInDays = 7
    }

    private var mListener: OnFetchArticlesListener? = null
    interface OnFetchArticlesListener {
        fun onFetchArticlesSuccess(lst : ArrayList<ArticlesEntity>)
        fun onFetchArticlesFailure(errorMsg : String)
    }

    fun setListener(listener: OnFetchArticlesListener?) {
        mListener = listener
    }

    fun getArticles() {
        if (NetworkUtil.isNetworkConnected()) {
            val queryParams = HashMap<String, String>()
            queryParams.put(ServiceUtils.API_KEY, ServiceUtils.API_KEY_VALUE)
            val callback = ServiceHelper.getClient().getArticles(periodInDays, queryParams)
            callback.enqueue(object : Callback<ResArticles> {
                override fun onResponse(
                    call: Call<ResArticles>,
                    response: Response<ResArticles>
                ) {
                    if (response.isSuccessful) {
                        val resArticles = response.body()
                        resArticles?.let {
                            val tempLst = it.results as ArrayList<ArticlesEntity>
                            if (!tempLst.isNullOrEmpty()) {
                                saveResultToDB(tempLst)
                                updateResult("")
                            }
                        } ?: run {
                            updateResult(MyApp._INSTANCE.resources.getString(R.string.text_no_record_found))
                        }
                    } else {
                        updateResult(MyApp._INSTANCE.resources.getString(R.string.error_server))
                    }
                }

                override fun onFailure(call: Call<ResArticles>, t: Throwable) {
                    updateResult(MyApp._INSTANCE.resources.getString(R.string.error_oops_something_not_right))
                }
            })
        } else {
            updateResult(MyApp._INSTANCE.resources.getString(R.string.error_network_connection))
        }
    }

    private fun saveResultToDB(tempLst: ArrayList<ArticlesEntity>) {
        DBHelper.getInstance().getArticlesDao().insert(tempLst)
    }

    private fun updateResult(errorMsg: String) {
        val articlesLst = getSavedArticles()
        if (!articlesLst.isNullOrEmpty()) {
            mListener?.onFetchArticlesSuccess(articlesLst)
        } else {
            mListener?.onFetchArticlesFailure(errorMsg)
        }
    }

    fun getSavedArticles(): ArrayList<ArticlesEntity> {
        return DBHelper.getInstance().getArticlesDao().getAllArticles() as ArrayList<ArticlesEntity>
    }

}