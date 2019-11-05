package me.codeminions.factory.presenter.snackFirstPage;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import me.codeminions.factory.data.bean.Snack;
import me.codeminions.factory.data.model.baseModel.ResponseCallBack;
import me.codeminions.factory.data.model.snackModel.SnackModel;

public class SnackFirstPagePresenter implements SnackFirstPageContract.SnackFirstPagePresenter {

    private SnackModel model = new SnackModel();
    private SnackFirstPageContract.SnackFirstPageView view;

    public SnackFirstPagePresenter(SnackFirstPageContract.SnackFirstPageView<SnackFirstPageContract.SnackFirstPagePresenter> view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void lookSnack(Snack snack) {

    }

    @Override
    public void getSnackList() {
        model.loadAll(new ResponseCallBack<List<Snack>>() {
            @Override
            public void onSuccess(@NotNull String info, List<Snack> response) {
                view.refreshSnackList(new ArrayList<>(response));
            }

            @Override
            public void onFail(@NotNull String info) {
                view.showTip(info);
            }
        });
    }

    @Override
    public void getRecommendList() {
        model.loadRecommend(new ResponseCallBack<List<Snack>>() {
            @Override
            public void onSuccess(@NotNull String info, List<Snack> response) {
                view.refreshSnackList(new ArrayList<>(response));
            }

            @Override
            public void onFail(@NotNull String info) {
                view.showTip(info);
            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {

    }
}
