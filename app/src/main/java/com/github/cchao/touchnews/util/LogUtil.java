package com.github.cchao.touchnews.util;

import android.util.Log;

/**
 * Created by cchao on 2016/6/7.
 * E-mail:   cchao1024@163.com
 * Description: log工具
 */
public class LogUtil {
    public static final boolean DEBUG = true;

    public static void v(String tag, String mess) {
        if (DEBUG) {
            Log.v(tag, mess);
        }
    }

    public static void d(String tag, String mess) {
        if (DEBUG) {
            Log.d(tag, mess);
        }
    }

    public static void i(String tag, String mess) {
        if (DEBUG) {
            Log.i(tag, mess);
        }
    }

    public static void w(String tag, String mess) {
        if (DEBUG) {
            Log.w(tag, mess);
        }
    }

    public static void e(String tag, String mess) {
        if (DEBUG) {
            Log.e(tag, mess);
        }
    }

    public static void v(String mess) {
        if (DEBUG) {
            Log.v(getTag(), mess);
        }
    }

    public static void d(String mess) {
        if (DEBUG) {
            Log.d(getTag(), mess);
        }
    }

    public static void i(String mess) {
        if (DEBUG) {
            Log.i(getTag(), mess);
        }
    }

    public static void w(String mess) {
        if (DEBUG) {
            Log.w(getTag(), mess);
        }
    }

    public static void e(String mess) {
        if (DEBUG) {
            Log.e(getTag(), mess);
        }
    }

    private static String getTag() {
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
            .getStackTrace();
        String callingClass = "";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass
                    .lastIndexOf('.') + 1);
                break;
            }
        }
        return callingClass;
    }
}