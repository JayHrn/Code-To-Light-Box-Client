package com.nchu.software.common;

/**
 * @Author JayHrn
 * @Date 2023-05-10 15:46
 * @Description 基本api请求路径
 */
public class BaseURL {
    private static final String BASE_URL = "http://localhost:9090"; // 设置全局的baseURL

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
