package com.nchu.software.pattern.strategy;

import com.nchu.software.model.Map;
import com.nchu.software.model.Robot;
import com.nchu.software.view.GameScreen;

/**
 * @Author JayHrn
 * @Date 2023-05-09 16:51
 * @Description 前进
 */
public class MoveForwardStrategy implements MoveStrategy {
    @Override
    public boolean doAction(Robot robot, Map map, GameScreen gameScreen) {
        // 1. 向上移动超出地图
        if (robot.getPosition().getX() - 1 < 0) {
            return false;
        }
        // 2. 没有超出地图，但是向上没有路可以走
        if (map.getMapBlocks()[robot.getPosition().getX()-1][robot.getPosition().getY()] == 0) {
            return false;
        }
        robot.getPosition().setX(robot.getPosition().getX() - 1);
        gameScreen.updateRobot();

        return true;
    }
}
