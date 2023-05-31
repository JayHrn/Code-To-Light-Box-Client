package com.nchu.software.pattern.strategy;

import com.nchu.software.model.Map;
import com.nchu.software.model.Robot;
import com.nchu.software.view.GameScreen;

/**
 * @Author JayHrn
 * @Date 2023-05-09 16:51
 * @Description 左转
 */
public class MoveLeftStrategy implements MoveStrategy {
    @Override
    public boolean doAction(Robot robot, Map map, GameScreen gameScreen) {
        // 1. 向左移动超出地图
        if (robot.getPosition().getY() - 1 < 0) {
            return false;
        }
        // 2. 没有超出地图，但是向左没有路可以走
        if (map.getMapBlocks()[robot.getPosition().getX()][robot.getPosition().getY() - 1] == 0) {
            return false;
        }
        robot.getPosition().setY(robot.getPosition().getY() - 1);
        gameScreen.updateRobot();

        return true;
    }
}
