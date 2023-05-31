package com.nchu.software.model.level;

import com.nchu.software.model.Game;
import com.nchu.software.model.GameState;
import com.nchu.software.model.MapFacade;
import com.nchu.software.model.Robot;
import com.nchu.software.pattern.Builder.GameBuilder;
import com.nchu.software.pattern.factory.Program;
import lombok.Data;

/**
 * @Author JayHrn
 * @Date 2023-05-09 13:53
 * @Description 关卡 模板方法
 */
@Data
public abstract class Level {
    private String name;
    private Game game;
    private GameBuilder gameBuilder = new GameBuilder();

    abstract GameState loadGameState();

    abstract Robot loadRobot();

    abstract MapFacade loadMap();

    abstract Program loadProgram();

    /**
     * 初始化游戏
     */
    public final void initGame() {
        GameState gameState = loadGameState();
        Robot robot = loadRobot();
        MapFacade mapFacade = loadMap();
        Program program = loadProgram();
        this.game = gameBuilder.buildGameState(gameState).
                buildRobot(robot).buildMap(mapFacade).
                buildProgram(program).getResult();
        // 重置游戏时间
        this.game.resetTime();
    }
}
