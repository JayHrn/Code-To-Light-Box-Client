package com.nchu.software.controller.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.nchu.software.common.BaseURL;
import com.nchu.software.common.Result;
import com.nchu.software.dto.UserDto;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author JayHrn
 * @Date 2023-05-09 20:08
 * @Description 用户查询相关接口
 */
public class UserController {
    /**
     * 查询所有用户
     *
     * @return List<User>
     */
    public Result<List<UserDto>> queryUser() {
        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 创建请求体
        HttpEntity<String> request = new HttpEntity<>("", headers);
        // 发送GET请求并获取响应
        ResponseEntity<String> response = restTemplate.exchange(
                BaseURL.getBaseUrl() + "/user/list",
                HttpMethod.GET,
                request,
                String.class);
        // 获取响应结果
        String responseBody = response.getBody();
        // 将响应结果解析为Result对象
        Result result = JSON.parseObject(responseBody, Result.class);
        // 获取Result对象中的data，转化成List<user>对象
        List<UserDto> userList = JSON.parseObject(
                String.valueOf(result.getData()),
                new TypeReference<List<UserDto>>() {
                });
        // 处理响应
        if (response.getStatusCode().is2xxSuccessful()) {
            if (result.getCode() == 200) {
                return Result.success(userList, result.getMsg());
            } else {
                return Result.error(result.getMsg());
            }
        } else {
            return Result.error("请求错误");
        }
    }

    /**
     * 根据id查询指定用户
     *
     * @param id 主键
     * @return User
     */
    public Result<UserDto> queryUserById(Long id) {
        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 创建请求体
        HttpEntity<String> request = new HttpEntity<>("", headers);
        // 发送GET请求并获取响应
        ResponseEntity<String> response = restTemplate.exchange(
                BaseURL.getBaseUrl() + "/user/id/{id}",
                HttpMethod.GET,
                request,
                String.class,
                id);
        // 获取响应结果
        String responseBody = response.getBody();
        // 将响应结果解析为Result对象
        Result result = JSON.parseObject(responseBody, Result.class);
        // 获取Result对象中的data，转化成user对象
        UserDto user = JSON.parseObject(
                String.valueOf(result.getData()),
                new TypeReference<UserDto>() {
                });
        // 处理响应
        if (response.getStatusCode().is2xxSuccessful()) {
            if (result.getCode() == 200) {
                return Result.success(user, result.getMsg());
            } else {
                return Result.error(result.getMsg());
            }
        } else {
            return Result.error("请求错误");
        }
    }

    /**
     * 根据username查询指定用户
     *
     * @param username 用户名
     * @return User
     */
    public Result<UserDto> queryUserByUsername(String username) {
        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 创建请求体
        HttpEntity<String> request = new HttpEntity<>("", headers);
        // 发送GET请求并获取响应
        ResponseEntity<String> response = restTemplate.exchange(
                BaseURL.getBaseUrl() + "/user/username/{username}",
                HttpMethod.GET,
                request,
                String.class,
                username);
        // 获取响应结果
        String responseBody = response.getBody();
        // 将响应结果解析为Result对象
        Result result = JSON.parseObject(responseBody, Result.class);
        // 获取Result对象中的data，转化成user对象
        UserDto user = JSON.parseObject(
                String.valueOf(result.getData()),
                new TypeReference<UserDto>() {
                });
        // 处理响应
        if (response.getStatusCode().is2xxSuccessful()) {
            if (result.getCode() == 200) {
                return Result.success(user, result.getMsg());
            } else {
                return Result.error(result.getMsg());
            }
        } else {
            return Result.error("请求错误");
        }
    }

    /**
     * 登录
     *
     * @return
     */
    public Result<UserDto> login(String username, String password) {
        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();
        // 添加StringHttpMessageConverter来处理字符串类型的响应
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 创建User对象
        UserDto user = new UserDto(); // 替换为你的User对象
        user.setUsername(username);
        user.setPassword(password);
        // 将User对象转换为JSON字符串
        String requestBody = JSON.toJSONString(user);
        // 创建请求体
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        // 发送POST请求并获取响应
        ResponseEntity<String> response = restTemplate.exchange(
                BaseURL.getBaseUrl() + "/user/login", // 替换为实际的API URL
                HttpMethod.POST,
                request,
                String.class);
        // 获取响应结果
        String responseBody = response.getBody();
        // 将响应结果解析为Result对象
        Result result = JSON.parseObject(responseBody, Result.class);
        // 获取Result对象中的data，转化成user对象
        UserDto returnUser = JSON.parseObject(
                String.valueOf(result.getData()),
                new TypeReference<UserDto>() {
                });
        // 处理响应
        if (response.getStatusCode().is2xxSuccessful()) {
            if (result.getCode() == 200) {
                return Result.success(returnUser, result.getMsg());
            }
            return Result.error(result.getMsg());
        } else {
            return Result.error("请求错误");
        }
    }

    /**
     * 注册
     *
     * @return
     */
    public Result<String> register(UserDto user) {
        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();
        // 添加StringHttpMessageConverter来处理字符串类型的响应
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 将User对象转换为JSON字符串
        String requestBody = JSON.toJSONString(user);
        // 创建请求体
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        // 发送POST请求并获取响应
        ResponseEntity<String> response = restTemplate.exchange(
                BaseURL.getBaseUrl() + "/user", // 替换为实际的API URL
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
                return Result.success("注册成功", result.getMsg());
            } else {
                return Result.error(result.getMsg());
            }
        } else {
            return Result.error("请求错误");
        }
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    public Result<String> update(UserDto user) {
        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();
        // 添加StringHttpMessageConverter来处理字符串类型的响应
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 将User对象转换为JSON字符串
        String requestBody = JSON.toJSONString(user);
        // 创建请求体
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        // 发送POST请求并获取响应
        ResponseEntity<String> response = restTemplate.exchange(
                BaseURL.getBaseUrl() + "/user", // 替换为实际的API URL
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
                Result.success(result.getMsg(), result.getMsg());
            } else {
                Result.error(result.getMsg());
            }

        }
        return Result.error("请求错误");
    }
}
