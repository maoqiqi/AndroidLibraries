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

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction

object FragmentUtils {

    @JvmOverloads
    @JvmStatic
    fun FragmentActivity.addFragment(container: Int, tag: String? = null, savedInstanceState: Bundle? = null, block: () -> Fragment?) {
        supportFragmentManager.beginTransaction().show(container, tag, savedInstanceState, block)
    }

    @JvmOverloads
    @JvmStatic
    fun Fragment.addFragment(container: Int, tag: String? = null, savedInstanceState: Bundle? = null, block: () -> Fragment?) {
        childFragmentManager.beginTransaction().show(container, tag, savedInstanceState, block)
    }

    @JvmOverloads
    @JvmStatic
    fun FragmentTransaction.show(container: Int, tag: String? = null, savedInstanceState: Bundle? = null, block: () -> Fragment?) {
        if (savedInstanceState == null) {
            block()?.let { if (it.isAdded) show(it).commit() else add(container, it, tag).commit() }
        }
    }

    @JvmOverloads
    @JvmStatic
    fun FragmentActivity.switchFragment(container: Int, from: Fragment?, to: Fragment?, tag: String? = null): Fragment? {
        if (to != null && from !== to) { // from != to 才切换
            val ft = supportFragmentManager.beginTransaction()
            // from 不为 null,隐藏
            from?.let { ft.hide(it) }
            // 显示 to
            to.let { if (it.isAdded) ft.show(it).commit() else ft.add(container, it, tag).commit() }
            return to
        }
        return from
    }
}