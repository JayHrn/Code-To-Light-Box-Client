package com.nchu.software.pattern.singleton;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @Author JayHrn
 * @Date 2023-05-16 13:34
 * @Description 场景管理器 单例模式
 */
public class SceneManager {
    private static SceneManager instance;
    private Stage stage;

    private SceneManager() {
        // 私有构造函数
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public SceneManager switchCurrentScene(Scene newScene) {
        stage.setScene(null);
        stage.setScene(newScene);
        return this;
    }

    public SceneManager setStageTitle(String title) {
        stage.setTitle(title);
        return this;
    }

    // 可以添加其他方法来传递数据或执行其他管理任务
}

