package app.estat.web.contoller;

import java.lang.reflect.Field;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

public class Utils {

    public static Boolean assertPropertiesEquals(Object o1, Object o2, Class clazz) throws IllegalAccessException {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType().equals(List.class)) {
                continue;
            }
            field.setAccessible(Boolean.TRUE);
            if (field.getType().equals(Date.class)) {
                if ((field.get(o1) == null && field.get(o2) != null) ||
                        (field.get(o1) != null && field.get(o2) == null)) {
                    return false;
                } else if (!formatDate((Date) field.get(o1)).equals(formatDate((Date) field.get(o2)))) {
                    return false;
                }
            } else if ((field.get(o1) != null && field.get(o2) != null &&
                    !field.get(o1).equals(field.get(o2))) || ((field.get(o1) == null && field.get(o2) != null)
                        || (field.get(o1) != null && field.get(o2) == null))) {
                return false;
            }
        }

        return true;
    }

    private static String formatDate(Date date) {
        if (date == null) {
            return "null";
        }

        return new SimpleDateFormat("yyyy-mm-dd").format(date);
    }

}
