package me.codeminions.common.mvp;


public interface BaseContract {

    public interface BaseView<T extends BasePresenter> {
        public void setPresenter(T presenter);

        public void showTip(String info);
    }

    public interface BasePresenter {
        public void start();

        public void destroy();
    }
}
