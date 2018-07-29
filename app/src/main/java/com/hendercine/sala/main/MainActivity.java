package com.hendercine.sala.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.hendercine.sala.R;
import com.hendercine.sala.base.BaseActivity;
import com.hendercine.sala.chrome.ChromeTabsWrapper;
import com.hendercine.sala.model.Feed;
import com.hendercine.sala.model.RError;
import com.hendercine.sala.model.Assembly;
import com.hendercine.sala.assemblies.RssFragment;
import com.hendercine.sala.assemblies.RssFragmentAdapter;
import com.hendercine.sala.util.FeedParser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements
                                                                       MainContract
                                                                               .View,
                                                                       RssFragment.OnItemSelectListener {


    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar mToolbar;

    @Inject
    ChromeTabsWrapper mChromeTabsWrapper;

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle state) {
        setSupportActionBar(mToolbar);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        getPresenter().loadAssemblyFragments();
    }

    @Override
    public void injectDependencies() {
        getActivityComponent().inject(this);
    }


    @Override
    public void onLoadAssemblyFragments() {
        setUpViewPager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mChromeTabsWrapper.bindCustomTabsService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mChromeTabsWrapper.unbindCustomTabsService();
    }

    @Override
    public void onItemSelected(Assembly assembly) {
        mChromeTabsWrapper.openCustomTab(assembly.getAssemblyTheme());
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

    @Override
    public void onFail(RError error) {

    }
}
