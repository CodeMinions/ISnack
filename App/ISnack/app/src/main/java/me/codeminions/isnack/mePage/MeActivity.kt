package me.codeminions.isnack.mePage

import me.codeminions.common.app.DataBindingFragment
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.FragmentHomepageBinding

class MeFragment : DataBindingFragment<FragmentHomepageBinding>() {

    override fun getContentLayoutId(): Int {
        return R.layout.activity_homepage
    }



}