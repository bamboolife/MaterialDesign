package com.sundy.exercise.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.sundy.common.base.BaseActivity;
import com.sundy.exercise.R;

import butterknife.BindView;

public class BehaviorActivity extends BaseActivity {
    @BindView(R.id.dependency)
    TextView dependency;

    @Override
    protected int getLayoutId() {
        return R.layout.bbl_behavior_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        dependency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewCompat.offsetTopAndBottom(dependency, 5);
            }
        });

    }
}
