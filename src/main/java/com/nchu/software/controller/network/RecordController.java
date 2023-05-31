package com.nchu.software.controller.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.nchu.software.common.BaseURL;
import com.nchu.software.common.Result;
import com.nchu.software.dto.LevelDto;
import com.nchu.software.dto.RecordDto;
import com.nchu.software.dto.UserDto;
import com.nchu.software.dto.UserRecord;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author JayHrn
 * @Date 2023-05-20 18:06
 * @Description
 */
public class RecordController {
    /**
     * 根据用户id查询闯关记录
     *
     * @param userId 用户id
     * @return User
     */
    public Result<RecordDto> queryRecordByUserId(Long userId,Long levelId) {
        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 创建请求体
        HttpEntity<String> request = new HttpEntity<>("", headers);
        // 发送GET请求并获取响应
        ResponseEntity<String> response = restTemplate.exchange(
                BaseURL.getBaseUrl() + "/record/user/{userId}/{levelId}",
                HttpMethod.GET,
                request,
                String.class,
                userId,
                levelId);
        // 获取响应结果
        String responseBody = response.getBody();
        // 将响应结果解析为Result对象
        Result result = JSON.parseObject(responseBody, Result.class);
        // 获取Result对象中的data，转化成user对象
        RecordDto record = JSON.parseObject(
                String.valueOf(result.getData()),
                new TypeReference<RecordDto>() {
                });
        // 处理响应
        if (response.getStatusCode().is2xxSuccessful()) {
            if (result.getCode() == 200) {
                return Result.success(record, result.getMsg());
            } else {
                return Result.error(result.getMsg());
            }
        } else {
            return Result.error("请求错误");
        }
    }

    /**
     * 新增闯关记录
     *
     * @return
     */
    public Result<String> addRecord(RecordDto record) {
        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();
        // 添加StringHttpMessageConverter来处理字符串类型的响应
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 将Record对象转换为JSON字符串
        String requestBody = JSON.toJSONString(record);
        // 创建请求体
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        // 发送POST请求并获取响应
        ResponseEntity<String> response = restTemplate.exchange(
                BaseURL.getBaseUrl() + "/record", // 替换为实际的API URL
                HttpMethod.POST,
                request,
                String.class);
        // 获取响应结果
        String responseBody = response.getBody();
        // 将响应结果解析为Result对象
        Result result = JSON.parseObject(responseBody, Result.class);
        // 处理响应
        if (response.getStatusCode().is2xxSuccessful()) {
            if (result.getCode() == 200) {
                return Result.success("添加成功", result.getMsg());
            } else {
                return Result.error(result.getMsg());
            }
        } else {
            return Result.error("请求错误");
        }
    }

    /**
     * 更新闯关记录
     *
     * @return
     */
    public Result<String> updateRecord(RecordDto record) {
        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();
        // 添加StringHttpMessageConverter来处理字符串类型的响应
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 将User对象转换为JSON字符串
        String requestBody = JSON.toJSONString(record);
        // 创建请求体
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        // 发送POST请求并获取响应
        ResponseEntity<String> response = restTemplate.exchange(
                BaseURL.getBaseUrl() + "/record", // 替换为实际的API URL
                HttpMethod.PUT,
                request,
                String.class);
        // 获取响应结果
        String responseBody = response.getBody();
        // 将响应结果解析为Result对象
        Result result = JSON.parseObject(responseBody, Result.class);
        // 处理响应
        if (response.getStatusCode().is2xxSuccessful()) {
            if (result.getCode() == 200) {
                return Result.success("添加成功", result.getMsg());
            } else {
                return Result.error(result.getMsg());
            }
        } else {
            return Result.error("请求错误");
        }
    }

    /**
     * 根据关卡id查询闯关记录
     *
     * @param id 关卡id
     * @return User
     */
    public Result<List<UserRecord>> queryRecordByLevelId(Long id) {
        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 创建请求体
        HttpEntity<String> request = new HttpEntity<>("", headers);
        // 发送GET请求并获取响应
        ResponseEntity<String> response = restTemplate.exchange(
                BaseURL.getBaseUrl() + "/record/{id}",
                HttpMethod.GET,
                request,
                String.class,
                id);
        // 获取响应结果
        String responseBody = response.getBody();
        // 将响应结果解析为Result对象
        Result result = JSON.parseObject(responseBody, Result.class);
        // 获取Result对象中的data，转化成user对象
        List<UserRecord> record = JSON.parseObject(
                String.valueOf(result.getData()),
                new TypeReference<List<UserRecord>>() {
                });
        // 处理响应
        if (response.getStatusCode().is2xxSuccessful()) {
            if (result.getCode() == 200) {
                return Result.success(record, result.getMsg());
            } else {
                return Result.error(result.getMsg());
            }
        } else {
            return Result.error("请求错误");
        }
    }
}
