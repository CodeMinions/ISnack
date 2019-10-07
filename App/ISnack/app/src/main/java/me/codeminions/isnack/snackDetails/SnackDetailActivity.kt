package me.codeminions.isnack.snackDetails

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_snack_details.*
import me.codeminions.common.widget.BaseAdapter
import me.codeminions.common.widget.BindingRecyclerAdapter
import me.codeminions.factory.PresenterActivity
import me.codeminions.factory.data.bean.Comment
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.ResponseModel
import me.codeminions.factory.data.model.SnackInfoModel
import me.codeminions.factory.net.RetrofitService
import me.codeminions.factory.net.URL_PIC
import me.codeminions.factory.presenter.snackDetail.SnackDetailContract
import me.codeminions.factory.presenter.snackDetail.SnackDetailPresenter
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.ActivitySnackDetailsBinding
import me.codeminions.isnack.databinding.ItemSnackCommentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SnackDetailActivity : PresenterActivity<ActivitySnackDetailsBinding>(),
        SnackDetailContract.SnackDetailView {
    override fun initPresenter(): SnackDetailContract.SnackDetailPresenter {
        return SnackDetailPresenter(this)
    }

    private lateinit var presenter: SnackDetailContract.SnackDetailPresenter
    private var commentList: ArrayList<Comment>? = null
    private lateinit var commentAdapter: BindingRecyclerAdapter<Comment, ItemSnackCommentBinding>

    companion object {
        fun startAction(context: Context, snack: Snack) {
            val intent = Intent(context, SnackDetailActivity::class.java)
            intent.putExtra("SnackId", snack)
            context.startActivity(intent)
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_snack_details
    }


    override fun initData() {
        binding.handler = this
        val snack = intent.extras?.getSerializable("SnackId") as Snack
        Log.i("snackInfo", snack.toString())

        binding.snack = snack
        binding.imgUrl = URL_PIC + snack.img

        presenter.getSnackMore(snackID = snack.snackID!!)
        presenter.getComment(snackId = snack.snackID!!)

        recycler_comment.layoutManager = LinearLayoutManager(this)
        commentAdapter = object : BindingRecyclerAdapter<Comment, ItemSnackCommentBinding>() {
            override fun getItemViewType(position: Int): Int {
                return R.layout.item_snack_comment
            }
            override fun onBindViewHolder(bing: ItemSnackCommentBinding, data: Comment) {
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
        recycler_comment.adapter = commentAdapter
    }

    override fun lookComment() {

    }

    override fun loadCommentSuccess(list: List<Comment>) {
        if(commentList != null) {
            commentList!!.addAll(list)
        } else {
            commentList = list as ArrayList<Comment>
        }
        commentAdapter.list = commentList as ArrayList<Comment>
        commentAdapter.notifyDataSetChanged()
    }

    override fun loadMoreSuccess(list: List<SnackInfoModel>) {
        initInfoTable(list)
        showTip("信息表初始化")
    }

    override fun initInfoTable(list: List<SnackInfoModel>) {
        val f = FrameLayout(this)
        val t1 = TextView(this)
        val t2 = TextView(this)
        val t3 = TextView(this)

        t1.text = "营养成分"
        t2.text = "每 ${list[0].quality.split("/")[1]}"
        t3.text = "人均每天"

        f.addView(t1)
        f.addView(t2)
        f.addView(t3)

        t1.gravity = Gravity.START
        t2.gravity = Gravity.CENTER
        t3.gravity = Gravity.END
        list_layout.addView(f)

        val l = f.layoutParams as LinearLayout.LayoutParams
        l.topMargin = 10
        l.bottomMargin = 10
        f.layoutParams = l

        val hiew = View(this)
        hiew.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, 3)
        hiew.setBackgroundColor(Color.parseColor("#808080"))

        list_layout.addView(hiew)

        for(it in list) {
            val frameLayout = FrameLayout(this)
            val text1 = TextView(this)
            val text2 = TextView(this)
            val text3 = TextView(this)

            text1.text = it.ingredient
            text2.text = it.quality.split("/")[0]
            text3.text = it.day

            frameLayout.addView(text1)
            frameLayout.addView(text2)
            frameLayout.addView(text3)

            text1.gravity = Gravity.START
            text2.gravity = Gravity.CENTER
            text3.gravity = Gravity.END

            text1.setTextColor(Color.parseColor("#ffffff"))
            text2.setTextColor(Color.parseColor("#ffffff"))
            text3.setTextColor(Color.parseColor("#ffffff"))

            list_layout.addView(frameLayout)

            val lp = frameLayout.layoutParams as LinearLayout.LayoutParams
            lp.topMargin = 10
            lp.bottomMargin = 10
            frameLayout.layoutParams = lp

            val view = View(this)
            view.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, 3)
            view.setBackgroundColor(Color.parseColor("#808080"))

            list_layout.addView(view)
        }
    }

    override fun setPresenter(presenter: SnackDetailContract.SnackDetailPresenter?) {
        this.presenter = presenter!!
    }

    override fun showTip(info: String?) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show()
    }
}