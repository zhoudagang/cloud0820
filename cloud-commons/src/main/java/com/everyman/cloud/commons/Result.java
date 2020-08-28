package com.everyman.cloud.commons;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author zhougang
 * @date 2020/08/20
 */
@Data
@NoArgsConstructor
public class Result<T>
{

    private T data;

    private String message;

    private int code;

    public Result(T data, String message, int code)
    {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public Result(String message, Integer code)
    {
        this(null, message, code);
    }

    public Result(T data)
    {
        this(data, "操作成功", 200);
    }


}