package me.codeminions.isnack.recommendPage

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import me.codeminions.common.widget.BindingRecyclerAdapter
import me.codeminions.common.widget.OnItemClickListener
import me.codeminions.factory.RecyclerFragment
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.net.URL_PIC
import me.codeminions.factory.presenter.snackFirstPage.SnackFirstPageContract
import me.codeminions.factory.presenter.snackFirstPage.SnackFirstPagePresenter
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.FragmentMainBinding
import me.codeminions.isnack.databinding.ItemMainSnacklistBinding
import me.codeminions.isnack.snackDetails.SnackDetailActivity
import java.util.ArrayList

class RecommendFragment :
        RecyclerFragment<FragmentMainBinding, ItemMainSnacklistBinding, Snack>(),
        SnackFirstPageContract.SnackFirstPageView<SnackFirstPageContract.SnackFirstPagePresenter> {

    private lateinit var presenter: SnackFirstPageContract.SnackFirstPagePresenter

    private var snackList: ArrayList<Snack>? = null

    override val recyclerView: RecyclerView by lazy { list_main_fragment }

    override val adapter: BindingRecyclerAdapter<Snack, ItemMainSnacklistBinding> =
            object : BindingRecyclerAdapter<Snack, ItemMainSnacklistBinding>() {

                override fun getItemViewType(position: Int) = R.layout.item_main_snacklist

                override fun onBindViewHolder(bing: ItemMainSnacklistBinding, data: Snack) {
                    bing.snack = data
                    bing.imgResUrl = URL_PIC + data.img
                }
            }

    override fun initWidget(root: View) {
        super.initWidget(root)

        adapter.listener = object : OnItemClickListener<Snack> {
            override fun onItemClick(data: Snack) = lookSnack(data)
        }
    }

    override fun initData() {
        getSnackList()

        if (!snackList.isNullOrEmpty()) {
            recyclerView.adapter = adapter
            adapter.list = snackList as ArrayList
            recyclerView.layoutManager = getLayoutManager()
            adapter.notifyDataSetChanged()
        }
    }

    override fun lookSnack(snack: Snack?) {
        SnackDetailActivity.startAction(context!!, snack!!)
    }

    override fun addSnack() {

    }

    override fun getSnackList() {
        presenter.getRecommendList()
    }

    override fun refreshSnackList(list: ArrayList<Snack>) {
        if (snackList != null) {
            snackList!!.addAll(list)
        } else {
            snackList = list
        }
        adapter.list = snackList as ArrayList<Snack>

        dataToLog()
        adapter.notifyDataSetChanged()
    }

    private fun dataToLog() {
        val sb = StringBuilder()
        snackList?.forEach {
            sb.append(it.toString())
        }
        Log.i("list_info", sb.toString())
    }

    override fun showTip(info: String?) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

    override fun setPresenter(presenter: SnackFirstPageContract.SnackFirstPagePresenter) {
        this.presenter = presenter
    }

    override fun initPresenter(): SnackFirstPageContract.SnackFirstPagePresenter {
        return SnackFirstPagePresenter(this)
    }

    override fun getContentLayoutId(): Int = R.layout.fragment_main

}