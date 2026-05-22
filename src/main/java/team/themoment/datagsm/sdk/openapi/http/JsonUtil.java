package team.themoment.datagsm.sdk.openapi.http;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import kotlinx.datetime.LocalDate;
import team.themoment.datagsm.sdk.openapi.exception.DataGsmException;

import java.io.IOException;

public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        SimpleModule kotlinDatetimeModule = new SimpleModule();
        // kotlinx.datetime.LocalDate.parse(String) is synthetic — use fromEpochDays conversion instead
        kotlinDatetimeModule.addDeserializer(LocalDate.class, new StdDeserializer<LocalDate>(LocalDate.class) {
            @Override
            public LocalDate deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
                java.time.LocalDate javaDate = java.time.LocalDate.parse(p.getText());
                return LocalDate.Companion.fromEpochDays((int) javaDate.toEpochDay());
            }
        });

        OBJECT_MAPPER = new ObjectMapper()
                .registerModule(new KotlinModule.Builder().build())
                .registerModule(kotlinDatetimeModule)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T fromJson(String json, TypeReference<T> typeRef) {
        try {
            return OBJECT_MAPPER.readValue(json, typeRef);
        } catch (Exception e) {
            throw new DataGsmException("Failed to deserialize JSON: " + e.getMessage(), e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            throw new DataGsmException("Failed to deserialize JSON: " + e.getMessage(), e);
        }
    }

    public static String toJson(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new DataGsmException("Failed to serialize JSON: " + e.getMessage(), e);
        }
    }
}
