package me.codeminions.factory.presenter.mePage

import me.codeminions.common.mvp.BaseContract
import me.codeminions.factory.data.bean.Comment
import me.codeminions.factory.data.model.baseModel.SnackListModel

interface MePageContract {

    interface MePageView : BaseContract.BaseView<MePagePresenter> {
        fun refreshList(list: List<Comment>)

        fun refreshOtherList(list: List<SnackListModel>)
    }

    interface MePagePresenter : BaseContract.BasePresenter {
        fun getCommentByUser(userId: Int)

        /**
         * 获取零食清单
         */
        fun getSnackListByUser(userId: Int)
    }
}
