package com.sundy.exercise;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.sundy.common.base.BaseActivity;
import com.sundy.exercise.fgt.TypeFragment;
import com.sundy.exercise.fgt.WidgetFragment;
import com.sundy.exercise.view.RevealBackgroundView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements RevealBackgroundView.OnStateChangeListener {
    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";
    @BindView(R2.id.nav_view)
    NavigationView mNavView;
    @BindView(R2.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R2.id.toolbar2)
    Toolbar mToolbar;
    @BindView(R2.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R2.id.viewpager)
    ViewPager mViewPager;
    @BindView(R2.id.fab)
    FloatingActionButton mFab;
    @BindView(R2.id.main_content)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R2.id.vRevealBackground)
    RevealBackgroundView vRevealBackground;
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTitles;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setupRevealBackground(savedInstanceState);
        setUpDrawer();
        initNavigationView();
        initContent();
    }

    private void setupRevealBackground(Bundle savedInstanceState) {
        vRevealBackground.setFillPaintColor(0x00000000);
        vRevealBackground.setOnStateChangeListener(this);
        if (savedInstanceState == null) {
            // final int[] startingLocation = getIntent().getIntArrayExtra(ARG_REVEAL_START_LOCATION);
            final int[] startingLocation = {0, 0};
            vRevealBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    vRevealBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                    vRevealBackground.startFromLocation(startingLocation);
                    return true;
                }
            });
        } else {
            vRevealBackground.setToFinishedFrame();
        }
    }

    @Override
    public void onStateChange(int state) {
        Log.i("log_state", "onStateChange: =" + state);
    }



    private void initContent() {
        mFragments = new ArrayList<>();
        mFragments.add(WidgetFragment.newInstance());
        mFragments.add(TypeFragment.newInstance("军事"));
        mFragments.add(TypeFragment.newInstance("科技"));
        mFragments.add(TypeFragment.newInstance("娱乐"));

        mTitles = new ArrayList<>();
        mTitles.add("热点");
        mTitles.add("军事");
        mTitles.add("科技");
        mTitles.add("娱乐");

        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        adapter.setArguments(mFragments, mTitles);
        mViewPager.setAdapter(adapter);

        //设置TabLayout可滚动，保证Tab数量过多时也可正常显示
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //两个参数分别对应Tab未选中的文字颜色和选中的文字颜色
        mTabLayout.setTabTextColors(Color.WHITE, Color.RED);
        //绑定ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
        //设置TabLayout的布局方式（GRAVITY_FILL、GRAVITY_CENTER）
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //设置TabLayout的选择监听
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            //重复点击时回调
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
               // scrollToTop(mFragments.get(tab.getPosition()).getTypeList());
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(mCoordinatorLayout, "点我分享哦！", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 滚动到列表顶部
     *
     * @param recyclerView
     */
    private void scrollToTop(RecyclerView recyclerView) {
        StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
        int[] lastPositions = manager.findLastVisibleItemPositions(null);
        if (lastPositions[0] < 15) {
            recyclerView.smoothScrollToPosition(0);
        } else {
            recyclerView.scrollToPosition(0);
        }
    }

    private void setUpDrawer() {
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        //设置左上角显示三道横线
        toggle.syncState();

        mToolbar.setTitle("MdView");

//        设置Toolbar左上角图标
//        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mDrawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
    }

    private void initNavigationView() {
        //初始化NavigationView顶部head的icon和name
        ImageView icon = (ImageView) mNavView.getHeaderView(0).findViewById(R.id.nav_head_icon);
        icon.setImageResource(R.mipmap.ic_launcher);
        TextView name = (TextView) mNavView.getHeaderView(0).findViewById(R.id.nav_head_name);
        name.setText("VipOthershe");
        //设置NavigationView对应menu item的点击事情
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_item1:
                        break;
                    case R.id.nav_item2:
                        break;
                    case R.id.nav_set:
                        break;
                    case R.id.menu_share:
                        break;
                    case R.id.nav_about:
                        break;
                }
                //隐藏NavigationView
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public class TabPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;
        private List<String> titles;

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setArguments(List<Fragment> fragments, List<String> titles) {
            this.fragments = fragments;
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        //返回TabLayout对应Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_share:
                break;
            case R.id.menu_search:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
