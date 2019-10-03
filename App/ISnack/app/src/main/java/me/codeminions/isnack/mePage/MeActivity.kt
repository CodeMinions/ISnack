package me.codeminions.isnack.mePage

import android.content.Context
import android.content.Intent
import me.codeminions.common.app.DataBindingActivity
import me.codeminions.factory.data.bean.User
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.ActivityHomepageBinding

class MeActivity : DataBindingActivity<ActivityHomepageBinding>() {

    companion object {
        fun startAction(context: Context) {
            val intent = Intent(context, MeActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_homepage
    }

    override fun initWidget() {
        super.initWidget()

        val user = User("半截拖鞋", R.drawable.default_portrait, 286)

        binding.user = user
        binding.imgResId = user.portrait
    }


}