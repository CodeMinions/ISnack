package me.codeminions.common.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;

public abstract class Fragment extends androidx.fragment.app.Fragment {

    private View mRoot;

    @LayoutRes
    protected abstract int getContentLayoutId();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(getContentLayoutId(), container, false);
        this.mRoot = root;
        initWidget(root);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initData();
    }

    protected void initWidget(View root){
        ButterKnife.bind(this, root);
    }

    protected void initData(){

    }

    public View getmRoot() {
        return mRoot;
    }
}
