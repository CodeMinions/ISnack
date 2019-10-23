package me.codeminions.factory.bingingAdapter

import android.graphics.Bitmap
import android.util.Log
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

@BindingAdapter("imageFromUrl")
fun setImgResUrl(view: ImageView, url: String?) {
    if(!url.isNullOrEmpty()){

        Log.i("ImageUrl", url)
        Glide.with(view.context)
                .load(url)
                .placeholder(R.drawable.bg_oval)
                .error(R.drawable.bg_oval)
                .into(view)
    }
}