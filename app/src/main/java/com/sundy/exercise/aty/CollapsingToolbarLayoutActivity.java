package com.sundy.exercise.aty;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;


import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.sundy.common.base.BaseActivity;
import com.sundy.exercise.R;
import com.sundy.exercise.adapter.SimpleAdapter;
import com.sundy.exercise.entity.CommonEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CollapsingToolbarLayoutActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsingToolbarLayoutRecycler)
    RecyclerView mRecycler;
    @BindView(R.id.appbarlayout)
    AppBarLayout mAppBarLayout;
    SimpleAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.bbl_collapsing_toolbar_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initToolbar();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new SimpleAdapter(getData());
        mRecycler.setAdapter(mAdapter);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置actionbar上的返回箭头距离标题的具体
        mToolbar.setContentInsetStartWithNavigation(0);
    }

    private List<CommonEntity> getData() {
        List<CommonEntity> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            items.add(new CommonEntity("title" + i));
        }
        return items;
    }

    @Override
    public void setListeners() {
        super.setListeners();
        /**
         * 监听appBarLayout的折叠和展开状态，在初始化时就会调用一次
         */
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //appBarLayout.getHeight()   appbarlayout的总高度
                //appBarLayout.getTotalScrollRange()  appbar能够滚动的高度
                //verticalOffset 垂直的滚动距离，往上滚是负值 初始化时为0
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    //往上滚动-----直到隐藏appbar
                }else{

                }
                System.out.println("appBarheight="+appBarLayout.getHeight()+",getTotalScrollRange = [" + appBarLayout.getTotalScrollRange() + "], verticalOffset = [" + verticalOffset + "]");
            }
        });

    }
}
