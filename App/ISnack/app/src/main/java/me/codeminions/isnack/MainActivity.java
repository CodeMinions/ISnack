package me.codeminions.isnack;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.List;

import butterknife.BindView;
import me.codeminions.common.mvp.BaseContract;
import me.codeminions.common.widget.BaseViewPagerAdapter;
import me.codeminions.common.widget.BindingRecyclerAdapter;
import me.codeminions.factory.PresenterActivity;
import me.codeminions.factory.data.bean.Snack;
import me.codeminions.factory.presenter.snackMain.SnackMainContract;
import me.codeminions.factory.presenter.snackMain.SnackMainPresenter;
import me.codeminions.isnack.commentPager.CommentFragment;
import me.codeminions.isnack.databinding.ActivityMainBinding;
import me.codeminions.isnack.databinding.ItemSnackSearchBinding;
import me.codeminions.isnack.editListPage.EditListActivity;
import me.codeminions.isnack.firstPage.FirstFragment;
import me.codeminions.isnack.mePage.MeActivity;
import me.codeminions.isnack.photoResult.PhotoResultFragment;
import me.codeminions.isnack.recommendPage.RecommendFragment;
import me.codeminions.isnack.snackDetails.SnackDetailActivity;

import static me.codeminions.factory.net.RetrofitServiceKt.URL_PIC;

public class MainActivity extends PresenterActivity<ActivityMainBinding>
        implements SnackMainContract.SnackMainView<SnackMainContract.SnackMainPresenter>,
        ViewPager.OnPageChangeListener {

    private SnackMainContract.SnackMainPresenter presenter;

    @Override
    public void setPresenter(SnackMainContract.SnackMainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    @NotNull
    public BaseContract.BasePresenter initPresenter() {
        return new SnackMainPresenter(MainActivity.this);
    }

    @BindView(R.id.tab_main)
    TabLayout tabLayout;

    @BindView(R.id.vp_main)
    ViewPager viewPager;

    @BindView(R.id.btn_main_photo)
    FloatingActionButton btnPhoto;


    FirstFragment firstFragment;
    RecommendFragment recommendFragment;
    CommentFragment commentFragment;

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
        recommendFragment = new RecommendFragment();
        commentFragment = new CommentFragment();

        viewPager.setAdapter(
                new BaseViewPagerAdapter(
                        new String[]{"推荐", "主页", "关注社区"},
                        getSupportFragmentManager(),
                        recommendFragment, firstFragment, commentFragment) {
                });

        viewPager.addOnPageChangeListener(this);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);

        viewPager.setCurrentItem(1);
        binding.setPosition(1);

        // 设置回车键监听
        // getText必须在监听事件中获得，否则获取不到
        binding.editorMain.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null
                    && KeyEvent.KEYCODE_ENTER == event.getKeyCode()
                    && KeyEvent.ACTION_DOWN == event.getAction())) {

                String searchContent = binding.editorMain.getText().toString();
                if (!searchContent.isEmpty()) {
                    binding.editorMain.setText(searchContent.trim());
                    startSearch(searchContent);
                }
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("resunme", "刷新");

//        initWidget();
//        initData();
    }

    public void startSearch(String content) {
        // 显示搜索页面
        binding.frameSearch.setVisibility(View.VISIBLE);
        binding.vpMain.setVisibility(View.GONE);

        binding.btnBack.setOnClickListener(v -> {
            binding.frameSearch.setVisibility(View.GONE);
            binding.vpMain.setVisibility(View.VISIBLE);
        });

        // 请求查找
        presenter.searchSnack(content);
    }

    @Override
    public void hintProgress() {
        binding.progressSearch.setVisibility(View.INVISIBLE);
        binding.recyclerSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void initSearchList(List<Snack> list) {
        binding.recyclerSearch.setLayoutManager(new LinearLayoutManager(this));
        BindingRecyclerAdapter<Snack, ItemSnackSearchBinding> adapter = new BindingRecyclerAdapter<Snack, ItemSnackSearchBinding>() {
            @Override
            public void onBindViewHolder(@NotNull ItemSnackSearchBinding bing, Snack snack) {
                bing.setSnack(snack);
                bing.setImgUrl(URL_PIC + snack.getImg());
                bing.btnJumpSearch.setOnClickListener(v -> {
                    // 跳转并隐藏搜索页
                    SnackDetailActivity.Companion.startAction(MainActivity.this, snack);
                    binding.frameSearch.setVisibility(View.INVISIBLE);
                    binding.vpMain.setVisibility(View.VISIBLE);
                    binding.editorMain.setText("");
                });
            }

            @Override
            public int getItemViewType(int position) {
                return R.layout.item_snack_search;
            }
        };
        adapter.setList(list);
        binding.recyclerSearch.setAdapter(adapter);

        binding.progressSearch.setVisibility(View.INVISIBLE);
        binding.recyclerSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {

    }

    @Override
    public void showTip(String info) {
        Toast.makeText(MainActivity.this, info, Toast.LENGTH_SHORT).show();
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

    public void onClickEdit(View v) {
        // 启动编辑页
        EditListActivity.Companion.startAction(this);
    }

    @Override
    public void showButton(int position) {
        float translationY = 0;
        float rotation = 0;

        switch (position) {
            case 0:
                translationY = 300;
                rotation = 360;
                break;
            case 1:
                translationY = 0;
                rotation = -360;
                btnPhoto.setImageResource(R.drawable.ic_dashboard_black_24dp);
                break;
            case 2:
                translationY = 0;
                rotation = 0;
                btnPhoto.setImageResource(R.drawable.ic_add);
                break;
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
        binding.setPosition(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
