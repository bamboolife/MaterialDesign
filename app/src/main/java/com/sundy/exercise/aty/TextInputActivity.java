package com.sundy.exercise.aty;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sundy.common.base.BaseActivity;
import com.sundy.exercise.R;

import butterknife.BindView;

public class TextInputActivity extends BaseActivity {

    private static final int MAX_LENGTH = 6;
    @BindView(R.id.username_inputlayout)
     TextInputLayout mUserNameTextInputLayout ;
    @BindView(R.id.password_inputlayout)
     TextInputLayout mPasswordTextInputLayout;
    @BindView(R.id.et_username)
     EditText mUserNameEditText ;
    @BindView(R.id.et_password)
     TextInputEditText mPasswordEditText ;
    @Override
    protected int getLayoutId() {
        return R.layout.bbl_text_input_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mUserNameTextInputLayout.setCounterEnabled(true);
        mUserNameTextInputLayout.setCounterMaxLength(MAX_LENGTH);


        mUserNameTextInputLayout.setErrorTextAppearance(R.style.TextInputErrorStyle);
        mPasswordTextInputLayout.setErrorTextAppearance(R.style.TextInputErrorStyle);

        mUserNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.length();
                if(length> MAX_LENGTH ){
                    mUserNameTextInputLayout.setError("Max length is :"+ MAX_LENGTH);
                }else{
                    mUserNameTextInputLayout.setErrorEnabled(false);
                }
            }
        });

        mPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.length();
                if(length> MAX_LENGTH ){
                    mPasswordTextInputLayout.setError("Max length is :"+ MAX_LENGTH);
                }else{
                    mPasswordTextInputLayout.setErrorEnabled(false);
                }
            }
        });
    }
}
