package beer.brew.vendingmachine.util;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GsonUtils {

    private static final String TAG = "GsonUtils";

    public static <T extends Serializable> T parse(String json, Class<T> clz) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        T bean = null;
        try {
            bean = new Gson().fromJson(json, clz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static String toJsonString(Object obj) {
        if (obj == null) {
            return null;
        }
        return new Gson().toJson(obj);
    }

    public static <T extends Serializable> List<T> parseToList(String json, Class<T> clz) {
        List<T> result = null;
        if (TextUtils.isEmpty(json)) {
            return result;
        }

        try {
            result = new ArrayList<>();
            JSONArray jArray = new JSONArray(json);
            int length = jArray.length();
            for (int i = 0; i < length; i++) {
                String str = jArray.getString(i);
                T obj = parse(str, clz);
                result.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isArray(String json) {
        boolean result = false;
        if (TextUtils.isEmpty(json)) {
            return false;
        }
        try {
            JSONArray jsonArray = new JSONArray(json);
            result = true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

}
