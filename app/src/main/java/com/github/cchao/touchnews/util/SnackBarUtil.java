package com.github.cchao.touchnews.util;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;


/**
 * Created by cchao on 2016/5/23.
 * E-mail:   cchao1024@163.com
 * Description: SnackBar tool
 */
public class SnackBarUtil {
    public static Snackbar showShort(@NonNull View view, @StringRes int resId) {

        Snackbar snackbar = Snackbar.make(view, resId, Snackbar.LENGTH_SHORT);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar showLong(@NonNull View view, @StringRes int resId) {

        Snackbar snackbar = Snackbar.make(view, resId, Snackbar.LENGTH_LONG);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar shoShort(@NonNull View view, @NonNull String string) {
        Snackbar snackbar = Snackbar.make(view, string, Snackbar.LENGTH_SHORT);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar showLong(@NonNull View view, @NonNull String string) {
        Snackbar snackbar = Snackbar.make(view, string, Snackbar.LENGTH_LONG);
        snackbar.show();
        return snackbar;
    }
}
