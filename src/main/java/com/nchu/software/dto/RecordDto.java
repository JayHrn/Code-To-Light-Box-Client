package com.nchu.software.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 闯关记录模型
 *
 * @TableName record
 */
@Data
public class RecordDto implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 关卡id
     */
    private Long levelId;

    /**
     * 闯关时长
     */
    private Integer time;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
