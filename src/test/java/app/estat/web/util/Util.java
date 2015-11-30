package app.estat.web.util;

import app.estat.web.model.entity.Entity;

import app.estat.web.model.response.EntityResponse;
import app.estat.web.model.response.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Util {

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

    public static String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    public static Response convertJsonStringToResponse(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Response response = mapper.readValue(jsonString, Response.class);
        return response;
    }

    public static <T extends EntityResponse> T
            convertJsonStringContentToEntityResponse(String jsonString, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T entityResponse = mapper.readValue(jsonString, clazz);
        return entityResponse;
    }

    public static String convertObjectToJsonString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public static <T extends EntityResponse> T convertJsonStringToEntityResponse(String jsonString, Class<T> clazz) throws IOException {
        Response response = convertJsonStringToResponse(jsonString);
        return convertJsonStringContentToEntityResponse(
                convertObjectToJsonString(response.getResponseContent()), clazz);
    }

}
