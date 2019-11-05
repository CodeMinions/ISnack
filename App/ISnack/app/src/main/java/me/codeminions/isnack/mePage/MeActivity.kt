package me.codeminions.isnack.mePage

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_homepage.*
import me.codeminions.common.mvp.BaseContract
import me.codeminions.common.widget.BaseViewPagerAdapter
import me.codeminions.common.widget.BindingRecyclerAdapter
import me.codeminions.factory.PresenterActivity
import me.codeminions.factory.data.bean.Comment
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.baseModel.SnackListModel
import me.codeminions.factory.net.URL_PIC
import me.codeminions.factory.presenter.mePage.MePageContract
import me.codeminions.factory.presenter.mePage.MePagePresenter
import me.codeminions.factory.utils.getLocalJson
import me.codeminions.factory.utils.getLoginStatus
import me.codeminions.factory.utils.setLoginOut
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.ActivityHomepageBinding
import me.codeminions.isnack.databinding.ItemMyPostBinding
import me.codeminions.isnack.databinding.ItemShowSnackListItBinding
import me.codeminions.isnack.databinding.ItemSnackListShowBinding
import me.codeminions.isnack.mePage.accountPage.AccountLoginFragment
import me.codeminions.isnack.mePage.accountPage.AccountRegisterFragment
import me.codeminions.isnack.mePage.accountPage.AccountTrigger

class MeActivity : PresenterActivity<ActivityHomepageBinding>(),
        MePageContract.MePageView,
        AccountTrigger {

    private lateinit var presenter: MePageContract.MePagePresenter

    override fun setPresenter(presenter: MePageContract.MePagePresenter?) {
        this.presenter = presenter!!
    }

    override fun showTip(info: String?) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show()
    }

    override fun initPresenter(): BaseContract.BasePresenter = MePagePresenter(this)

    companion object {
        fun startAction(context: Context) {
            val intent = Intent(context, MeActivity::class.java)
            context.startActivity(intent)
        }
    }

    lateinit var accountLoginFragment: AccountLoginFragment
    lateinit var accountRegisterFragment: AccountRegisterFragment

    private lateinit var commentAdapter: BindingRecyclerAdapter<Comment, ItemMyPostBinding>
    private lateinit var snackListAdapter: BindingRecyclerAdapter<SnackListModel, ItemSnackListShowBinding>

    private lateinit var currentUser: User
    private var commentList: ArrayList<Comment>? = null
    private var snackList: ArrayList<SnackListModel>? = null

    override fun getLayoutResId(): Int = R.layout.activity_homepage

    override fun initWidget() {
        super.initWidget()
        binding.handler = this
    }

    override fun initData() {

        if (!getLoginStatus(this)) {
            initAccount()
        } else {
            initMe()
            // 初始化个人信息页
            initRecycler()
            getMyComment()
        }
    }

    private fun getMyComment() {

        presenter.getCommentByUser(currentUser.userID!!)
        presenter.getSnackListByUser(currentUser.userID!!)
    }

    private fun initRecycler() {
        binding.userRecyclerSnackList.layoutManager = LinearLayoutManager(this)
        snackListAdapter = object : BindingRecyclerAdapter<SnackListModel, ItemSnackListShowBinding>() {
            override fun getItemViewType(position: Int): Int = R.layout.item_snack_list_show

            override fun onBindViewHolder(bing: ItemSnackListShowBinding, data: SnackListModel) {
                bing.model = data
                bing.imgUrl = URL_PIC + data.user.portrait

                bing.isShow = false
                bing.btnItemSnackList.setOnClickListener {
                    val r = if (!bing.isShow) 90f else 0f
                    it.animate()
                            .rotation(r)
                            .setDuration(100)
                            .start()

                    bing.isShow = !bing.isShow
                }

                bing.listShowSnackIt.layoutManager = LinearLayoutManager(bing.root.context)
                val snackItListAdapter = object : BindingRecyclerAdapter<Snack, ItemShowSnackListItBinding>() {
                    override fun getItemViewType(position: Int): Int = R.layout.item_show_snack_list_it

                    override fun onBindViewHolder(bing: ItemShowSnackListItBinding, data: Snack) {
                        bing.imgUrl = URL_PIC + data.img
                        bing.snack = data
                    }
                }
                bing.listShowSnackIt.adapter = snackItListAdapter
                snackItListAdapter.list = data.list
                snackItListAdapter.notifyDataSetChanged()
            }
        }
        binding.userRecyclerSnackList.adapter = snackListAdapter

        user_recycler_comment.layoutManager = LinearLayoutManager(this)


        commentAdapter = object : BindingRecyclerAdapter<Comment, ItemMyPostBinding>() {
            override fun getItemViewType(position: Int): Int = R.layout.item_my_post

            override fun onBindViewHolder(bing: ItemMyPostBinding, data: Comment) {
                bing.comment = data
                bing.user = currentUser
                bing.imgUrl = URL_PIC + currentUser.portrait
            }
        }
        user_recycler_comment.adapter = commentAdapter
    }

    override fun refreshList(list: List<Comment>) {
        if (commentList != null)
            commentList!!.addAll(list)
        else
            commentList = list as ArrayList<Comment>
        commentAdapter.list = list
        commentAdapter.notifyDataSetChanged()
    }

    override fun refreshOtherList(list: List<SnackListModel>) {
        if (snackList != null)
            snackList!!.addAll(list)
        else
            snackList = list as ArrayList<SnackListModel>
        snackListAdapter.list = list
        snackListAdapter.notifyDataSetChanged()
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
        vp_account.adapter = object : BaseViewPagerAdapter(null,
                supportFragmentManager,
                accountLoginFragment,
                accountRegisterFragment) {
        }
    }

    private fun initMe() {
        frag_account.visibility = View.INVISIBLE

        val user = getLocalJson(this)
        binding.imgResUrl = URL_PIC + user?.portrait
        binding.user = user
        currentUser = user!!
    }

    fun onClickBack(v: View) {
        finish()
    }

    fun onClickLoginOut(v: View) {
        setLoginOut(this)
        finish()
    }
}