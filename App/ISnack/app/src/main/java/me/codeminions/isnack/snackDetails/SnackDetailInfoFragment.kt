package me.codeminions.isnack.snackDetails

import me.codeminions.common.app.DataBindingFragment
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.netData.Constant
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.FragmentSnackInfoBinding

class SnackDetailInfoFragment : DataBindingFragment<FragmentSnackInfoBinding>() {

    private var snack: Snack? = null

    override fun getContentLayoutId(): Int {
        return R.layout.fragment_snack_info
    }

    override fun initData() {
        snack = arguments?.get("data") as Snack

        dataBinding.snack = snack
        dataBinding.imgResId = Constant.imgs[snack!!.tag]
    }
}