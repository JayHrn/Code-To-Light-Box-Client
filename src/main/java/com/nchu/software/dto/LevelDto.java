package com.nchu.software.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 关卡模型
 *
 * @TableName level
 */
@Data
public class LevelDto implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 关卡难度
     */
    private Long difficulty;

    /**
     * 上一关卡
     */
    private Long preLevel;
}
