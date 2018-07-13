package com.hendercine.sala;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View,
                                                          View.OnClickListener{

    @BindView(R.id.tvHello)
    TextView mTextView;
    private MainPresenter mPresenter;

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        mTextView.setOnClickListener(this);
        mPresenter = new MainPresenter();
        mPresenter.attach(this);
        mPresenter.loadHelloText();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    @Override
    public void onTextLoaded(String text) {
        mTextView.setText(text);

    }

    @Override
    public void onClick(View v) {
        mPresenter.loadHelloText();
    }

}
