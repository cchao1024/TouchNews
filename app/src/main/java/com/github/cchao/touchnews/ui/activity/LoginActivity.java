package com.github.cchao.touchnews.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.github.cchao.touchnews.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by cchao on 2016/8/24.
 * E-mail:   cchao1024@163.com
 * Description: 登录框
 */
public class LoginActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.editTextName)
    EditText mEditTextName;
    @Bind(R.id.textInputLayoutName)
    TextInputLayout mTextInputLayoutName;
    @Bind(R.id.editTextPassword)
    EditText mEditTextPassword;
    @Bind(R.id.textInputLayoutPassword)
    TextInputLayout mTextInputLayoutPassword;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.user_login);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mEditTextName = (EditText) findViewById(R.id.editTextName);
        mTextInputLayoutName.setErrorEnabled(true);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);
        mTextInputLayoutPassword.setErrorEnabled(true);

        mEditTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkName(s.toString(), false);
            }
        });

        mEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkPswd(s.toString(), false);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.buttonLogin)
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLogin) {
            hideKeyBoard();
            if (!checkName(mEditTextName.getText(), true))
                return;
            if (!checkPswd(mEditTextPassword.getText(), true))
                return;
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean checkPswd(CharSequence pswd, boolean isLogin) {
        if (TextUtils.isEmpty(pswd)) {
            if (isLogin) {
                mTextInputLayoutPassword.setError(getString(R.string.error_pswd_empty));
                return false;
            }
        } else {
            mTextInputLayoutPassword.setError(null);
        }
        return true;
    }

    private boolean checkName(CharSequence name, boolean isLogin) {
        if (TextUtils.isEmpty(name)) {
            if (isLogin) {
                mTextInputLayoutName.setError(getString(R.string.error_login_empty));
                return false;
            }
        } else {
            mTextInputLayoutName.setError(null);
        }
        return true;
    }

    private void hideKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
