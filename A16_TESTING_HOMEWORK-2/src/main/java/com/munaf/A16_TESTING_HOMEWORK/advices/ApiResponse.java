package com.munaf.A16_TESTING_HOMEWORK.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {


    @JsonFormat(pattern = "hh:mm:ss a dd-MM-yyyy") // a = AM or PM
    private LocalDateTime timestamp = LocalDateTime.now();
    private HttpStatus status;
    private T data;
    private ApiError apiError;

//    public ApiResponse() {
//        timestamp = LocalDateTime.now();
//    }

    public ApiResponse(T data, HttpStatus status) {
        this.data = data;
        this.status = status;
    }

    public ApiResponse(ApiError apiError, HttpStatus status) {
        this.apiError = apiError;
        this.status = status;
    }

//    public static <T> ApiResponse<T> success(T data, HttpStatus status) {
//        ApiResponse<T> response = new ApiResponse<>();
//        response.setData(data);
//        response.setStatus(status);
//        return response;
//    }
//
//    public static ApiResponse<?> error(Object error, HttpStatus status) {
//        ApiResponse<?> response = new ApiResponse<>();
//        response.setError(error);
//        response.setStatus(status);
//        return response;
//    }
}
