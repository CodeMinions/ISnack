package me.codeminions.factory.presenter.editPage

import me.codeminions.common.mvp.BaseContract
import me.codeminions.factory.data.bean.Snack
import java.util.ArrayList

interface EditListConstract {

    interface EditListView : BaseContract.BaseView<EditListPresenter> {

        /**
         * 获取线上零食信息
         */
        fun getSnackList()

        /**
         * 刷新零食列表
         */
        public fun refreshSnackList(list: ArrayList<Snack>)

        fun getListContent() : String

        fun sendSuccess()

        fun sendFail(info: String)
    }

    interface EditListPresenter : BaseContract.BasePresenter {
        /**
         * 向后台获取零食列表
         */
        fun getSnackList()

        /**
         * 发送用户清单
         */
        fun sendSnackList(userId: Int, title: String,
                          snackList: List<Snack>, content: String)
    }
}