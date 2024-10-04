package com.krishav.completesetup.completesetup.advices;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private T data;
    private LocalDateTime timeStamp;

    public ApiResponse()
    {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data)
    {
        this();
        this.data=data;
    }
}
