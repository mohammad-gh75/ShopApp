package org.maktab36.finalproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
    private static final String PREF_LAST_RESULT_ID = "lastResultId";

    public static int getLastResultId(Context context) {
        SharedPreferences preferences = getDefaultSharedPreferences(context);
        return preferences.getInt(PREF_LAST_RESULT_ID,0);
    }

    public static void setLastResultId(Context context, int lastResultId) {
        SharedPreferences preferences = getDefaultSharedPreferences(context);
        preferences
                .edit()
                .putInt(PREF_LAST_RESULT_ID, lastResultId)
                .apply();;
    }

    private static SharedPreferences getDefaultSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }
}
