@file:JvmName("ImageLoader")

package com.codearms.maoqiqi.imageloader

import android.app.Activity
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder

@JvmOverloads
fun Activity.displayImage(imageView: ImageView, path: String?, placeholder: Int = 0, error: Int = 0) {
    if (path.isNullOrEmpty()) return
    val builder: RequestBuilder<Drawable> = Glide.with(this).load(path)
    if (placeholder != 0) builder.placeholder(placeholder)
    if (error != 0) builder.error(error)
    builder.into(imageView)
}

@JvmOverloads
fun Fragment.displayImage(imageView: ImageView, path: String?, placeholder: Int = 0, error: Int = 0) {
    if (path.isNullOrEmpty()) return
    val builder: RequestBuilder<Drawable> = Glide.with(this).load(path)
    if (placeholder != 0) builder.placeholder(placeholder)
    if (error != 0) builder.error(error)
    builder.into(imageView)
}