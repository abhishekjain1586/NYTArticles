package com.nytarticles.viewholder

interface OnItemClickListener<T> {

    fun onItemClick(position: Int, obj: T, actionType: Int)
}