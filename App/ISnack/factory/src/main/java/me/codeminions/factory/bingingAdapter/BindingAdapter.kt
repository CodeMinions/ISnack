package me.codeminions.factory.bingingAdapter

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:src")
fun setImgResId(view: ImageView, resId: Int) {
    view.setImageResource(resId)
}

@BindingAdapter("android:src")
fun setImgResId(view: ImageView, bitmap: Bitmap) {
    view.setImageBitmap(bitmap)
}
