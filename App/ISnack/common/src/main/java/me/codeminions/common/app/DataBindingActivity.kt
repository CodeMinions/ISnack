package me.codeminions.common.app

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

abstract class DataBindingAdapter : AppCompatActivity() {

    abstract fun getLayoutResId() : Int

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        DataBindingUtil.setContentView()
    }

}