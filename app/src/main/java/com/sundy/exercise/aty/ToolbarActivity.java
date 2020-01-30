package com.sundy.exercise.aty;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.sundy.common.base.BaseActivity;
import com.sundy.exercise.R;

import butterknife.BindView;

public class ToolbarActivity extends BaseActivity {
    @BindView(R.id.atoolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.bbl_toolbar_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
      mToolbar.setTitle("测试");
      setSupportActionBar(mToolbar);
      mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
      });
      mToolbar.setLogo(R.mipmap.ic_launcher_round);
     // mToolbar.inflateMenu(R.menu.menu_main);
      mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
          @Override
          public boolean onMenuItemClick(MenuItem item) {

              return false;
          }
      });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}
