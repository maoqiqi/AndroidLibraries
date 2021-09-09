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

import androidx.activity.ComponentActivity
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Activity data binding lazy
 * @link https://github.com/maoqiqi/AndroidLibraries
 * @e-mail fengqi.mao.march@gmail.com
 * @author March
 * @date 2021-03-23 21:01
 * @version v1.0.0
 */
@MainThread
inline fun <reified DB : ViewDataBinding> ComponentActivity.binding(@LayoutRes resId: Int): Lazy<DB> = lazy {
    DataBindingUtil.setContentView<DB>(this, resId).apply { lifecycleOwner = this@binding }
}