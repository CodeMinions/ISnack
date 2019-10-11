package me.codeminions.isnack.editListPage

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.text.AlteredCharSequence
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_edit_list.*
import me.codeminions.common.mvp.BaseContract
import me.codeminions.common.widget.BindingRecyclerAdapter
import me.codeminions.factory.PresenterActivity
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.net.URL_PIC
import me.codeminions.factory.presenter.editPage.EditListConstract
import me.codeminions.factory.presenter.editPage.EditListPresenter
import me.codeminions.factory.utils.getUserId
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.ActivityEditListBinding
import me.codeminions.isnack.databinding.ItemSnackListSelectedBinding
import java.util.*
import kotlin.collections.ArrayList

class EditListActivity : PresenterActivity<ActivityEditListBinding>(),
        EditListConstract.EditListView {

    override fun initPresenter(): BaseContract.BasePresenter {
        return EditListPresenter(this)
    }

    private lateinit var presenter: EditListConstract.EditListPresenter

    private val resultList = ArrayList<Snack>()

    private var snackList: ArrayList<Snack>? = null

    private var isEdtiorOpen = false

    override fun getLayoutResId(): Int = R.layout.activity_edit_list

    companion object {
        fun startAction(context: Context) {
            val intent = Intent(context, EditListActivity::class.java)
            context.startActivity(intent)
        }
    }

    lateinit var adapter: BindingRecyclerAdapter<Snack, ItemSnackListSelectedBinding>

    override fun initWidget() {
        super.initWidget()
        binding.handler = this

        binding.recyclerSnackList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = object : BindingRecyclerAdapter<Snack, ItemSnackListSelectedBinding>() {
            override fun onBindViewHolder(bing: ItemSnackListSelectedBinding, data: Snack) {
                bing.snack = data
                bing.imgResUrl = URL_PIC + data.img

                bing.listCheck.setOnCheckedChangeListener { _, isChecked ->
                    run {
                        bing.isCheck = isChecked
                        makeResultList(data, isChecked)
                    }
                }
            }

            override fun getItemViewType(position: Int): Int = R.layout.item_snack_list_selected
        }
        recycler_snack_list.adapter = adapter
    }

    override fun initData() {
        getSnackList()
    }

    fun makeResultList(data: Snack, isCheck: Boolean) {
        if (isCheck) {
            resultList.add(data)
        } else {
            resultList.remove(data)
        }
    }

    fun onClickSendList(v: View) {
        val userId = getUserId(this)
        if (userId.isEmpty()) {
            AlertDialog.Builder(this).setTitle("是不是忘了登录哟").show()
        } else {
            // 弹框输入标题
            val editText = EditText(this)
            val build = AlertDialog.Builder(this)
            build.setTitle("给清单写个标题吧")
                    .setView(editText)
                    .setPositiveButton("提交啦~\\(≧▽≦)/~") { dialog, _ ->
                        run {
                            presenter.sendSnackList(userId.toInt(), editText.text.toString(), resultList, getListContent())
                            dialog.dismiss()
                        }
                    }.show()
        }
    }

    override fun sendSuccess() {
        showTip("成功提交啦~~")
        finish()
    }

    override fun sendFail(info: String) {
        showTip(info)
    }

    override fun getListContent(): String = edit_list_text.text.toString()

    // 点击上划
    fun openEditor(view: View) {
        val translationY = if (isEdtiorOpen)
            780f
        else
            0f

        isEdtiorOpen = !isEdtiorOpen

        edit_list_editor.animate()
                .setDuration(200)
                .translationY(translationY)
//                .setInterpolator(AnticipateInterpolator(2f))
                .start()
    }

    override fun getSnackList() {
        presenter.getSnackList()
    }

    override fun refreshSnackList(list: ArrayList<Snack>) {
        if (snackList != null) {
            snackList?.addAll(list)
        } else {
            snackList = list
        }
        adapter.list = snackList as ArrayList<Snack>

        adapter.notifyDataSetChanged()
    }

    override fun setPresenter(presenter: EditListConstract.EditListPresenter?) {
        this.presenter = presenter!!
    }

    override fun showTip(info: String?) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show()
    }

    fun onClickBack(v: View) {
        finish()
    }
}