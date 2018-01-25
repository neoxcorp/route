package com.route.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class Permissions {

    public static boolean checkPermission(Activity activity, int REQUEST_CODE, String permission) {
        if (isGranted(activity, permission)) {
            return true;
        } else {
            request(activity, REQUEST_CODE, permission);
            return false;
        }
    }

    public static boolean isGranted(Context context, String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    public static void request(Activity activity, int REQUEST_CODE, String permission) {
        requestSeveral(activity, REQUEST_CODE, new String[]{ permission });
    }

    public static void requestSeveral(Activity activity, int REQUEST_CODE, String[] permissions) {
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE);
    }

}
