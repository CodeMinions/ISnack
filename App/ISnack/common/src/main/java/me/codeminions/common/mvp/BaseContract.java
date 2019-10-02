package me.codeminions.common.mvp;


public interface BaseContract {

    public interface BaseView<T extends BasePresenter> {
        public void setPresenter(T presenter);
    }

    public interface BasePresenter {
        public void start();

        public void destroy();
    }
}
