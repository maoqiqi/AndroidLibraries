package com.codearms.maoqiqi.utils

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.codearms.maoqiqi.encrypt.getNavBarHeight

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