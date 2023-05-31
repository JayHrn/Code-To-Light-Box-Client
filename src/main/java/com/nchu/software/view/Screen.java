package com.nchu.software.view;

import javafx.scene.Scene;

/**
 * @Author JayHrn
 * @Date 2023-05-17 14:16
 * @Description 游戏界面
 */
public interface Screen {
    /**
     * 获取当前界面的左上角标题
     *
     * @return String
     */
    String getStageTitle();

    /**
     * 获取之前的界面
     *
     * @return Screen
     */
    Screen getPreScreen();

    /**
     * 获取当前界面的场景
     *
     * @return Scene
     */
    Scene getScene();
}
