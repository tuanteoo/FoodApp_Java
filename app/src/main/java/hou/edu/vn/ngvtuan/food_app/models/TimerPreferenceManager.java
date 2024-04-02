package hou.edu.vn.ngvtuan.food_app.models;

import android.content.Context;
import android.content.SharedPreferences;

public class TimerPreferenceManager {
    private static final String TIMER_PREFS = "timer_prefs";

    public static void saveTimerFinishedState(Context context, String key, boolean timerFinished) {
        SharedPreferences.Editor editor = context.getSharedPreferences(TIMER_PREFS, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, timerFinished);
        editor.apply();
    }

    public static boolean getTimerFinishedState(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(TIMER_PREFS, Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false); // Default value false means timer not finished
    }

    public static void removeTimerFinishedState(Context context, String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(TIMER_PREFS, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.apply();
    }
}