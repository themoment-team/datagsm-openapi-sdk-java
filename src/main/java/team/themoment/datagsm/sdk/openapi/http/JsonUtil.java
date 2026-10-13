package team.themoment.datagsm.sdk.openapi.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
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
        // kotlinx.datetime.LocalDate.toString() returns ISO-8601 (e.g. "2026-06-24")
        kotlinDatetimeModule.addSerializer(LocalDate.class, new StdSerializer<LocalDate>(LocalDate.class) {
            @Override
            public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
                gen.writeString(value.toString());
            }
        });

        OBJECT_MAPPER = new ObjectMapper()
                .registerModule(new KotlinModule.Builder().build())
                .registerModule(kotlinDatetimeModule)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                // 서버에 새 enum 값(예: 프로젝트 카테고리)이 추가돼도 역직렬화가 실패하지 않도록 null로 읽는다.
                .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
                // 요청 본문에서 null 필드는 보내지 않는다. 서버는 생략된 필드를 "기존 값 유지"로 해석한다.
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 컬렉션 안의 null(모르는 enum 값 포함)은 버려 List/Set에 null 원소가 섞이지 않게 한다.
        OBJECT_MAPPER.setDefaultSetterInfo(JsonSetter.Value.forContentNulls(Nulls.SKIP));
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
