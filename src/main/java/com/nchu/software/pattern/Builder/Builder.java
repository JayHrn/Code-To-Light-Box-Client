package com.nchu.software.pattern.Builder;

import com.nchu.software.model.*;
import com.nchu.software.pattern.factory.Program;

/**
 * @Author JayHrn
 * @Date 2023-05-09 14:16
 * @Description 建造者抽象类
 */
public abstract class Builder {
    // 建造游戏初始状态
    public abstract Builder buildGameState(GameState gameState );
    // 建造游戏地图
    public abstract Builder buildMap(MapFacade mapFacade);
    // 建造游戏机器人
    public abstract Builder buildRobot(Robot robot);
    // 建造游戏程序
    public abstract Builder buildProgram(Program program);
    // 获取游戏结构
    public abstract Game getResult();
}
