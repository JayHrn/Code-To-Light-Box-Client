package com.nchu.software.pattern.command;

import com.nchu.software.model.Map;
import com.nchu.software.model.Robot;
import com.nchu.software.pattern.strategy.MoveStrategy;
import com.nchu.software.view.GameScreen;

// 具体命令类：移动类，移动有具体的策略
public class MoveCommand implements Command {
    private Robot robot;
    private Map map;
    private MoveStrategy moveStrategy;
    private GameScreen gameScreen;

    public MoveCommand(Robot robot, Map map, MoveStrategy moveStrategy, GameScreen gameScreen) {
        this.robot = robot;
        this.map = map;
        this.moveStrategy = moveStrategy;
        this.gameScreen = gameScreen;
    }

    public boolean execute() {
        return moveStrategy.doAction(robot, map, gameScreen);
    }
}
