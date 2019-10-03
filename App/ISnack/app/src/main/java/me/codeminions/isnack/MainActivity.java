package me.codeminions.isnack;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.io.FileNotFoundException;

import butterknife.BindView;
import me.codeminions.common.app.DataBindingActivity;
import me.codeminions.common.widget.BaseViewPagerAdapter;
import me.codeminions.factory.presenter.snackMain.SnackMainContract;
import me.codeminions.factory.presenter.snackMain.SnackMainPresenter;
import me.codeminions.isnack.databinding.ActivityMainBinding;
import me.codeminions.isnack.firstPage.FirstFragment;
import me.codeminions.isnack.mePage.MeActivity;
import me.codeminions.isnack.photoResult.PhotoResultFragment;

public class MainActivity extends DataBindingActivity<ActivityMainBinding>
        implements SnackMainContract.SnackMainView<SnackMainContract.SnackMainPresenter>,
        ViewPager.OnPageChangeListener {

    private SnackMainContract.SnackMainPresenter presenter;

    @Override
    public void setPresenter(SnackMainContract.SnackMainPresenter presenter) {
        this.presenter = presenter;
    }

    SnackMainContract.SnackMainPresenter initPresenter() {
        return new SnackMainPresenter(MainActivity.this);
    }

    @BindView(R.id.tab_main)
    TabLayout tabLayout;

    @BindView(R.id.vp_main)
    ViewPager viewPager;

    @BindView(R.id.btn_main_photo)
    FloatingActionButton btnPhoto;


    FirstFragment firstFragment;
    FirstFragment firstFragment1;
    FirstFragment firstFragment2;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initPresenter();

        binding.setHandler(this);

        firstFragment = new FirstFragment();
        firstFragment1 = new FirstFragment();
        firstFragment2 = new FirstFragment();

        viewPager.setAdapter(
                new BaseViewPagerAdapter(
                        new String[]{"个人", "主页", "分类"},
                        getSupportFragmentManager(),
                        firstFragment, firstFragment1, firstFragment2) {
                });

        viewPager.addOnPageChangeListener(this);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(1);
    }

    @Override
    public void initData() {

    }

    @Override
    public void lookPortrait() {
        presenter.jumpToMe(this);
    }

    @Override
    public void jumpToMe(Context context) {
        MeActivity.Companion.startAction(context);
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

    public void onClickPortrait(View v) {
        lookPortrait();
    }

    public void onClickPhoto(View v) {
        presenter.startPhoto(this);
    }

    @Override
    public void showButton(int position) {
        float translationY;
        float rotation;

        if(position == 1) {
            translationY = 0;
            rotation = -360;
        }else {
            translationY = 300;
            rotation = 360;
        }

        btnPhoto.animate()
                .translationY(translationY)
                .setDuration(500)
                .rotation(rotation)
                .start();
    }

    Uri imageUri;

    @Override
    public void startPhoto(Uri uri) {

        imageUri = uri;

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                showPhotoResult(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void showPhotoResult(Bitmap bitmap) {
        PhotoResultFragment fragment = new PhotoResultFragment();
        Bundle b = new Bundle();
        b.putParcelable("pic", bitmap);

        fragment.setArguments(b);
        fragment.show(getSupportFragmentManager(), "ResultFragment");
    }

    @Override
    public void onPageSelected(int position) {
        showButton(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
