package com.codearms.maoqiqi.databinding

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView data binding adapter
 * author: March
 * date: 2021-03-23 21:01
 * version v1.0.0
 */
abstract class DataBindingAdapter<T, DB : ViewDataBinding>(
    val context: Context,
    private val lifecycleOwner: LifecycleOwner,
    private val redId: Int,
    var data: List<T?>?
) : RecyclerView.Adapter<DataBindingViewHolder<DB>>() {

    @JvmOverloads
    constructor(activity: AppCompatActivity, resId: Int, data: List<T?>? = null) : this(activity.baseContext, activity, resId, data)

    @JvmOverloads
    constructor(fragment: Fragment, resId: Int, data: List<T?>? = null) : this(fragment.requireContext(), fragment.viewLifecycleOwner, resId, data)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<DB> =
        DataBindingViewHolder(LayoutInflater.from(context).inflate(redId, parent, false), lifecycleOwner)

    override fun onBindViewHolder(holder: DataBindingViewHolder<DB>, position: Int) {
        data?.let { bind(holder, position, holder.binding, it[position]) }
        // holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = data?.size ?: 0

    abstract fun bind(holder: DataBindingViewHolder<DB>, position: Int, binding: DB, item: T?)
}