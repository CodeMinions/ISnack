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
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.ResponseModel
import me.codeminions.factory.data.model.SnackListModel
import me.codeminions.factory.net.RetrofitService
import me.codeminions.factory.net.URL_PIC
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.FragmentCommentBinding
import me.codeminions.isnack.databinding.ItemMyPostBinding
import me.codeminions.isnack.databinding.ItemShowSnackListItBinding
import me.codeminions.isnack.databinding.ItemSnackListShowBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CommentFragment :
        DataBindingFragment<FragmentCommentBinding>() {

    private var commentList: ArrayList<Comment>? = null

    private lateinit var adapter: BindingRecyclerAdapter<Comment, ItemMyPostBinding>
    private lateinit var snackListAdapter: BindingRecyclerAdapter<SnackListModel, ItemSnackListShowBinding>

    private var snackList: ArrayList<SnackListModel>? = null

    override fun initWidget(root: View) {
        dataBinding.fragRecyclerSnackList.layoutManager = LinearLayoutManager(context)
        snackListAdapter = object : BindingRecyclerAdapter<SnackListModel, ItemSnackListShowBinding>() {
            override fun getItemViewType(position: Int): Int = R.layout.item_snack_list_show

            override fun onBindViewHolder(bing: ItemSnackListShowBinding, data: SnackListModel) {
                bing.model = data
                bing.imgUrl = URL_PIC + data.user.portrait

                bing.isShow = false
                bing.btnItemSnackList.setOnClickListener {
                    val r = if(!bing.isShow) 90f else 0f
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
        dataBinding.fragRecyclerSnackList.adapter = snackListAdapter


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
        RetrofitService.getApiService().getAllSnackList().enqueue(object : Callback<ResponseModel<List<SnackListModel>>> {
            override fun onResponse(call: Call<ResponseModel<List<SnackListModel>>>, response: Response<ResponseModel<List<SnackListModel>>>) {
                if (response.isSuccessful) {
                    if(response.body()?.code == 1) {
                        val list = response.body()?.result as List<SnackListModel>
                        refreshOtherList(list)
                    }else{
                        showTips(response.body()?.message!!)
                    }
                }
            }
            override fun onFailure(call: Call<ResponseModel<List<SnackListModel>>>, t: Throwable) {
                showTips("ServerError : ${t.message}")
            }
        })
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

    fun showTips(info: String) {
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

    fun refreshOtherList(list: List<SnackListModel>) {
        if (snackList != null)
            snackList!!.addAll(list)
        else
            snackList = list as ArrayList<SnackListModel>
        snackListAdapter.list = list
        snackListAdapter.notifyDataSetChanged()
    }

    private fun dataToLog() {
        val sb = StringBuilder()
        commentList?.forEach {
            sb.append(it.toString())
        }
        Log.i("list_info", sb.toString())
    }
}