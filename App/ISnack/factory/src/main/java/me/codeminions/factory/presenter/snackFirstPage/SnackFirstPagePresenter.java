package me.codeminions.factory.presenter.snackFirstPage;

import java.util.ArrayList;

import me.codeminions.factory.data.bean.Snack;
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
        ArrayList<Snack> list = model.loadAll();
        view.refreshSnackList(list);
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {

    }
}
