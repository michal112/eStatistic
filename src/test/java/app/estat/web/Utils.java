package app.estat.web;

import app.estat.web.model.entity.Entity;

import java.lang.reflect.Field;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

public class Utils {

    public static Boolean assertPropertiesEquals(Object o1, Object o2, Class clazz) throws IllegalAccessException {
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(Boolean.TRUE);
            if (field.getType().equals(List.class)) {
                continue;
            } else if ((field.get(o1) == null && field.get(o2) != null) ||
                    (field.get(o1) != null && field.get(o2) == null)) {
                return false;
            } else if ((field.get(o1) != null && field.get(o2) != null)) {
                if (field.getType().equals(Date.class)) {
                    if (!(formatDate((Date) field.get(o1)).equals(formatDate((Date) field.get(o2))))) {
                        return false;
                    }
                } else if (field.getType().getAnnotatedInterfaces().length > 0 &&
                        field.getType().getAnnotatedInterfaces()[0].getType().equals(Entity.class)) {
                    if (!(((Entity) field.get(o1)).getId().equals(((Entity) field.get(o2)).getId()))) {
                        return false;
                    }
                } else if (!(field.get(o1).equals(field.get(o2)))) {
                    return false;
                }
            }
        }

        return true;
    }

    private static String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-mm-dd").format(date);
    }

}
