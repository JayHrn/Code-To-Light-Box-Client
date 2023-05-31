package com.nchu.software.common;

import lombok.Data;

/**
 * @Author JayHrn
 * @Date 2023-05-09 13:35
 * @Description 通用返回结果
 * {
 * code: 状态码
 * msg: 消息
 * data: 数据
 * }
 */
@Data
public class Result<T> {
    private Integer code; //编码：200 成功，0和其它数字为失败
    private String msg;  //消息
    private T data;  //数据

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    /**
     * 成功带返回数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data, String msg) {
        Result<T> result = new Result<>(data);
        result.code = 200;
        result.data = data;
        result.msg = msg;
        return result;
    }

    /**
     * 成功不带返回数据
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(String msg) {
        Result<T> result = new Result<>();
        result.code = 200;
        result.msg = msg;
        return result;
    }

    /**
     * 错误带返回消息
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }
}
