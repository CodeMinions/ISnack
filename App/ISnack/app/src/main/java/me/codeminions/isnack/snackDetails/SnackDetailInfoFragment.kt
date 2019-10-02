package me.codeminions.isnack

import me.codeminions.common.app.DataBindingFragment
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.netData.Constant
import me.codeminions.isnack.databinding.FragmentSnackInfoBinding

class SnackDetailInfoFragment : DataBindingFragment<FragmentSnackInfoBinding>() {

    private var snack: Snack? = null

    override fun getContentLayoutId(): Int {
        return R.layout.fragment_snack_info
    }

    override fun initData() {
        snack = arguments?.get("data") as Snack

        getDataBing().snack = snack

        getDataBing().imgResId = Constant.imgs[snack!!.tag]
    }
}