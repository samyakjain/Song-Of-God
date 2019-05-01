package song.of.god.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import song.of.god.BuildConfig;

public class UtilityHelper {

    private static int windowWidth;
    private static int windowHeight;

    public static boolean isDebugMode() {
        return BuildConfig.BUILD_TYPE.equals("debug");
    }

    public static String getVersionName(Context mContext) {
        PackageManager manager = mContext.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            return info.versionName;
            //versionCode = (int) PackageInfoCompat.getLongVersionCode(info);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
           return  "Unknown";
        }
    }

    public static int getScreenResolution(@NonNull Context context, @NonNull String widthOrHeight) {
        if (windowHeight == 0 && windowWidth == 0) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);
            windowWidth = metrics.widthPixels;
            windowHeight = metrics.heightPixels;
        }

        if (widthOrHeight.equals("width")) {
            return windowWidth;
        }
        return windowHeight;
    }

}
