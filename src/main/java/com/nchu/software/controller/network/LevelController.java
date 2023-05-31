package com.nchu.software.controller.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.nchu.software.common.BaseURL;
import com.nchu.software.common.Result;
import com.nchu.software.dto.LevelDto;
import com.nchu.software.dto.UserDto;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * @Author JayHrn
 * @Date 2023-05-16 15:12
 * @Description 关卡相关接口
 */
public class LevelController {
    /**
     * 查询第一关
     *
     * @return List<User>
     */
    public Result<LevelDto> queryFirstLevel() {
        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 创建请求体
        HttpEntity<String> request = new HttpEntity<>("", headers);
        // 发送GET请求并获取响应
        ResponseEntity<String> response = restTemplate.exchange(
                BaseURL.getBaseUrl() + "/level/firstLevel",
                HttpMethod.GET,
                request,
                String.class);
        // 获取响应结果
        String responseBody = response.getBody();
        // 将响应结果解析为Result对象
        Result result = JSON.parseObject(responseBody, Result.class);
        // 获取Result对象中的data，转化成Level对象
        LevelDto level = JSON.parseObject(
                String.valueOf(result.getData()),
                new TypeReference<LevelDto>() {
                });
        // 处理响应
        if (response.getStatusCode().is2xxSuccessful()) {
            if (result.getCode() == 200) {
                return Result.success(level, result.getMsg());
            } else {
                return Result.error(result.getMsg());
            }
        } else {
            return Result.error("请求错误");
        }
    }

    /**
     * 根据上一个关卡，查询下一个关卡
     *
     * @return Result<LevelDto>
     */
    public Result<LevelDto> queryNextLevel(Long id) {
        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 创建请求体
        HttpEntity<String> request = new HttpEntity<>("", headers);
        // 发送GET请求并获取响应
        ResponseEntity<String> response = restTemplate.exchange(
                BaseURL.getBaseUrl() + "/level/nextLevel/{id}",
                HttpMethod.GET,
                request,
                String.class,
                id);
        // 获取响应结果
        String responseBody = response.getBody();
        // 将响应结果解析为Result对象
        Result result = JSON.parseObject(responseBody, Result.class);
        // 获取Result对象中的data，转化成Level对象
        LevelDto level = JSON.parseObject(
                String.valueOf(result.getData()),
                new TypeReference<LevelDto>() {
                });
        // 处理响应
        if (response.getStatusCode().is2xxSuccessful()) {
            if (result.getCode() == 200) {
                return Result.success(level, result.getMsg());
            } else {
                return Result.error(result.getMsg());
            }
        } else {
            return Result.error("请求错误");
        }
    }

    /**
     * 根据name查询指定关卡
     *
     * @param name 关卡名称
     * @return User
     */
    public Result<LevelDto> queryLevelByName(String name) {
        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 创建请求体
        HttpEntity<String> request = new HttpEntity<>("", headers);
        // 发送GET请求并获取响应
        ResponseEntity<String> response = restTemplate.exchange(
                BaseURL.getBaseUrl() + "/level/{name}",
                HttpMethod.GET,
                request,
                String.class,
                name);
        // 获取响应结果
        String responseBody = response.getBody();
        // 将响应结果解析为Result对象
        Result result = JSON.parseObject(responseBody, Result.class);
        // 获取Result对象中的data，转化成user对象
        LevelDto level = JSON.parseObject(
                String.valueOf(result.getData()),
                new TypeReference<LevelDto>() {
                });
        // 处理响应
        if (response.getStatusCode().is2xxSuccessful()) {
            if (result.getCode() == 200) {
                return Result.success(level, result.getMsg());
            } else {
                return Result.error(result.getMsg());
            }
        } else {
            return Result.error("请求错误");
        }
    }
}
