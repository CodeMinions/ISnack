package me.codeminions.factory.bingingAdapter

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import me.codeminions.factory.R

@BindingAdapter("android:src")
fun setImgResId(view: ImageView, resId: Int) {
    view.setImageResource(resId)
}

@BindingAdapter("android:src")
fun setImgResId(view: ImageView, bitmap: Bitmap) {
    view.setImageBitmap(bitmap)
}

@BindingAdapter("android:src")
fun setImgResUrl(view: ImageView, url: String) {
    if(url.isNotEmpty()){
        Glide.with(view.context)
                .load(url)
                .error(R.drawable.bg_oval)
                .into(view)
    }
}