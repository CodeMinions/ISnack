package me.codeminions.isnack.commentPager

import android.util.Log
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.android.synthetic.main.fragment_comment.view.*
import me.codeminions.common.app.DataBindingFragment
import me.codeminions.common.widget.BindingRecyclerAdapter
import me.codeminions.factory.data.bean.Comment
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.ResponseModel
import me.codeminions.factory.net.RetrofitService
import me.codeminions.factory.net.URL_PIC
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.FragmentCommentBinding
import me.codeminions.isnack.databinding.ItemMyPostBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CommentFragment :
        DataBindingFragment<FragmentCommentBinding>() {

    private var commentList: ArrayList<Comment>? = null

    private lateinit var adapter: BindingRecyclerAdapter<Comment, ItemMyPostBinding>

    override fun initWidget(root: View) {
        root.frag_recycler_comment.layoutManager = LinearLayoutManager(context)
        adapter = object : BindingRecyclerAdapter<Comment, ItemMyPostBinding>() {

            override fun getItemViewType(position: Int) = R.layout.item_my_post

            override fun onBindViewHolder(bing: ItemMyPostBinding, data: Comment) {
                bing.comment = data
                RetrofitService.getApiService().getUserById(data.send_id).enqueue(object : Callback<ResponseModel<User>> {
                    override fun onResponse(call: Call<ResponseModel<User>>, response: Response<ResponseModel<User>>) {
                        if (response.isSuccessful) {
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
        root.frag_recycler_comment.adapter = adapter
    }

    override fun getContentLayoutId(): Int = R.layout.fragment_comment

    override fun initData() {
        RetrofitService.getApiService().getAllComment().enqueue(object : Callback<ResponseModel<List<Comment>>> {
            override fun onResponse(call: Call<ResponseModel<List<Comment>>>,
                                    response: Response<ResponseModel<List<Comment>>>) {
                if (response.isSuccessful) {
                    val list = response.body()?.result as List<Comment>
                    refreshSnackList(list)
                }
            }

            override fun onFailure(call: Call<ResponseModel<List<Comment>>>, t: Throwable) {

            }
        })
    }

    fun showTip(info: String) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
    }


    fun refreshSnackList(list: List<Comment>) {
        if (commentList != null) {
            commentList?.addAll(list)
        } else {
            commentList = list as ArrayList<Comment>
        }
        adapter.list = commentList as ArrayList<Comment>

        dataToLog()
        adapter.notifyDataSetChanged()
    }

    private fun dataToLog() {
        val sb = StringBuilder()
        commentList?.forEach {
            sb.append(it.toString())
        }
        Log.i("list_info", sb.toString())
    }
}