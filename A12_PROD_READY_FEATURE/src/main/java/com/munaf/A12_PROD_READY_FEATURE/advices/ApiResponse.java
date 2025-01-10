package com.munaf.A12_PROD_READY_FEATURE.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//hh: Hour in 12-hour format (use HH for 24-hour format with AM/PM).
//mm: Minutes.
//ss: Seconds.
//dd: Day of the month.
//MM: Month.
//yyyy: Calendar year.

@Data
public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private T data;
    private ApiError error;


    public ApiResponse(){
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }
}
