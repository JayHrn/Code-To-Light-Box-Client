package com.nchu.software.pattern.Builder;

import com.nchu.software.model.*;
import com.nchu.software.pattern.factory.Program;

/**
 * @Author JayHrn
 * @Date 2023-05-09 14:18
 * @Description 游戏建造者
 */
public class GameBuilder extends Builder {
    /**
     * 游戏对象
     */
    private Game game = Game.getInstance();

    @Override
    public Builder buildGameState(GameState gameState) {
        this.game.setGameState(gameState);
        return this;
    }

    @Override
    public Builder buildMap(MapFacade mapFacade) {
        this.game.setMapFacade(mapFacade);
        return this;
    }

    @Override
    public Builder buildRobot(Robot robot) {
        this.game.setRobot(robot);
        return this;
    }

    @Override
    public Builder buildProgram(Program program) {
        this.game.setProgram(program);
        return this;
    }

    @Override
    public Game getResult() {
        return this.game;
    }

}
