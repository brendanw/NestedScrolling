package me.brendanweinstein.nestedscroll;

import android.util.Log;

import java.util.HashMap;


public class SLog {
    static HashMap<String, String> logMap = new HashMap<>();

    public static void d(String TAG, String log) {
        if (logMap.containsKey(TAG) && !logMap.get(TAG).equals(log)) {
            Log.d(TAG, log);
        }
        logMap.put(TAG, log);
    }
}
