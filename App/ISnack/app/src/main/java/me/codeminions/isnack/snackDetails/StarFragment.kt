package me.codeminions.isnack.snackDetails

import android.view.View
import me.codeminions.common.app.DataBindingFragment
import me.codeminions.factory.data.bean.Snack
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.FragmentStarBinding

class StarFragment : DataBindingFragment<FragmentStarBinding>() {
    override fun getContentLayoutId(): Int = R.layout.fragment_star

    override fun initWidget(root: View) {
//        val user = dataBinding.snack.arguments["snack"] as Snack


    }

}