package com.sundy.exercise.aty;

import android.os.Bundle;
import android.view.View;

import com.sundy.common.base.BaseActivity;
import com.sundy.exercise.R;
import com.sundy.exercise.fgt.DemoBottomSheetDialogFragment;

public class BottomDialogFragmentActivity extends BaseActivity {

    private DemoBottomSheetDialogFragment demoBottomSheetDialogFragment ;
    @Override
    protected int getLayoutId() {
        return R.layout.bbl_bottom_dialog_fragment_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        demoBottomSheetDialogFragment = DemoBottomSheetDialogFragment.newInstance();
        //显示dialogFragment
        showBottomSheetDialogFragment();
    }

    public void showdialog(View view) {
        showBottomSheetDialogFragment();
    }
    public void hidedialog(View view) {
        //隐藏dialogFragment
        hideBottomSheetDialogFragment();
    }

    /**
     * 显示BottomSheetDialogFragment
     */
    private void hideBottomSheetDialogFragment() {
        if (demoBottomSheetDialogFragment == null) {
            demoBottomSheetDialogFragment.dismiss();
        }
    }

    /**
     * 显示BottomSheetDialogFragment
     */
    private void showBottomSheetDialogFragment() {
        demoBottomSheetDialogFragment.show(getSupportFragmentManager(),"bottomSheetDialogFragment");
    }

}
