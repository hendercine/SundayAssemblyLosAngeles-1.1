/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 9/21/18 1:48 PM
 */

package com.hendercine.sala_v1_1.ui.mainview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hendercine.sala_v1_1.BuildConfig;
import com.hendercine.sala_v1_1.R;
import com.hendercine.sala_v1_1.models.Assembly;
import com.hendercine.sala_v1_1.ui.aboutsala.AboutSalaFragment;
import com.hendercine.sala_v1_1.ui.assemblies.AssembliesFragment;
import com.hendercine.sala_v1_1.ui.base.BaseActivity;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindString;
import butterknife.BindView;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    public static final int RC_SIGN_IN = 237;

    private static final String USER_ID = "userId";
    private static final String USER_NAME = "userName";
    private static final String USER_PHOTO_URL = "userPhotoUrl";
    private static final String CURRENT_USER = "current_user";
    private static final String ASSEMBLIES = "assemblies";

    private String mAppBarTitle;
    private String mAppBarImageUrl;
    private String mAssemblyDateAndTheme;
    private String mAssemblyBackDrop;
    private String mUserId;
    private String mUsername;
    private String mUserPhotoUrl;

    private Fragment mFragment;
    private Bundle mBundle;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference mAssemblyDbRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseUser mCurrentUser;

    private ActionBarDrawerToggle mToggle;
    private ActionBar mActionBar;

    private FragmentManager mFragmentManager;
    private AboutSalaFragment mAboutSalaFragment;
    private AssembliesFragment mAssemliesFragment;

    private boolean mIsTwoPane;
    private String[] mSideBarArray;
    private SideBarRVAdapter mSideBarAdapter;

    private Assembly mAssemblyData;
    private Assembly mAssembly;
//    private Performer mPerformer;
//    private Song mSong;
    private ArrayList<Assembly> mAssembliesList;

    private View mNavHeaderView;

    @Nullable
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @Nullable
    @BindView(R.id.nav_view)
    NavigationView mNavView;

    @Nullable
    @BindView(R.id.main_side_bar_recycler_view)
    RecyclerView mSideBarRecyclerView;

    @BindView(R.id.collapsing_toolbar_backdrop_img)
    ImageView collapsingToolbarBackDrop;
    @Nullable
    @BindView(R.id.toolbar_main)
    Toolbar mToolbar;
    @Nullable
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Nullable
    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;
    @Nullable
    @BindView(R.id.user_nav_header_img_view)
    ImageView mUserNavHeaderIV;
    @Nullable
    @BindView(R.id.username_nav_header_text_view)
    TextView mUsernameHeaderTV;
    @Nullable
    @BindView(R.id.logout_btn)
    Button mNavLogoutBtn;
    @BindView(R.id.content_frame)
    FrameLayout mContentFrame;

    @BindString(R.string.about_banner_url)
    String mAboutBannerUrl;
    @BindString(R.string.about_sala_title)
    String mAboutTitle;
    @BindString(R.string.about_nav_title)
    String mAboutSideBar;
    @BindString(R.string.program_nav_title)
    String mProgramSidebar;
    @BindString(R.string.lyrics_nav_title)
    String mLyricsSideBar;
    @BindString(R.string.speaker_bio_nav_title)
    String mSpeakerSideBar;
    @BindString(R.string.assemblies_nav_title)
    String mAssembliesSideBar;
    @BindString(R.string.help_often_nav_title)
    String mHelpSideBar;
    @BindString(R.string.live_better_nav_title)
    String mLiveSideBar;
    @BindString(R.string.salamander_chat_nav_title)
    String mChatSideBar;
    @BindString(R.string.instagram_nav_title)
    String mInstaSideBar;
    @BindString(R.string.facebook_nav_title)
    String mFacebookSideBar;
    @BindString(R.string.twitter_nav_title)
    String mTwitterSideBar;
    @BindString(R.string.sala_on_the_web_nav_title)
    String mWebsiteSideBar;
    @BindString(R.string.logout_nav_title)
    String mLogoutSideBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag("LogMessage");
        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mFirebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();

        mAssemblyDbRef = mFirebaseDatabase.getReference().child(ASSEMBLIES);

        if (savedInstanceState != null) {
            mUserId = savedInstanceState.getString(USER_ID);
            mUsername = savedInstanceState.getString(USER_NAME);
            mUserPhotoUrl = savedInstanceState.getString(USER_PHOTO_URL);
        }

        mIsTwoPane = getResources().getBoolean(R.bool.isTablet);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mAppBarTitle = mAboutTitle;
        mAppBarImageUrl = mAboutBannerUrl;

        // Setup Two-pane sidebar "drawer"
        if (mIsTwoPane && mSideBarRecyclerView != null) {
            mSideBarArray = new String[]{
                    mAboutSideBar, mAssembliesSideBar, mProgramSidebar,
                    mLyricsSideBar, mSpeakerSideBar, mHelpSideBar,
                    mLiveSideBar, mChatSideBar, mInstaSideBar,
                    mFacebookSideBar, mTwitterSideBar, mWebsiteSideBar,
                    mLogoutSideBar};
            mSideBarRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mSideBarAdapter = new SideBarRVAdapter(mSideBarArray);
            mSideBarRecyclerView.setAdapter(mSideBarAdapter);

            activateSideBarItems();
        } else {
            if (mActionBar != null) {
                mActionBar.setDisplayHomeAsUpEnabled(true);
                mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            }

            mToggle = new ActionBarDrawerToggle(
                    this,
                    mDrawer,
                    mToolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
            );

            activateDrawerItems();
        }

        if (savedInstanceState == null) {
            mFragmentManager = getSupportFragmentManager();
            mAssemliesFragment = new AssembliesFragment();
            mFragmentManager
                    .beginTransaction()
                    .add(mContentFrame.getId(), mAssemliesFragment)
                    .commit();
        }

        setCollapsingToolbarBehavior();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // Check if user is signed in (non-null) and update UI accordingly.
                mCurrentUser = firebaseAuth.getCurrentUser();
                if (mCurrentUser != null) {
                    if (savedInstanceState == null) {
                        // already signed in
                        checkIfNewUser(mCurrentUser);
                        updateUI(mCurrentUser);
                    }
                } else {
                    // not signed in
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(!BuildConfig.DEBUG /* Credentials */, true /* hints */)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.EmailBuilder().build(),
                                            new AuthUI.IdpConfig.GoogleBuilder()
                                                    .build()
                                    ))
                                    .setLogo(R.drawable.sala_logo_grass)
                                    .build(),
                            RC_SIGN_IN
                    );
                }
            }
        };
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

    private void updateUI(FirebaseUser currentUser) {

    }

    private void checkIfNewUser(FirebaseUser currentUser) {

    }

    private void setCollapsingToolbarBehavior() {

    }

    private void activateDrawerItems() {

    }

    private void activateSideBarItems() {

    }
}
