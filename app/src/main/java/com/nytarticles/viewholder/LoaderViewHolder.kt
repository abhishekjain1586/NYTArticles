package com.nytarticles.viewholder

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.nytarticles.R

class LoaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val progress = itemView.findViewById<ProgressBar>(R.id.progress)
}