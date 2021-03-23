package com.codearms.maoqiqi.databinding

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView data binding view holder
 * author: March
 * date: 2021-03-23 21:01
 * version v1.0.0
 */
class DataBindingViewHolder<DB : ViewDataBinding>(itemView: View, owner: LifecycleOwner? = null) : RecyclerView.ViewHolder(itemView) {
    val binding: DB = DataBindingUtil.bind<DB>(itemView)?.apply { lifecycleOwner = owner } ?: throw IllegalStateException("DataBindingUtil.bind fail")
}