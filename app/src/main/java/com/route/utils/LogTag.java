package com.route.utils;

import android.support.annotation.NonNull;
import android.util.Log;

public class LogTag {

    private static final String TAG = "LOG_TAG";
    private static final String DIVIDER = ":\n";

    public static void i(@NonNull Object object, @NonNull String msg) {
        Log.i(TAG, object.getClass().getSimpleName() + DIVIDER + msg);
    }

    public static void i(@NonNull String msg) {
        Log.i(TAG, msg);
    }

    public static void d(@NonNull Object object, @NonNull String msg) {
        Log.d(TAG, object.getClass().getSimpleName() + DIVIDER + msg);
    }

    public static void d(@NonNull String msg) {
        Log.d(TAG, msg);
    }

    public static void e(@NonNull Object object, @NonNull String msg) {
        Log.e(TAG, object.getClass().getSimpleName() + DIVIDER + msg);
    }

    public static void e(@NonNull String msg) {
        Log.e(TAG, msg);
    }

    public static void w(@NonNull Object object, @NonNull String msg) {
        Log.w(TAG, object.getClass().getSimpleName() + DIVIDER + msg);
    }

    public static void w(@NonNull String msg) {
        Log.w(TAG, msg);
    }

    public static void v(@NonNull Object object, @NonNull String msg) {
        Log.v(TAG, object.getClass().getSimpleName() + DIVIDER + msg);
    }

    public static void v(@NonNull String msg) {
        Log.v(TAG, msg);
    }

    public static void wtf(@NonNull Object object, @NonNull String msg) {
        Log.wtf(TAG, object.getClass().getSimpleName() + DIVIDER + msg);
    }

    public static void wtf(@NonNull String msg) {
        Log.wtf(TAG, msg);
    }
}
