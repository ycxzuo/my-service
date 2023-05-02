package com.yczuoxin.myservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ResponseResult<T> {

    private int code;

    private String message;

    private T data;

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(HttpStatus.OK.value(), "OK", data);
    }
    public static <T> ResponseResult<T> error(HttpStatus status) {
        return new ResponseResult<>(status.value(), status.name(), null);
    }

}
