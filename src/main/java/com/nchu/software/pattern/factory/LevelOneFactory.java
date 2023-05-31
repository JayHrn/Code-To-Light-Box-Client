package com.nchu.software.pattern.factory;

import com.nchu.software.model.level.Level;
import com.nchu.software.model.level.LevelOne;

/**
 * @Author JayHrn
 * @Date 2023-05-09 13:52
 * @Description 关卡1
 */
public class LevelOneFactory implements LevelFactory{
    @Override
    public Level createLevel() {
        return new LevelOne();
    }
}
