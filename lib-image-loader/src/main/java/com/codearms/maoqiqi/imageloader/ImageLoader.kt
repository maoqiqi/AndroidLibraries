package com.codearms.maoqiqi.imageloader

import android.app.Activity
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

object ImageLoader {

    @JvmOverloads
    @JvmStatic
    fun Activity.displayImage(imageView: ImageView, path: String?, placeholder: Int = 0) {
        if (path.isNullOrEmpty()) return
        if (placeholder == 0)
            Glide.with(this).load(path).into(imageView)
        else
            Glide.with(this).load(path).placeholder(placeholder).into(imageView)
    }

    @JvmOverloads
    @JvmStatic
    fun Fragment.displayImage(imageView: ImageView, path: String?, placeholder: Int = 0) {
        if (path.isNullOrEmpty()) return
        if (placeholder == 0)
            Glide.with(this).load(path).into(imageView)
        else
            Glide.with(this).load(path).placeholder(placeholder).into(imageView)
    }
}