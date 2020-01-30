package com.sundy.exercise.aty;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.sundy.common.base.BaseActivity;
import com.sundy.exercise.R;
import com.sundy.exercise.adapter.SimpleAdapter;
import com.sundy.exercise.entity.CommonEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FloatActionButtonActivity extends BaseActivity {
    SimpleAdapter mAdapter;
    @BindView(R.id.fab_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.activity_float_action_button_fab)
    FloatingActionButton mFab ;
    private Snackbar snackbar;

    @Override
    protected int getLayoutId() {
        return R.layout.bbl_float_action_button_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initSnackbar();
        mAdapter=new SimpleAdapter(getData());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private List<CommonEntity> getData() {
        List<CommonEntity> items=new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            items.add(new CommonEntity("title"+i));
        }
        return items;
    }


    @Override
    public void setListeners() {
        super.setListeners();
        /**
         * 在代码中动态设置Fab的隐藏和显示，并且能够
         * 监听Fab的隐藏和显示的状态变化；
         */
//        mFab.show(new FloatingActionButton.OnVisibilityChangedListener() {
//            @Override
//            public void onShown(FloatingActionButton fab) {
//                super.onShown(fab);
//                Toast.makeText(FloatActionButtonActivity.this, "show", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mFab.hide(new FloatingActionButton.OnVisibilityChangedListener() {
//            @Override
//            public void onHidden(FloatingActionButton fab) {
//                super.onHidden(fab);
//                Toast.makeText(FloatActionButtonActivity.this, "hidden", Toast.LENGTH_SHORT).show();
//            }
//        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatActionButtonActivity.this, "click", Toast.LENGTH_SHORT).show();
                //显示snackbar
                snackbar.show();
            }
        });
    }

    private void initSnackbar() {
        //通过调用静态方法make创建Snackbar对象；
        snackbar = Snackbar.make(mRecyclerView, "MessageView", Snackbar.LENGTH_INDEFINITE);
        //给actionView的字体设置颜色
        snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
        snackbar.setAction("actionView", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatActionButtonActivity.this, "点击了actionview", Toast.LENGTH_SHORT).show();
            }
        });


        changeSnackbarBackground();
    }

    //改变MessageView字体颜色
    public void changeMessageViewTextColor(){
        ViewGroup view = (ViewGroup) snackbar.getView();
        SnackbarContentLayout childAt = (SnackbarContentLayout) view.getChildAt(0);
        //得到MessageView
        TextView messageView = (TextView) childAt.getChildAt(0);
        messageView.setTextColor(getResources().getColor(android.R.color.white));
    }

    //改变Snackbar的背景颜色
    public void changeSnackbarBackground() {
        View view = snackbar.getView();
        view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
    }
}
