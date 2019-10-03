package me.codeminions.factory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.codeminions.common.mvp.BaseContract
import androidx.databinding.ViewDataBinding
import me.codeminions.common.app.DataBindingFragment

abstract class PresenterFragment<FragmentBinding : ViewDataBinding> : DataBindingFragment<FragmentBinding>() {

    abstract fun initPresenter() : BaseContract.BasePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initPresenter()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}