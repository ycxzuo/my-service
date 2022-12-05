package com.yczuoxin.myservice.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseResult<T> {

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private int code;

    private String message;

    private T data;

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult(HttpStatus.OK.value(), "OK", data);
    }
    public static <T> ResponseResult<T> error(HttpStatus status) {
        return new ResponseResult(status.value(), status.name(), null);
    }

}
