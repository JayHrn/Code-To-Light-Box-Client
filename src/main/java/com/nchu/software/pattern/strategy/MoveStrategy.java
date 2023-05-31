package com.nchu.software.pattern.strategy;

import com.nchu.software.model.Map;
import com.nchu.software.model.Robot;
import com.nchu.software.view.GameScreen;

/**
 * @Author JayHrn
 * @Date 2023-05-09 16:43
 * @Description 机器人移动策略 策略模式（前进，左转，右转）
 */
public interface MoveStrategy {
    boolean doAction(Robot robot, Map map, GameScreen gameScreen);
}
