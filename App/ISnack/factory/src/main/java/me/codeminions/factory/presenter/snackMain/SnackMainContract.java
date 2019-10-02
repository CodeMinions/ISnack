package me.codeminions.factory.presenter.snackMain;

import me.codeminions.common.mvp.BaseContract;

public interface SnackMainContract {

    public interface SnackMainView<T extends SnackMainPresenter> extends BaseContract.BaseView<T> {
        /**
         * 查看头像
         */
        public void lookPortrait();

        /**
         * 点击搜索
         */
        public void search();

        /**
         * TODO: 19-9-21 购物
         */
        public void shop();

        /**
         * 列表数据筛选
         */
        public void selectData();

        /**
         * TODO: 19-9-21 添加
         */
        public void addSomething();
    }

    public interface SnackMainPresenter extends BaseContract.BasePresenter {

    }
}
