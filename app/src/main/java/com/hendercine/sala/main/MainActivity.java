package com.hendercine.sala.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.hendercine.sala.R;
import com.hendercine.sala.base.BaseActivity;
import com.hendercine.sala.chrome.ChromeTabsWrapper;

import java.util.ArrayList;
import java.util.List;

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
        getPresenter().loadRssFragments();
    }

    @Override
    public void injectDependencies() {
        getActivityComponent().inject(this);
    }

    @OnClick(R.id.tvHello)
    public void onClick() {
        getPresenter().loadRssFragments();
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

    @Override
    public void onLoadRssFragments() {

    }

    public void setUpViewPager() {
        List<RssFragment> fragmentList = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (Feed feed : new FeedParser().parseFeeds(this)) {
            fragmentList.add(RssFragment.newInstance(feed));
            titles.add(feed.getTitle());
        }

        RssFragmentAdapter adapter = new RssFragmentAdapter
                (getSupportFragmentManager(), fragmentList, titles);
        mViewPager.setAdapter(adapter);
    }
}