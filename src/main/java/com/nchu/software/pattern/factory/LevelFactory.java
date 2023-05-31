package com.nchu.software.pattern.factory;

import com.nchu.software.model.level.Level;

/**
 * @Author JayHrn
 * @Date 2023-05-09 13:39
 * @Description 关卡工厂，创建不同的关卡
 */
public interface LevelFactory {
    Level createLevel();
}
