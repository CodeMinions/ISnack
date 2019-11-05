package me.codeminions.factory.presenter.snackMain;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import me.codeminions.common.mvp.BaseContract;
import me.codeminions.factory.data.bean.Snack;

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

        public void jumpToMe(Context context);

        /**
         * 显示拍照按钮
         */
        public void showButton(int position);

        /**
         * 启动相机
         */
        public void startPhoto(Uri uri);


        public void showPhotoResult(Bitmap bitmap);

        void initSearchList(List<Snack> list);

        void hintProgress();
    }

    public interface SnackMainPresenter extends BaseContract.BasePresenter {
        public void jumpToMe(Context context);

        public void startPhoto(@NotNull Context context);

        void searchSnack(String content);
    }
}
