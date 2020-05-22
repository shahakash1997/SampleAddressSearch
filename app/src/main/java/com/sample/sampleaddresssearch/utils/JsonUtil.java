package com.sample.sampleaddresssearch.utils;

import com.google.gson.Gson;
import com.sample.sampleaddresssearch.config.Config;

public class JsonUtil {
    private static final String TAG = Config.logger + JsonUtil.class.getSimpleName();
    private static Gson M_GSON = new Gson();

    public static String jsonify(Object object) {
        try {
            return M_GSON.toJson(object);
        } catch (Exception e) {
            Tracer.debug(TAG, " jsonify " + e);
            return "";
        }
    }

    public static Object objectify(String jsonString, Class<?> cls) {
        try {
            return M_GSON.fromJson(jsonString, cls);
        } catch (Exception e) {
            Tracer.debug(TAG, " objectify " + " " + e);
            return null;
        }
    }
}
