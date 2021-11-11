package base;

import com.google.gson.Gson;

/**
 * 用于将对象转为JSON
 */
public class ToJson {

    private static Gson gson = new Gson();

    public static String Conver(Object object){
        return gson.toJson(object);
    }

}
