package com.nytarticles.viewholder

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.nytarticles.R
import com.nytarticles.db.entities.ArticlesEntity

class ArticlesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var mListener: OnItemClickListener<ArticlesEntity>? = null
    private var articleObj: ArticlesEntity? = null

    val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
    val tvByline = itemView.findViewById<TextView>(R.id.tv_byline)
    val tvPublishedDate = itemView.findViewById<TextView>(R.id.tv_published_date)
    val layMain = itemView.findViewById<ConstraintLayout>(R.id.lay_main)

    fun setListener(listener: OnItemClickListener<ArticlesEntity>, obj: ArticlesEntity) {
        mListener = listener
        articleObj = obj
        layMain.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.lay_main -> {
                mListener?.onItemClick(adapterPosition, articleObj!!, 0)
            }
        }
    }
}