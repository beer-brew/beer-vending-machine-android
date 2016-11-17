package beer.brew.vendingmachine.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.lang.reflect.Type;

public class JsonUtils {

    private static Gson gson = createGson();

    public static <T> T fromJson(Reader reader, Type type) {
        return gson.fromJson(reader, type);
    }

    public static <T> T fromJson(Reader reader, Class<T> type) {
        return gson.fromJson(reader, type);
    }

    public static <T> T fromJson(String jsonString, Type type) {
        return gson.fromJson(jsonString, type);
    }

    public static <T> T fromJson(String jsonString, Class<T> type) {
        return gson.fromJson(jsonString, type);
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    private static Gson createGson() {
        return new GsonBuilder().create();
    }
}