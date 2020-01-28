package webapp.kuga.util;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String bean2String(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convert(String str, Class<T> valueType) {
        try {
            return mapper.readValue(str, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convert(InputStream is, Class<T> valueType) {
        try {
            return mapper.readValue(is, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}