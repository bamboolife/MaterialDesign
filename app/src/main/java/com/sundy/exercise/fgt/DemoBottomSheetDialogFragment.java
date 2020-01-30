package com.sundy.exercise.fgt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.sundy.exercise.R;

/**
 * Created by serenitynanian on 2017/5/26.
 */

public class DemoBottomSheetDialogFragment extends BottomSheetDialogFragment {

    public static DemoBottomSheetDialogFragment newInstance() {

        Bundle args = new Bundle();

        DemoBottomSheetDialogFragment fragment = new DemoBottomSheetDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //填充自己的想要的布局
        View view = inflater.inflate(R.layout.bbl__bottom_sheet_layout, container, false);
        return view;
    }
}
