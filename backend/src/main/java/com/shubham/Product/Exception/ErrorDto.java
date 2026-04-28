package com.shubham.Product.Exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;

import java.util.Date;

@NoArgsConstructor
@Data
public class ErrorDto {
    private int code;
    private String message;
    private Date date= new Date(System.currentTimeMillis());

    public ErrorDto(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
