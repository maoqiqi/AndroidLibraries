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
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyboardUtils {

    /**
     * Return whether soft input is visible
     * @return true: yes<br> false: no
     */
    @JvmStatic
    fun Activity.isSoftInputVisible(): Boolean {
        val decorView: View = this.window.decorView
        // 获取View可见区域的bottom
        val outRect = Rect()
        decorView.getWindowVisibleDisplayFrame(outRect)
        return decorView.height > outRect.bottom + this.getNavBarHeight()
    }

    /**
     * Hide the soft input
     */
    @JvmStatic
    fun Activity.hideSoftInput() {
        val view: View = this.currentFocus ?: View(this)
        view.hideSoftInput()
    }

    /**
     * Show the soft input.
     */
    @JvmStatic
    fun View.showSoftInput() {
        val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(this, 0)
    }

    /**
     * Hide the soft input
     */
    @JvmStatic
    fun View.hideSoftInput() {
        val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(this.windowToken, 0)
    }
}