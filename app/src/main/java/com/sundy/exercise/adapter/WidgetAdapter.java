package com.sundy.exercise.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sundy.exercise.R;
import com.sundy.exercise.entity.Widget;

import java.util.List;

/**
 * 项目名称：MaterialDesign
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-30 10:50
 * 描述：
 */
public class WidgetAdapter extends BaseQuickAdapter<Widget, BaseViewHolder> {

    public WidgetAdapter(@Nullable List<Widget> data) {
        super(R.layout.bbl_fgt_widget_item_layout, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Widget item) {
          helper.setText(R.id.tv_title,item.getName());
    }
}
