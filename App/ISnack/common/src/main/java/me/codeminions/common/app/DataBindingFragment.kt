package me.codeminions.common.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import butterknife.ButterKnife

abstract class DataBindingFragment<T: ViewDataBinding> : androidx.fragment.app.Fragment() {

    private var mRoot: View? = null
    private lateinit var dataBinding: T

    @LayoutRes
    protected abstract fun getContentLayoutId(): Int


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        dataBinding = DataBindingUtil.inflate(inflater, getContentLayoutId(), container, false)

        this.mRoot = dataBinding.root
        initWidget(dataBinding.root)

        return mRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initData()
    }

    open fun initWidget(root: View) {
        ButterKnife.bind(this, root)
    }

    open fun initData() {

    }

    fun getRoot(): View? {
        return mRoot
    }

    fun getDataBing(): T {
        return dataBinding
    }
}
