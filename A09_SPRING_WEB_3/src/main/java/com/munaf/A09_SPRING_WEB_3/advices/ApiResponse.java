package com.munaf.A09_SPRING_WEB_3.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

//hh: Hour in 12-hour format (use HH for 24-hour format with AM/PM).
//mm: Minutes.
//ss: Seconds.
//dd: Day of the month.
//MM: Month.
//yyyy: Calendar year.

@Data
public class ApiResponse<T> {

    @JsonFormat(pattern = "hh:mm:ss dd/MM/yyyy")
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
