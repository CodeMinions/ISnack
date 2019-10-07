package me.codeminions.isnack.mePage

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_homepage.*
import kotlinx.android.synthetic.main.activity_snack_details.*
import me.codeminions.common.app.DataBindingActivity
import me.codeminions.common.widget.BaseViewPagerAdapter
import me.codeminions.common.widget.BindingRecyclerAdapter
import me.codeminions.factory.data.bean.Comment
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.ResponseModel
import me.codeminions.factory.net.RetrofitService
import me.codeminions.factory.net.URL_PIC
import me.codeminions.factory.utils.getLocalJson
import me.codeminions.factory.utils.getLoginStatus
import me.codeminions.factory.utils.setLoginOut
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.ActivityHomepageBinding
import me.codeminions.isnack.databinding.ItemMyPostBinding
import me.codeminions.isnack.databinding.ItemSnackCommentBinding
import me.codeminions.isnack.mePage.accountPage.AccountLoginFragment
import me.codeminions.isnack.mePage.accountPage.AccountRegisterFragment
import me.codeminions.isnack.mePage.accountPage.AccountTrigger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    private lateinit var commentAdapter: BindingRecyclerAdapter<Comment, ItemMyPostBinding>

    private lateinit var currentUser: User
    private var commentList: ArrayList<Comment>? = null

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
            // 初始化个人信息页
            initRecycler()
            getMyComment()
        }
    }

    fun getMyComment() {
        RetrofitService.getApiService().getCommentByUser(currentUser.userID!!.toInt()).enqueue(object: Callback<ResponseModel<List<Comment>>> {
            override fun onResponse(call: Call<ResponseModel<List<Comment>>>, response: Response<ResponseModel<List<Comment>>>) {
                if(response.isSuccessful) {
                    val list = response.body()?.result as List<Comment>
                    refreshList(list)
                }
            }

            override fun onFailure(call: Call<ResponseModel<List<Comment>>>, t: Throwable) {

            }
        })
    }

    fun initRecycler() {
        user_recycler_comment.layoutManager = LinearLayoutManager(this)
        commentAdapter = object : BindingRecyclerAdapter<Comment, ItemMyPostBinding>() {
            override fun getItemViewType(position: Int): Int {
                return R.layout.item_my_post
            }
            override fun onBindViewHolder(bing: ItemMyPostBinding, data: Comment) {
                bing.comment = data
                // 获取账号对应信息
                RetrofitService.getApiService().getUserById(data.send_id).enqueue(object: Callback<ResponseModel<User>> {
                    override fun onResponse(call: Call<ResponseModel<User>>, response: Response<ResponseModel<User>>) {
                        if(response.isSuccessful) {
                            val user = response.body()?.result as User
                            bing.user = user
                            bing.imgUrl = URL_PIC + user.portrait
                        }
                    }
                    override fun onFailure(call: Call<ResponseModel<User>>, t: Throwable) {

                    }
                })
            }
        }
        user_recycler_comment.adapter = commentAdapter
    }

    fun refreshList(list: List<Comment>) {
        if(commentList != null)
            commentList!!.addAll(list)
        else
            commentList = list as ArrayList<Comment>
        commentAdapter.list = list
        commentAdapter.notifyDataSetChanged()
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
        currentUser = user!!

        binding.imgResUrl = URL_PIC + user.portrait

//        Glide.with(this)
//                .load(URL_PIC + user?.portrait)
//                .into(me_user_portrait)
    }

    fun onClickBack(v: View) {
        finish()
    }

    fun onClickLoginOut(v: View) {
        setLoginOut(this)
        finish()
    }
}