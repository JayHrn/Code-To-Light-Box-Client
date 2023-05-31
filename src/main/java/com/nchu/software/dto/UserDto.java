package com.nchu.software.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 *
 * @TableName user
 */
@Data
public class UserDto implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */

    private String username;

    /**
     * 密码
     */

    private String password;

    /**
     * 电子邮件
     */

    private String email;

    /**
     * 解锁关卡
     */

    private String unlockedLevel;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

    /**
     * 更新时间
     */

    private LocalDateTime updateTime;
}
