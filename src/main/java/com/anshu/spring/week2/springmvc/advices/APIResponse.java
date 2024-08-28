package com.anshu.spring.week2.springmvc.advices;

import com.anshu.spring.week2.springmvc.Exceptions.ApiError;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class APIResponse <T>{

    private T data;
    private ApiError apiError;

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timestamp;

    public APIResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public APIResponse(T data) {
        this();
        this.data = data;
    }

    public APIResponse(ApiError apiError) {
        this();
        this.apiError = apiError;
    }
}
