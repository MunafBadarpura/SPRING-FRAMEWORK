package com.munaf.stock_tading_client.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {

    private int statusCode;
    private HttpStatus status;
    private Object data;


    public static ResponseModel convertStructToResponseModel(Struct struct) {
        ResponseModel responseModel = new ResponseModel();

        // Extract and convert "statusCode"
        if (struct.containsFields("statusCode")) {
            responseModel.setStatusCode((int) struct.getFieldsOrThrow("statusCode").getNumberValue());
        }

        // Extract and convert "status"
        if (struct.containsFields("status")) {
            String statusStr = struct.getFieldsOrThrow("status").getStringValue();
            responseModel.setStatus(HttpStatus.valueOf(statusStr));
        }

        // Extract and convert "data"
        if (struct.containsFields("data")) {
            Value dataValue = struct.getFieldsOrThrow("data");
            Object dataObj = getValue(dataValue);
            responseModel.setData(dataObj);
        }

        return responseModel;
    }

    private static Object getValue(Value value) {
        switch (value.getKindCase()) {
            case STRING_VALUE:
                return value.getStringValue();
            case NUMBER_VALUE:
                return value.getNumberValue();
            case BOOL_VALUE:
                return value.getBoolValue();
            case STRUCT_VALUE:
                Map<String, Object> map = new HashMap<>();
                for (Map.Entry<String, Value> entry : value.getStructValue().getFieldsMap().entrySet()) {
                    map.put(entry.getKey(), getValue(entry.getValue()));
                }
                return map;
            case LIST_VALUE:
                return value.getListValue().getValuesList().stream()
                        .map(ResponseModel::getValue)
                        .toList();
            case NULL_VALUE:
            case KIND_NOT_SET:
            default:
                return null;
        }
    }


}
