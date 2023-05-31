package com.nchu.software;

import com.nchu.software.pattern.singleton.MediaPlayerChange;
import com.nchu.software.pattern.singleton.SceneManager;
import com.nchu.software.view.LoginScreen;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @Author JayHrn
 * @Date 2023-05-09 12:02
 * @Description 程序入口
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // 设置舞台
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        primaryStage.setResizable(false);//不可拉伸

        // 获取 MediaPlayer 实例 且 播放背景音乐
        MediaPlayerChange.getInstance().getMediaPlayer().play();

        // 场景管理器设置舞台
        SceneManager.getInstance().setStage(primaryStage);
        // 登陆界面
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.initScene();
        // 展示舞台
        primaryStage.show();
    }

    @Override
    public void stop() {
        // 在关闭应用程序时停止播放背景音乐
        MediaPlayerChange.getInstance().getMediaPlayer().stop();
    }

}
