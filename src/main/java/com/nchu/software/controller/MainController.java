package com.nchu.software.controller;

import com.nchu.software.pattern.singleton.SceneManager;
import com.nchu.software.pattern.singleton.VolumeStage;
import com.nchu.software.view.LevelScreen;
import com.nchu.software.view.MainScreen;
import com.nchu.software.view.RankScreen;
import com.nchu.software.view.VolumeScreen;
import javafx.stage.Stage;

/**
 * @Author JayHrn
 * @Date 2023-05-16 18:12
 * @Description 主界面逻辑控制
 */
public class MainController {
    private MainScreen mainScreen;

    public MainController(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

    /**
     * 监听
     */
    public void startEventListen() {
        // 启动游戏进入关卡选择页面
        mainScreen.getStartButton().setOnAction(event -> {
            LevelScreen levelScreen = new LevelScreen();
            levelScreen.setPreScreen(mainScreen);
            levelScreen.initScene();
        });
        // 进入排行榜界面
        mainScreen.getRankButton().setOnAction(event -> {
            RankScreen rankScreen = new RankScreen();
            rankScreen.setPreScreen(mainScreen);
            rankScreen.initScene();
        });
        // 弹窗控制音量
        mainScreen.getVolumeButton().setOnAction(event -> {

            Stage volumeStage = VolumeStage.getInstance();
            if (volumeStage.isShowing()) {
                volumeStage.close();
            }
            // 设置小舞台
            volumeStage.setWidth(700);
            volumeStage.setHeight(500);
            volumeStage.setTitle("音量调节页面");
            //创建音量界面
            VolumeScreen volumeScreen = new VolumeScreen();
            volumeScreen.setMainScreen(mainScreen);
            volumeScreen.initScene();

            volumeStage.setScene(volumeScreen.getVolumeScene());

            volumeStage.show();


        });

        // 退出登录
        mainScreen.getLogoutButton().setOnAction(event -> {
            SceneManager.getInstance().setStageTitle(mainScreen.getPreScreen().getStageTitle()).switchCurrentScene(mainScreen.getPreScreen().getScene());
        });
    }
}
