package com.nytarticles.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nytarticles.R
import com.nytarticles.db.entities.ArticlesEntity
import com.nytarticles.viewholder.ArticlesViewHolder
import com.nytarticles.viewholder.LoaderViewHolder
import com.nytarticles.viewholder.OnItemClickListener

const val ITEM_VIEW_LOADER = 0;
const val ITEM_VIEW_ARTICLE = 1;

class ArticlesAdapter(private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val articlesLst = ArrayList<ArticlesEntity>()
    private var mListener: OnItemClickListener<ArticlesEntity>? = null

    fun setData(lst: ArrayList<ArticlesEntity>) {
        articlesLst.clear()
        articlesLst.addAll(lst)
    }

    fun resetLst() {
        articlesLst.clear()
    }

    fun setListener(listener: OnItemClickListener<ArticlesEntity>?) {
        mListener = listener
    }

    override fun getItemViewType(position: Int): Int {
        if (articlesLst.get(position).id == null) {
            return ITEM_VIEW_LOADER
        }
        return ITEM_VIEW_ARTICLE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_VIEW_ARTICLE) {
            ArticlesViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_view_article, parent, false))
        } else {
            LoaderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_view_loader, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(holder.adapterPosition) == ITEM_VIEW_ARTICLE) {
            val obj = articlesLst.get(holder.adapterPosition)
            obj.title?.let {
                (holder as ArticlesViewHolder).tvTitle.text = it
            }
            obj.byline?.let {
                (holder as ArticlesViewHolder).tvByline.text = it
            }
            obj.published_date?.let {
                (holder as ArticlesViewHolder).tvPublishedDate.text = it
            }

            mListener?.let {
                (holder as ArticlesViewHolder).setListener(it, obj)
            }
        } else if (getItemViewType(holder.adapterPosition) == ITEM_VIEW_LOADER) {

        }
    }

    override fun getItemCount(): Int {
        return articlesLst.size
    }
}