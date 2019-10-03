package me.codeminions.common.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import butterknife.ButterKnife

abstract class DataBindingActivity<T: ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: T

    @LayoutRes
    abstract fun getLayoutResId() : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayoutResId())

        initWidget()

        initData()
    }

    open fun initWidget() {
        ButterKnife.bind(this)
    }

    open fun initData() {}

}