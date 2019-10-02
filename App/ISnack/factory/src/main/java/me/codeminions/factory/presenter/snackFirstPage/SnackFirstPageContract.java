package me.codeminions.factory.presenter.snackFirstPage;

import java.util.ArrayList;

import me.codeminions.common.mvp.BaseContract;
import me.codeminions.factory.data.bean.Snack;

public interface SnackFirstPageContract {

    public interface SnackFirstPageView<T extends SnackFirstPagePresenter> extends BaseContract.BaseView<T> {
        /**
         * 跳转零食详细信息
         * @param snack 零食信息
         */
        public void lookSnack(Snack snack);

        /**
         * 添加零食
         */
        public void addSnack();

        /**
         * 获取线上零食信息
         */
        public void getSnackList();

        /**
         * 刷新零食列表
         */
        public void refreshSnackList(ArrayList<Snack> list);
    }

    public interface SnackFirstPagePresenter extends BaseContract.BasePresenter {
        /**
         * 查看零食详细信息
         * @param snack 零食信息
         */
        public void lookSnack(Snack snack);

        /**
         * 向后台获取零食列表
         */
        public void getSnackList();
    }
}
