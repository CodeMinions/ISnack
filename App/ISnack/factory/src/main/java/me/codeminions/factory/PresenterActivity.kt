package me.codeminions.factory

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.databinding.ViewDataBinding
import me.codeminions.common.app.DataBindingActivity
import me.codeminions.common.mvp.BaseContract

abstract class PresenterActivity<ActivityDataBinding: ViewDataBinding> : DataBindingActivity<ActivityDataBinding>() {

    abstract fun initPresenter() : BaseContract.BasePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        initPresenter()
        super.onCreate(savedInstanceState)
    }
}