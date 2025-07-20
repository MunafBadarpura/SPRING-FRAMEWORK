package com.munaf.A23_STOCK_TRADING_GRPC.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.ListValue;
import com.google.protobuf.NullValue;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Data
public class ResponseModel {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private int statusCode;
    private HttpStatus status;
    private Object data;


    public static Struct createResponse(HttpStatus status, Object data) {
//      Convert object to a Protobuf Value
        Value dataValue = convertToValue(data);

        return Struct.newBuilder()
                .putFields("status", Value.newBuilder().setStringValue(status.name()).build())
                .putFields("statusCode", Value.newBuilder().setNumberValue(status.value()).build())
                .putFields("data", dataValue)
                .build();
    }



    public static Value convertToValue(Object data) {
        if (data == null) {
            return Value.newBuilder().setNullValue(NullValue.NULL_VALUE).build();
        }

        if (data instanceof String) {
            return Value.newBuilder().setStringValue((String) data).build();
        }

        if (data instanceof Number) {
            return Value.newBuilder().setNumberValue(((Number) data).doubleValue()).build();
        }

        if (data instanceof Boolean) {
            return Value.newBuilder().setBoolValue((Boolean) data).build();
        }

        if (data instanceof Map<?,?>) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) data;
            Struct.Builder structBuilder = Struct.newBuilder();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                structBuilder.putFields(entry.getKey(), convertToValue(entry.getValue()));
            }
            return Value.newBuilder().setStructValue(structBuilder.build()).build();
        }

        if (data instanceof List<?>) {
            ListValue.Builder listBuilder = ListValue.newBuilder();
            for (Object item : (List<?>) data) {
                listBuilder.addValues(convertToValue(item));
            }
            return Value.newBuilder().setListValue(listBuilder.build()).build();
        }

        // Fallback: convert POJO to Map using Jackson
        Map<String, Object> pojoAsMap = objectMapper.convertValue(data, Map.class);
        return convertToValue(pojoAsMap);
    }
}
