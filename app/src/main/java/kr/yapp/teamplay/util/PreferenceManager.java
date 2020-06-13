package kr.yapp.teamplay.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    public static final String PREFERENCES_NAME = "rebuild_preference";

    private static final String tokenKey = "tokenKey";

    private static final String userIdKey = "userId";


    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static void setTokenKey(Context context, String value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(tokenKey, value);
        editor.commit();
    }

    public static String getTokenKey(Context context) {
        SharedPreferences prefs = getPreferences(context);
        String value = prefs.getString(tokenKey, "잉?");
        return value;
    }

    public static void setUserId(Context context, String value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(userIdKey, value);
        editor.commit();
    }

    public static String getUserId(Context context) {

        SharedPreferences prefs = getPreferences(context);
        String value = prefs.getString(userIdKey, "잉?");
        return value;
    }
}
