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

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("data")
fun <T> RecyclerView.setAdapterData(data: List<T?>?) {
    if (adapter is DataBindingAdapter<*, *>) {
        (adapter as DataBindingAdapter<T, ViewDataBinding>).updateDataAndNotify(data)
    }
}