package me.codeminions.isnack;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import me.codeminions.common.app.BaseActivity;
import me.codeminions.factory.presenter.snackMain.SnackMainContract;

public class MainActivity extends BaseActivity implements SnackMainContract.SnackMainView<SnackMainContract.SnackMainPresenter> {

    @Override
    public void setPresenter(SnackMainContract.SnackMainPresenter presenter) {

    }

    @BindView(R.id.tab_main)
    TabLayout tabLayout;

    @BindView(R.id.vp_main)
    ViewPager viewPager;

    FirstFragment firstFragment;
    FirstFragment firstFragment1;
    FirstFragment firstFragment2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        firstFragment = new FirstFragment();
        firstFragment1 = new FirstFragment();
        firstFragment2 = new FirstFragment();

        viewPager.setAdapter(new MainTabAdapter(getSupportFragmentManager(), firstFragment, firstFragment1, firstFragment2));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void lookPortrait() {

    }

    @Override
    public void search() {

    }

    @Override
    public void shop() {

    }

    @Override
    public void selectData() {

    }

    @Override
    public void addSomething() {

    }

}
