package com.nchu.software.pattern.strategy;

import com.nchu.software.model.Map;
import com.nchu.software.model.Robot;
import com.nchu.software.view.GameScreen;

/**
 * @Author JayHrn
 * @Date 2023-05-19 18:25
 * @Description
 */
public class MoveDownStrategy implements MoveStrategy {
    @Override
    public boolean doAction(Robot robot, Map map, GameScreen gameScreen) {
        // 1. 向下移动超出地图
        if (robot.getPosition().getX() + 1 >= map.getMapBlocks().length) {
            return false;
        }
        // 2. 没有超出地图，但是向下没有路可以走
        if (map.getMapBlocks()[robot.getPosition().getX() + 1][robot.getPosition().getY()] == 0) {
            return false;
        }
        robot.getPosition().setX(robot.getPosition().getX() + 1);
        gameScreen.updateRobot();

        return true;
    }
}
