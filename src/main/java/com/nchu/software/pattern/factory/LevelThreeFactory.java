package com.nchu.software.pattern.factory;

import com.nchu.software.model.level.Level;
import com.nchu.software.model.level.LevelThree;
import com.nchu.software.model.level.LevelTwo;

/**
 * @Author JayHrn
 * @Date 2023-05-09 13:55
 * @Description 关卡3
 */
public class LevelThreeFactory implements LevelFactory{
    @Override
    public Level createLevel() {
        return new LevelThree();
    }
}
