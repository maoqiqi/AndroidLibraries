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

package com.codearms.maoqiqi.android

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View

/**
 * Utils about activity
 * link: https://github.com/maoqiqi/AndroidLibraries
 * author: March
 * date: 2021-02-02 21:01
 * version v1.0.0
 */
object ActivityUtils {

    /**
     * Return the activity by context.
     * @return the activity by context
     */
    @JvmStatic
    fun Context?.getActivity(): Activity? {
        var context = this
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

    /**
     * Return the activity by view.
     * @return the activity by view
     */
    @JvmStatic
    fun View?.getActivity(): Activity? = this?.context?.getActivity()
}