package me.codeminions.isnack.mePage

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.StringSignature
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_homepage.*
import me.codeminions.common.app.DataBindingActivity
import me.codeminions.factory.utils.getLoginStatus
import me.codeminions.common.widget.BaseViewPagerAdapter
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.net.URL_PIC
import me.codeminions.factory.utils.getLocalJson
import me.codeminions.factory.utils.getLoginInfo
import me.codeminions.factory.utils.setLoginOut
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
        binding.handler = this
    }

    override fun initData() {

        if (!getLoginStatus(this)) {
            initAccount()
        } else {
            initMe()
        }
    }

    /**
     * TODO: 19-10-6 注册后的回调可能还有点问题
     */
    override fun onTrigger() {
        initData()
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

        val user = getLocalJson(this)
        binding.user = user

        binding.imgResId = URL_PIC + user?.portrait
    }

    fun onClickBack(v: View) {
        finish()
    }

    fun onClickLoginOut(v: View) {
        setLoginOut(this)
        finish()
    }
}