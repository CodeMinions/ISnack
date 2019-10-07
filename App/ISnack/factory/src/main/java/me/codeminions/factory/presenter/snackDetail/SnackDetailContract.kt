package me.codeminions.factory.presenter.snackDetail

import me.codeminions.common.mvp.BaseContract
import me.codeminions.factory.data.bean.Comment
import me.codeminions.factory.data.bean.SnackInfo
import me.codeminions.factory.data.model.ResponseCallBack
import me.codeminions.factory.data.model.SnackInfoModel

interface SnackDetailContract {

    interface SnackDetailView : BaseContract.BaseView<SnackDetailPresenter> {

        /**
         * 查看评论
         */
        fun lookComment()

        fun loadCommentSuccess(list: List<Comment>)

        fun loadMoreSuccess(list: List<SnackInfoModel>)

        fun initInfoTable(list: List<SnackInfoModel>)
    }

    interface SnackDetailPresenter : BaseContract.BasePresenter {

        fun getSnackMore(snackID: Int)

        fun getComment(snackId: Int)
    }
}