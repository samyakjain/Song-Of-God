package song.of.god.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import timber.log.Timber;

import static song.of.god.application.AppConstants.SP_FILE_KEY;

public class SharedPreferenceHelper {

    private static SharedPreferences getSharedPreferencesObject(@NonNull Context context) {
        return context.getSharedPreferences(SP_FILE_KEY, Context.MODE_PRIVATE);
    }

    public static long getLongFromSharedPreferences(@NonNull Context context, String key) {
        return getSharedPreferencesObject(context).getLong(key, 0);
    }

    public static int getIntFromSharedPreferences(@NonNull Context context, String key) {
        return getSharedPreferencesObject(context).getInt(key, 0);
    }

    @Nullable
    public static String getStringFromSharedPreference(@NonNull Context context, String key) {
        return getSharedPreferencesObject(context).getString(key, "0");
    }

    public static boolean getBooleanFromSharedPreference(@NonNull Context context, String key) {
        return getSharedPreferencesObject(context).getBoolean(key, false);
    }

    public static boolean getBooleanFromSharedPreference(@NonNull Context context, String key, boolean defaultValue) {
        return getSharedPreferencesObject(context).getBoolean(key, defaultValue);
    }

    @SuppressLint("ApplySharedPref")
    public static <T> void putInSharedPreference(@NonNull Context context, String key, T t) {
        Timber.i("putting in sharedPref " + key);
        SharedPreferences sharedPref = context.getSharedPreferences(SP_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (t instanceof String) {
            editor.putString(key, (String) t);
        } else if (t instanceof Long) {
            editor.putLong(key, (Long) t);
        } else if (t instanceof Integer) {
            editor.putInt(key, (Integer) t);
        } else if (t instanceof Boolean) {
            editor.putBoolean(key, (Boolean) t);
        } else if (t instanceof Float) {
            editor.putFloat(key, (Float) t);
        }
        editor.commit();
    }

}
