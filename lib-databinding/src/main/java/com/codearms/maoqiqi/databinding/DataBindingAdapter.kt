/*
 * Copyright [2021] [March]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
 * @link https://github.com/maoqiqi/AndroidLibraries
 * @e-mail fengqi.mao.march@gmail.com
 * @author March
 * @date 2021-03-23 21:01
 * @version v1.0.0
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
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = data?.size ?: 0

    protected open fun bind(holder: DataBindingViewHolder<DB>, position: Int, binding: DB, item: T?) {
        bind(position, binding, item)
    }

    protected open fun bind(position: Int, binding: DB, item: T?) {

    }

    @JvmOverloads
    fun updateDataAndNotify(newData: List<T?>?, isRefresh: Boolean = true, isNotify: Boolean = true) {
        if (isRefresh) {
            this.data = newData
            if (isNotify) notifyItemRangeChanged(0, itemCount)
        } else {
            if (newData.isNullOrEmpty()) return
            val tmp = arrayListOf<T?>()
            data?.let { tmp.addAll(it) }
            tmp.addAll(newData)
            data = tmp
            if (isNotify) notifyItemRangeChanged(0, itemCount)
        }
    }
}