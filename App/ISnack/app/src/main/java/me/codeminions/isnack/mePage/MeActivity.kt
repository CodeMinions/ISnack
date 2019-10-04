package me.codeminions.isnack.mePage

import android.content.Context
import android.content.Intent
import android.view.View
import kotlinx.android.synthetic.main.activity_homepage.*
import me.codeminions.common.app.DataBindingActivity
import me.codeminions.factory.utils.getLoginStatus
import me.codeminions.common.widget.BaseViewPagerAdapter
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.utils.getLoginInfo
import me.codeminions.isnack.R
import me.codeminions.isnack.mePage.accountPage.AccountLoginFragment
import me.codeminions.isnack.mePage.accountPage.AccountRegisterFragment
import me.codeminions.isnack.databinding.ActivityHomepageBinding
import me.codeminions.isnack.mePage.accountPage.AccountTrigger

class MeActivity : DataBindingActivity<ActivityHomepageBinding>(),
        AccountTrigger {

    companion object {
        fun startAction(context: Context) {
            val intent = Intent(context, MeActivity::class.java)
            context.startActivity(intent)
        }
    }

    lateinit var accountLoginFragment: AccountLoginFragment
    lateinit var accountRegisterFragment: AccountRegisterFragment

    override fun getLayoutResId(): Int {
        return R.layout.activity_homepage
    }

    override fun initWidget() {
        super.initWidget()

        if (!getLoginStatus(this)) {
            initAccount()
        } else {
            initMe()
        }
    }

    override fun onTrigger() {
        initWidget()
    }
    private fun initAccount() {
        frag_account.visibility = View.VISIBLE

        accountLoginFragment = AccountLoginFragment()
        accountRegisterFragment = AccountRegisterFragment()
        vp_account.adapter = object : BaseViewPagerAdapter(null, supportFragmentManager,
                accountLoginFragment, accountRegisterFragment) {
        }
    }
    private fun initMe() {
        frag_account.visibility = View.INVISIBLE

//        val user = User("半截拖鞋", "", "286")
        val user = getLoginInfo(this)
        binding.user = user
    }
}