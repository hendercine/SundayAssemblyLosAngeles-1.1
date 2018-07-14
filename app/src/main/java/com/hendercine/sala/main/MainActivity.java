package com.hendercine.sala.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.hendercine.sala.R;
import com.hendercine.sala.base.BaseActivity;
import com.hendercine.sala.chrome.ChromeTabsWrapper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.tvHello)
    TextView mTextView;

    @Inject
    ChromeTabsWrapper mChromeTabsWrapper;


    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle state) {
        getPresenter().loadHelloText();
    }

    @Override
    public void injectDependencies() {
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

    @Override
    protected void onStart() {
        super.onStart();
        mChromeTabsWrapper.bindCustomTabsService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mChromeTabsWrapper.unbindCustomTabsService();
    }
}