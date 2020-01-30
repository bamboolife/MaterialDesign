package com.sundy.exercise.aty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.sundy.common.base.BaseActivity;
import com.sundy.exercise.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BottomDialogActivity extends BaseActivity {
    @BindView(R.id.bottomdialog)
    LinearLayout mBottomDialog;
    private BottomSheetBehavior<LinearLayout> behavior;

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter myRecyclerAdapter;
    private BottomSheetBehavior<RecyclerView> recyclerViewBottomSheetBehavior;


    @Override
    protected int getLayoutId() {
        return R.layout.bbl_bottom_dialog_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        //---------------加载LinearLayout菜单布局-------------

        //从底部菜单中获得与之关联的BottomSheetBehavior
        behavior = BottomSheetBehavior.from(mBottomDialog);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                System.out.println("bottomSheet = [" + bottomSheet + "], newState = [" + newState + "]");
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                System.out.println("bottomSheet = [" + bottomSheet + "], slideOffset = [" + slideOffset + "]");
            }
        });

        //----------------加载RecyclerView布局----------------------
//        mRecyclerView = (RecyclerView) findViewById(R.id.bottom_dialog_recyclerview);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        myRecyclerAdapter = new MyRecyclerViewAdapter();
//        handlerData();
//        mRecyclerView.setAdapter(myRecyclerAdapter);
//
//        //监听底部菜单的状态变化
//        recyclerViewBottomSheetBehavior = BottomSheetBehavior.from(mRecyclerView);
//        recyclerViewBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                System.out.println("bottomSheet = [" + bottomSheet + "], newState = [" + newState + "]");
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                System.out.println("bottomSheet = [" + bottomSheet + "], slideOffset = [" + slideOffset + "]");
//            }
//        });
    }

    private void handlerData() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            list.add(i + "");
        }
        myRecyclerAdapter.setData(list);
    }

    /**
     * 展开bottomDialog
     */
    private void expandBottomSheet() {
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    /**
     * 折叠bottomDialog
     * 如果底部菜单中没有设置app：behavior_peekHeight 将完全隐藏，与STATE_HIDDEN效果一样
     */
    private void collapsedBottomSheet() {
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    /**
     * 隐藏bottomDialog
     * 如果此功能有效果，必须在布局中设置app：behavior_hideable = "true"
     * 如果不设置或者设置为false 调用此方法没有任何效果
     */
    private void hideableBottomSheet() {
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }


    public static class MyRecyclerViewAdapter extends RecyclerView.Adapter<BottomDialogViewHolder> {

        private List<String> data;

        public void setData(List<String> list) {
            this.data = list;
        }

        @Override
        public BottomDialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //注意这里的第二个参数必须为null，如果为parent时，由于嵌套的作用，只会显示一个item项
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bbl_bottom_sheet_item, null, false);
            return new BottomDialogViewHolder(view);
        }

        @Override
        public void onBindViewHolder(BottomDialogViewHolder holder, int position) {
            holder.name.setText("position:" + data.get(position));
            holder.index.setText(position + "");
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }
    }

    public static class BottomDialogViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView index;

        public BottomDialogViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.bottom_sheet_dialog_item_name);
            index = (TextView) itemView.findViewById(R.id.bottom_sheet_dialog_item_index);
        }
    }
}
