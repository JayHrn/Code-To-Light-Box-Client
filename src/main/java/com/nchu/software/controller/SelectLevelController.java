package com.nchu.software.controller;

import com.nchu.software.common.Result;
import com.nchu.software.controller.network.LevelController;
import com.nchu.software.controller.network.UserController;
import com.nchu.software.dto.LevelDto;
import com.nchu.software.dto.UserDto;
import com.nchu.software.model.level.Level;
import com.nchu.software.pattern.singleton.SceneManager;
import com.nchu.software.pattern.factory.LevelOneFactory;
import com.nchu.software.pattern.factory.LevelThreeFactory;
import com.nchu.software.pattern.factory.LevelTwoFactory;
import com.nchu.software.util.LocalGameStorage;
import com.nchu.software.view.AlertTip;
import com.nchu.software.view.GameScreen;
import com.nchu.software.view.LevelScreen;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 * @Author JayHrn
 * @Date 2023-05-16 19:14
 * @Description 关卡选择逻辑控制
 */
public class SelectLevelController {
    private LevelScreen levelScreen;

    public SelectLevelController(LevelScreen levelScreen) {
        this.levelScreen = levelScreen;
    }

    /**
     * 监听
     */
    public void startEventListen() {
        levelScreen.getBackButton().setOnAction(event -> {
            SceneManager.getInstance().setStageTitle(levelScreen.getPreScreen().getStageTitle()).switchCurrentScene(levelScreen.getPreScreen().getScene());
        });

        for (int i = 0; i < levelScreen.getLevelButton().length; i++) {
            Button button = levelScreen.getLevelButton()[i];
            // 第几关
            final int levelGrade = i + 1;
            button.setOnAction(event -> {
                // 创建游戏界面
                GameScreen gameScreen = new GameScreen();
                gameScreen.setPreScreen(levelScreen);
                switch (levelGrade) {
                    case 1:
                        // 设置游戏界面是第一关的
                        LevelOneFactory levelOneFactory = new LevelOneFactory();
                        Level level1 = levelOneFactory.createLevel();
                        // 初始化游戏对象
                        level1.initGame();
                        gameScreen.setLevel(level1);
                        gameScreen.initScene();

                        LevelController levelController1 = new LevelController();
                        Result result1 = levelController1.queryLevelByName(level1.getName());
                        LocalGameStorage.getInstance().setCurrentLevel((LevelDto) result1.getData());
                        break;
                    case 2:
                        // 判断关卡解锁
                        if (getUnlockedLevelLength() >= 2) {
                            // 设置游戏界面是第二关的
                            LevelTwoFactory levelTwoFactory = new LevelTwoFactory();
                            Level level2 = levelTwoFactory.createLevel();
                            // 初始化游戏对象
                            level2.initGame();
                            gameScreen.setLevel(level2);
                            gameScreen.initScene();

                            LevelController levelController2 = new LevelController();
                            Result result2 = levelController2.queryLevelByName(level2.getName());
                            LocalGameStorage.getInstance().setCurrentLevel((LevelDto) result2.getData());
                        } else {
                            AlertTip.showAlert(Alert.AlertType.INFORMATION, "未解锁", "对不起，您未解锁改关卡！");
                        }
                        break;
                    case 3:
                        // 判断关卡解锁
                        if (getUnlockedLevelLength() >= 3) {
                            // 设置游戏界面是第三关的
                            LevelThreeFactory levelThreeFactory = new LevelThreeFactory();
                            Level level3 = levelThreeFactory.createLevel();
                            // 初始化游戏对象
                            level3.initGame();
                            gameScreen.setLevel(level3);
                            gameScreen.initScene();

                            LevelController levelController3 = new LevelController();
                            Result result3 = levelController3.queryLevelByName(level3.getName());
                            LocalGameStorage.getInstance().setCurrentLevel((LevelDto) result3.getData());
                        } else {
                            AlertTip.showAlert(Alert.AlertType.INFORMATION, "未解锁", "对不起，您未解锁改关卡！");
                        }
                        break;
                    default:
                        break;
                }
            });
        }
    }

    /**
     * 获取解锁关卡数目
     *
     * @return int
     */
    public int getUnlockedLevelLength() {
        UserController userController = new UserController();
        Result<UserDto> result = userController.queryUserById(LocalGameStorage.getInstance().getCurrentUser().getId());
        String unlockedLevel = result.getData().getUnlockedLevel();
        return unlockedLevel.split(";").length;
    }
}
