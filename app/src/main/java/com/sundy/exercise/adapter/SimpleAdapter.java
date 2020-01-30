package com.sundy.exercise.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sundy.exercise.R;
import com.sundy.exercise.entity.CommonEntity;

import java.util.List;

/**
 * 项目名称：MaterialDesign
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-30 15:02
 * 描述：
 */
public class SimpleAdapter extends BaseQuickAdapter<CommonEntity, BaseViewHolder> {

    public SimpleAdapter(@Nullable List<CommonEntity> data) {
        super(R.layout.bbl_bottom_sheet_item, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CommonEntity item) {
           helper.setText(R.id.bottom_sheet_dialog_item_name,item.getTitle());
    }
}
