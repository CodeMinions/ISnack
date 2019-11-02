package me.codeminions.factory.presenter.snackDetail

import me.codeminions.common.mvp.BaseContract
import me.codeminions.factory.data.bean.Comment
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.baseModel.CommentModel
import me.codeminions.factory.data.model.baseModel.ResponseCallBack
import me.codeminions.factory.data.model.baseModel.SnackInfoModel

interface SnackDetailContract {

    interface SnackDetailView : BaseContract.BaseView<SnackDetailPresenter> {

        /**
         * 查看评论
         */
        fun lookComment()

        fun loadCommentSuccess(list: List<CommentModel>)

        fun loadMoreSuccess(list: List<SnackInfoModel>)

        fun initInfoTable(list: List<SnackInfoModel>)

        fun showMarkInfo(data: IntArray)
    }

    interface SnackDetailPresenter : BaseContract.BasePresenter {

        fun getCommentUser(userId: Int, callBack: ResponseCallBack<User>)

        fun getSnackMore(snackID: Int)

        fun getComment(snackId: Int)

        fun getMark(snackId: Int)
    }
}