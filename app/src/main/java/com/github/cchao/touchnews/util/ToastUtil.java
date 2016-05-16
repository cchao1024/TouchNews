package com.github.cchao.touchnews.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.github.cchao.touchnews.BaseApplication;

/**
 * Created by cchao on 2016/4/22.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class ToastUtil {
        private static Toast toast;
        private static View view;

        private ToastUtil ( ) {
        }

        @SuppressLint ( "ShowToast" )
        private static void getToast ( Context context ) {
                if ( toast == null ) {
                        toast = new Toast ( context );
                }
                if ( view == null ) {
                        view = Toast.makeText ( context, "", Toast.LENGTH_SHORT ).getView ( );
                }
                toast.setView ( view );
        }

        public static void showShortToast ( Context context, CharSequence msg ) {
                if ( context == null ) {
                        showToast ( BaseApplication.getContext ( ), msg, Toast.LENGTH_SHORT );
                } else {
                        showToast ( context.getApplicationContext ( ), msg, Toast.LENGTH_SHORT );
                }
        }

        public static void showShortToast ( Context context, int resId ) {
                if ( context == null ) {
                        showToast ( BaseApplication.getContext ( ), resId, Toast.LENGTH_SHORT );
                } else {
                        showToast ( context.getApplicationContext ( ), resId, Toast.LENGTH_SHORT );
                }
        }

        public static void showLongToast ( CharSequence msg ) {
                showToast ( BaseApplication.getContext ( ), msg, Toast.LENGTH_LONG );
        }

        public static void showLongToast ( Context context, CharSequence msg ) {
                if ( context == null ) {
                        showToast ( BaseApplication.getContext ( ), msg, Toast.LENGTH_LONG );
                } else {
                        showToast ( context.getApplicationContext ( ), msg, Toast.LENGTH_LONG );
                }
        }

        public static void showLongToast ( Context context, int resId ) {
                if ( context == null ) {
                        showToast ( BaseApplication.getContext ( ), resId, Toast.LENGTH_LONG );
                } else {
                        showToast ( context.getApplicationContext ( ), resId, Toast.LENGTH_LONG );
                }
        }

        private static void showToast ( Context context, CharSequence msg, int duration ) {
                try {
                        getToast ( context );
                        toast.setText ( msg );
                        toast.setDuration ( duration );
                        toast.show ( );
                } catch ( Exception e ) {
                        LogUtils.d ( e.getMessage ( ) );
                }
        }

        private static void showToast ( Context context, int resId, int duration ) {
                try {
                        if ( resId == 0 ) {
                                return;
                        }
                        getToast ( context );
                        toast.setText ( resId );
                        toast.setDuration ( duration );
                        toast.show ( );
                } catch ( Exception e ) {
                        LogUtils.d ( e.getMessage ( ) );
                }
        }

}
