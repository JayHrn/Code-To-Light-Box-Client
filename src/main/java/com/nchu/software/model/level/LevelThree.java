package com.nchu.software.model.level;

import com.nchu.software.model.GameState;
import com.nchu.software.model.MapFacade;
import com.nchu.software.model.Position;
import com.nchu.software.model.Robot;
import com.nchu.software.pattern.factory.Program;
import com.nchu.software.pattern.factory.ProgramFactory;

/**
 * @Author JayHrn
 * @Date 2023-05-09 15:47
 * @Description 关卡3
 */
public class LevelThree extends Level {
    private final String name = "第三关";

    @Override
    GameState loadGameState() {
        GameState gameState = new GameState();
        return gameState;
    }

    @Override
    Robot loadRobot() {
        Robot robot = new Robot();
        Position position = new Position();
        position.setX(0);
        position.setY(4);
        robot.setPosition(position);
        return robot;
    }

    @Override
    MapFacade loadMap() {
        int[][] mapBlocks = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 2, 0, 0}
        };
        Position startPosition = new Position();
        startPosition.setX(0);
        startPosition.setY(4);
        return new MapFacade(mapBlocks, startPosition);
    }

    @Override
    Program loadProgram() {
        ProgramFactory programFactory = new ProgramFactory();
        return programFactory.createProgram();
    }

    @Override
    public String getName() {
        return name;
    }

}
