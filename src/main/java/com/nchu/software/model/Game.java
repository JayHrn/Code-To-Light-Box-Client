package com.nchu.software.model;

import com.nchu.software.pattern.factory.Program;
import lombok.Data;

/**
 * @Author JayHrn
 * @Date 2023-05-09 13:53
 * @Description 游戏类
 */
@Data
public class Game {
    private GameState gameState;
    private MapFacade mapFacade;
    private Robot robot;
    private Program program;
    private int time;

    /**
     * 私有化构造方法
     */
    private Game() {
    }

    /**
     * 定义静态内部类
     */
    private static class GameHolder {
        // 在内部类中声明并初始化外部类的对象
        private static final Game INSTANCE = new Game();
    }

    /**
     * 提供公共的访问方式
     *
     * @return 游戏
     */
    public static Game getInstance() {

        return GameHolder.INSTANCE;
    }

    /**
     * 重置时间
     */
    public void resetTime() {
        this.time = 0;
    }
}
