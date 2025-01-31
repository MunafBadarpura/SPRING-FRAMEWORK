package com.munaf.SPRING_SECURITY_PRAC.advices;


//hh: Hour in 12-hour format (use HH for 24-hour format with AM/PM).
//mm: Minutes.
//ss: Seconds.
//dd: Day of the month.
//MM: Month.
//yyyy: Calendar year.

import lombok.Data;

import java.time.LocalDateTime;

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
