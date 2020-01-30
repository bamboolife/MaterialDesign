package com.sundy.exercise.fgt;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sundy.common.base.BaseFragment;
import com.sundy.exercise.R;
import com.sundy.exercise.adapter.WidgetAdapter;
import com.sundy.exercise.aty.BehaviorActivity;
import com.sundy.exercise.aty.BottomSheetDialogActivity;
import com.sundy.exercise.aty.CollapsingToolbarLayoutActivity;
import com.sundy.exercise.aty.CoordinatorLayoutActivity;
import com.sundy.exercise.aty.FloatActionButtonActivity;
import com.sundy.exercise.aty.SwipeDismissBehaviorActivity;
import com.sundy.exercise.aty.TextInputActivity;
import com.sundy.exercise.aty.ToolbarActivity;
import com.sundy.exercise.entity.Widget;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WidgetFragment extends BaseFragment {
    WidgetAdapter mAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;


    public static WidgetFragment newInstance() {
        WidgetFragment fragment = new WidgetFragment();
//        Bundle args = new Bundle();
//        args.putString("type", type);
//        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.bbl_fgt_widget_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
     mAdapter=new WidgetAdapter(getData());
     mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
     mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
     mRecyclerView.setAdapter(mAdapter);
    }

    private List<Widget> getData() {
        List<Widget> items=new ArrayList<>();
        items.add(new Widget("Toolbar示例", ToolbarActivity.class));
        items.add(new Widget("BottomSheetdialog示例", BottomSheetDialogActivity.class));
        items.add(new Widget("Coordinatorlayout", CoordinatorLayoutActivity.class));
        items.add(new Widget("fab", FloatActionButtonActivity.class));
        items.add(new Widget("Coordinatorlayout_CollapsingToolbarLayout", CollapsingToolbarLayoutActivity.class));
        items.add(new Widget("Swipedismissbehavior", SwipeDismissBehaviorActivity.class));
        items.add(new Widget("Textinputlayout", TextInputActivity.class));
        items.add(new Widget("Behavior", BehaviorActivity.class));
        items.add(new Widget("Cardview", ToolbarActivity.class));
                return items;
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                  Widget widget= (Widget) adapter.getItem(position);
                Intent intent=new Intent(mContext,widget.getClazz());
                startActivity(intent);
            }
        });
    }


}
