package com.hendercine.sala;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.hendercine.sala.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.tvHello)
    TextView mTextView;

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        getPresenter().loadHelloText();
    }

    @Override
    protected void injectDependencies() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onTextLoaded(String text) {
        mTextView.setText(text);

    }

    @OnClick(R.id.tvHello)
    public void onClick() {
        getPresenter().loadHelloText();
    }

}
