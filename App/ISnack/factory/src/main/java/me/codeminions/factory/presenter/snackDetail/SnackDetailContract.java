package me.codeminions.factory.presenter.snackDetail;

import me.codeminions.common.mvp.BaseContract;

public interface SnackDetailContract {

    interface SnackDetailView extends BaseContract.BaseView<SnackDetailPresenter> {
        /**
         * 想尝试
         */
        public void wantTry();

        /**
         * 已尝试
         */
        public void hadTry();

        /**
         * 查看评论
         */
        public void lookComment();
    }

    interface SnackDetailPresenter extends BaseContract.BasePresenter {

    }
}
