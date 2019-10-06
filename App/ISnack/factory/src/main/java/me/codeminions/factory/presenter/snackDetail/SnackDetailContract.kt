package me.codeminions.factory.presenter.snackDetail

import me.codeminions.common.mvp.BaseContract
import me.codeminions.factory.data.bean.SnackInfo
import me.codeminions.factory.data.model.ResponseCallBack

interface SnackDetailContract {

    interface SnackDetailView : BaseContract.BaseView<SnackDetailPresenter> {

        /**
         * 查看评论
         */
        fun lookComment()

        fun loadInfoSuccess()

        fun loadMoreSuccess()
    }

    interface SnackDetailPresenter : BaseContract.BasePresenter {

        fun getSnackMore(snackID: Int)
    }
}