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

package com.codearms.maoqiqi.databinding.adapter

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.codearms.maoqiqi.databinding.DataBindingAdapter
import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException

/**
 * BindingAdapters created for easier implementation for RecyclerView DataBinding
 * @link https://github.com/maoqiqi/AndroidLibraries
 * @e-mail fengqi.mao.march@gmail.com
 * @author March
 * @date 2021-03-23 21:01
 * @version v1.0.0
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter(value = ["data", "isRefresh"], requireAll = false)
fun <T> RecyclerView.updateData(data: List<T?>?, isRefresh: Boolean) {
    if (adapter is DataBindingAdapter<*, *>) {
        (adapter as DataBindingAdapter<T, ViewDataBinding>).updateDataAndNotify(data, isRefresh)
    }
}

@BindingAdapter("itemDecorations")
fun RecyclerView.addItemDecorations(oldValue: String?, newValue: String?) {
    if (oldValue == newValue || newValue.isNullOrEmpty() || !newValue.contains(".")) return
    newValue.split(",").filter { item -> item.isNotEmpty() }.forEach(this::createItemDecoration)
}

private fun RecyclerView.createItemDecoration(className: String) {
    try {
        val classLoader: ClassLoader = context.classLoader
        val itemDecorationClass = Class.forName(className, false, classLoader).asSubclass(RecyclerView.ItemDecoration::class.java)
        val constructor: Constructor<out RecyclerView.ItemDecoration?> = itemDecorationClass.getConstructor()
        constructor.isAccessible = true
        constructor.newInstance()?.let { addItemDecoration(it) }
    } catch (e: ClassNotFoundException) {
        throw IllegalStateException("Unable to find ItemDecoration $className", e)
    } catch (e: InvocationTargetException) {
        throw IllegalStateException("Could not instantiate the ItemDecoration: $className", e)
    } catch (e: InstantiationException) {
        throw IllegalStateException("Could not instantiate the ItemDecoration: $className", e)
    } catch (e: IllegalAccessException) {
        throw IllegalStateException("Cannot access non-public constructor $className", e)
    } catch (e: ClassCastException) {
        throw IllegalStateException("Class is not a ItemDecoration $className", e)
    } catch (e: NoSuchMethodException) {
        throw IllegalStateException("Error creating ItemDecoration $className", e)
    }
}