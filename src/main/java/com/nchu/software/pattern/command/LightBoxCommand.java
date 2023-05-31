package com.nchu.software.pattern.command;

import com.nchu.software.model.Map;
import com.nchu.software.model.Robot;
import com.nchu.software.view.GameScreen;

// 具体命令类：点亮
public class LightBoxCommand implements Command {
    private Robot robot;
    private Map map;
    private GameScreen gameScreen;

    public LightBoxCommand(Robot robot, Map map, GameScreen gameScreen) {
        this.robot = robot;
        this.map = map;
        this.gameScreen=gameScreen;
    }

    public boolean execute() {
        // 点亮小灯泡逻辑
        // 只有目标位置和机器人位置一样才能点亮
        if(map.getMapBlocks()[robot.getPosition().getX()][robot.getPosition().getY()]==2){
            gameScreen.lightMap();
            return true;
        }
        return false;
    }
}
