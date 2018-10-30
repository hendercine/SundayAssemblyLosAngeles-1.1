/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 9/21/18 1:48 PM
 */

package com.hendercine.sala_v1_1.ui.mainview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hendercine.sala_v1_1.BuildConfig;
import com.hendercine.sala_v1_1.R;
import com.hendercine.sala_v1_1.models.Assembly;
import com.hendercine.sala_v1_1.models.User;
import com.hendercine.sala_v1_1.ui.aboutsala.AboutSalaFragment;
import com.hendercine.sala_v1_1.ui.assemblies.AssembliesFragment;
import com.hendercine.sala_v1_1.ui.base.BaseActivity;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import butterknife.BindString;
import butterknife.BindView;
import timber.log.Timber;

@SuppressWarnings("Convert2Lambda")
public class MainActivity extends BaseActivity {

    public static final int RC_SIGN_IN = 237;

    private static final String USER_ID = "userId";
    private static final String USER_NAME = "userName";
    private static final String USER_PHOTO_URL = "userPhotoUrl";
    private static final String CURRENT_USER = "current_user";
    private static final String ASSEMBLIES = "assemblies";
    private static final String USERS = "users";

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
//    @BindString(R.string.lyrics_nav_title)
//    String mLyricsSideBar;
    @BindString(R.string.speaker_bio_nav_title)
    String mSpeakerSideBar;
    @BindString(R.string.assemblies_nav_title)
    String mAssembliesSideBar;
    @BindString(R.string.help_often_nav_title)
    String mHelpSideBar;
    @BindString(R.string.live_better_nav_title)
    String mLiveSideBar;
//    @BindString(R.string.salamander_chat_nav_title)
//    String mChatSideBar;
//    @BindString(R.string.instagram_nav_title)
//    String mInstaSideBar;
//    @BindString(R.string.facebook_nav_title)
//    String mFacebookSideBar;
//    @BindString(R.string.twitter_nav_title)
//    String mTwitterSideBar;
//    @BindString(R.string.sala_on_the_web_nav_title)
//    String mWebsiteSideBar;
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
                    mAboutSideBar,
                    mAssembliesSideBar,
                    mProgramSidebar,
//                    mLyricsSideBar,
                    mSpeakerSideBar,
                    mHelpSideBar,
                    mLiveSideBar,
//                    mChatSideBar,
//                    mInstaSideBar,
//                    mFacebookSideBar,
//                    mTwitterSideBar,
//                    mWebsiteSideBar,
                    mLogoutSideBar};
            mSideBarRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mSideBarAdapter = new SideBarRVAdapter(mSideBarArray);
            mSideBarRecyclerView.setAdapter(mSideBarAdapter);
            activateSideBarItems();
        } else {
            // Set home button and nav drawer
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
        // Set app start fragment
        if (savedInstanceState == null) {
            mFragmentManager = getSupportFragmentManager();
            mAboutSalaFragment = new AboutSalaFragment();
            mFragmentManager
                    .beginTransaction()
                    .replace(mContentFrame.getId(), mAboutSalaFragment)
                    .commit();
        }

        setCollapsingToolbarBehavior();

        // Setup sign-in flow
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                showSnackBar(R.string.signed_in_snackbar);
                updateUI(mAuth.getCurrentUser());
            } else if (response == null) {
                showSnackBar(R.string.sign_in_canceled_snackbar);
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return !mIsTwoPane;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.logout_menu) {
            AuthUI.getInstance().signOut(this);
            return true;
        } else {
            return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putParcelable(CURRENT_USER, mCurrentUser);
        outState.putString(USER_ID, mUserId);
        outState.putString(USER_NAME, mUsername);
        outState.putString(USER_PHOTO_URL, mUserPhotoUrl);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(mAuthStateListener);
        updateUI(mAuth.getCurrentUser());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.removeAuthStateListener(mAuthStateListener);
    }

    private void checkIfNewUser(FirebaseUser currentUser) {
        String userId = getUid();
        String displayName = currentUser.getDisplayName();
        String userMail = currentUser.getEmail();

        mDatabaseRef.child(USERS).child(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User user1 = dataSnapshot.getValue(User.class);
                        if (user1 == null) {
                            writeNewUser(userId, displayName, userMail);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        FirebaseUserMetadata metadata = Objects.requireNonNull(
                mAuth.getCurrentUser()).getMetadata();

        if (metadata != null) {
            if (metadata.getCreationTimestamp() == metadata.getLastSignInTimestamp()) {
                // The user is new, show them a fancy intro screen!
                writeNewUser(userId, displayName, userMail);
                showToast("Welcome " + displayName + "!");
            } else {
                // This is an existing user, show them a welcome back screen.
                showToast("Welcome back " + displayName + "!");
            }
        }

    }

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser != null) {
            // Signed in
            if (mUsernameHeaderTV != null && mUserNavHeaderIV != null) {
                mUsernameHeaderTV.setText(currentUser.getDisplayName());
                if (currentUser.getPhotoUrl() != null) {
                    mUserPhotoUrl = currentUser.getPhotoUrl().toString();
                    Glide.with(this)
                            .load(mUserPhotoUrl)
                            .into(mUserNavHeaderIV);
                } else {
                    Glide.with(this)
                            .load(R.drawable.baseline_account_circle_48)
                            .into(mUserNavHeaderIV);
                }
            }
        } else {
            // Signed out
            if (mUsernameHeaderTV != null && mUserNavHeaderIV != null) {
                mUsernameHeaderTV.setText(R.string.dummy_user_name);
                Glide.with(this)
                        .load(R.drawable.sala_logo_grass)
                        .into(mUserNavHeaderIV);
            }
        }

    }

    private void writeNewUser(String userId, String displayName, String email) {
        User user = new User();
        user.setUserId(userId);
        user.setUsername(displayName);
        user.setEmail(email);
        mDatabaseRef.child(USERS).child(userId).setValue(user);
    }

    public void headerLogout(View view) {
        if (view != null) {
            signOut();
        }
    }

    private void signOut() {
        AuthUI.getInstance().signOut(MainActivity.this);
        mAssembliesList.clear();
    }

    private void activateDrawerItems() {
        // Handle navigation drawer click events
        if (mNavView != null) {
            mNavHeaderView = mNavView.getHeaderView(0);
            mNavLogoutBtn = mNavHeaderView.findViewById(R.id.logout_btn);
            if (mNavLogoutBtn != null) {
                mNavLogoutBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        headerLogout(v);
                    }
                });
            }
            mUsernameHeaderTV = mNavHeaderView.findViewById(R.id.username_nav_header_text_view);
            mUserNavHeaderIV = mNavHeaderView.findViewById(R.id.user_nav_header_img_view);
            updateUI(mAuth.getCurrentUser());
            mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    menuItem.setChecked(true);
                    if (mDrawer != null) {
                        mDrawer.closeDrawer(GravityCompat.START, true);
                    }
                    // TODO: Add code here to update the UI based on the item selected
                    // For example, swap UI fragments here
                    mFragment = null;
                    mBundle = new Bundle();
                    int position = menuItem.getItemId();
                    if (position == R.id.about_nav) {
                        mFragment = new AboutSalaFragment();
                        mAppBarTitle = mAboutTitle;
                        mAppBarImageUrl = mAboutBannerUrl;
                    } else if (position == R.id.assemblies_nav) {
                        mFragment = new AssembliesFragment();
                        mAppBarTitle = mAssemblyDateAndTheme;
                        mAboutBannerUrl = mAssemblyBackDrop;
//                        Toast.makeText(
//                                getApplicationContext(),
//                                "This will display AssembliesFragment",
//                                Toast.LENGTH_SHORT
//                        ).show();
                    } else if (position == R.id.program_nav) {
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display ProgramFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.musician_nav) {
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display LyricsFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.speaker_nav) {
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display SpeakerFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.help_often_nav) {
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display HelpOftenFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.live_better_nav) {
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display LiveBetterFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.chat_nav) {
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display ChatFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                        // TODO: Create intents for Instagram, Facebook and Twitter
                    } else if (position == R.id.insta_link_nav) {
                        Toast.makeText(
                                getApplicationContext(),
                                "This will open Instagram",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.facebook_link_nav) {
                        Toast.makeText(
                                getApplicationContext(),
                                "This will open Facebook",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.twitter_link_nav) {
                        Toast.makeText(
                                getApplicationContext(),
                                "This will open Twitter",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.site_link_nav) {
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display WebsiteFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.logout_nav) {
                        signOut();
                        return true;
                    }
                    if (mFragment != null) {
                        Fade fade = new Fade();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.content_frame, mFragment)
//                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null)
                                .commit();
                    }
                    mDrawer.closeDrawer(GravityCompat.START, true);
                    return true;
                }
            });
        }

    }

    private void activateSideBarItems() {
        // Handle two-pane side bar drawer click events
        mSideBarAdapter.setClickListener(new SideBarRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // TODO: Add code here to update the UI based on the item selected
                // For example, swap UI fragments here
                mFragment = null;
                mBundle = new Bundle();
                if (position == mSideBarAdapter.getItemId(0)) {
                    mFragment = new AboutSalaFragment();
                    mAppBarTitle = mAboutTitle;
                    mAppBarImageUrl = mAboutBannerUrl;
                } else if (position == mSideBarAdapter.getItemId(1)) {
                    mFragment = new AssembliesFragment();
                    mAppBarTitle = mAboutTitle;
                    mAppBarImageUrl = mAboutBannerUrl;
                } else if (position == mSideBarAdapter.getItemId(2)) {
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display ProgramFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(3)) {
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display LyricsFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(4)) {
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display SpeakerFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(5)) {
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display HelpOftenFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(6)) {
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display LiveBetterFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(7)) {
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display ChatFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                    // TODO: Create intents for Instagram, Facebook and Twitter
                } else if (position == mSideBarAdapter.getItemId(8)) {
                    Toast.makeText(
                            getApplicationContext(),
                            "This will open Instagram",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(9)) {
                    Toast.makeText(
                            getApplicationContext(),
                            "This will open Facebook",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(10)) {
                    Toast.makeText(
                            getApplicationContext(),
                            "This will open Twitter",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(11)) {
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display WebsiteFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(12)) {
                    signOut();
                }
                if (mFragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, mFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }

    private void setCollapsingToolbarBehavior() {
        // Handle transition from expanded to collapsed and in between
        if (mCollapsingToolbarLayout != null && mToolbar != null &&
                mAppBarLayout != null) {
            mCollapsingToolbarLayout.setTitleEnabled(true);
            mCollapsingToolbarLayout.setTitle(mAppBarTitle);
            mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    Fade fade = new Fade();
                    if (Math.abs(verticalOffset) == mAppBarLayout.getTotalScrollRange()) {
                        // Collapsed
                        TransitionManager.beginDelayedTransition
                                (mAppBarLayout, fade);
                        mToolbar.setVisibility(View.VISIBLE);
                    } else if (verticalOffset == 0) {
                        // Expanded
                        mToolbar.setVisibility(View.GONE);
                    } else {
                        // mid-scroll
                        TransitionManager.beginDelayedTransition(mToolbar, fade);
                    }
                }
            });
        }
        // Load Backdrop Image
        Glide.with(this)
                .load(mAppBarImageUrl)
                .into(collapsingToolbarBackDrop);
    }
}
