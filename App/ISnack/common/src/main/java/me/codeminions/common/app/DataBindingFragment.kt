package me.codeminions.common.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager

import butterknife.ButterKnife

abstract class DataBindingFragment<FragmentBinding: ViewDataBinding> : androidx.fragment.app.Fragment() {

    protected var mRoot: View? = null
    protected lateinit var dataBinding: FragmentBinding
    lateinit var fm : FragmentManager

    @LayoutRes
    protected abstract fun getContentLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fm = (context as AppCompatActivity).supportFragmentManager
    }

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
}
