package com.nchu.software.view;

import com.nchu.software.controller.SelectLevelController;
import com.nchu.software.pattern.singleton.SceneManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import lombok.Data;

/**
 * @Author JayHrn
 * @Date 2023-05-16 18:16
 * @Description 关卡选择界面
 */
@Data
public class LevelScreen implements Screen {
    private final String stageTitle = "关卡界面";
    private Scene levelScene; // 场景
    private Screen preScreen; // 上一个场景
    private Button backButton; // 返回按钮
    private Button[] levelButton = new Button[3]; // 关卡选择按钮

    public void initScene() {
        // 容器面板
        Pane pane = new Pane();
        // 返回按钮
        backButton = new Button();
        backButton.getStyleClass().add("backButton");

        for (int i = 0; i < levelButton.length; i++) {
            // new按钮对象，不然没有对象报错
            levelButton[i] = new Button();
            // 设置类名
            String levelClass = "level" + (i + 1) + "Button";
            levelButton[i].getStyleClass().add(levelClass);
            // 添加进panel
            pane.getChildren().add(levelButton[i]);
        }

        // 添加组件
        pane.getChildren().addAll(backButton);

        // 初始化场景
        levelScene = new Scene(pane, SceneManager.getInstance().getStage().getWidth(), SceneManager.getInstance().getStage().getHeight());
        // 去除光标聚焦
        levelScene.getRoot().requestFocus();
        // 添加css
        String cssFile = getClass().getResource("/css/level.css").toExternalForm();
        levelScene.getStylesheets().add(cssFile);
        // 控制业务逻辑
        SelectLevelController selectLevelController = new SelectLevelController(this);
        selectLevelController.startEventListen();
        // 设置当前舞台标题和切换场景
        SceneManager.getInstance().setStageTitle(stageTitle);
        SceneManager.getInstance().switchCurrentScene(levelScene);
    }

    @Override
    public String getStageTitle() {
        return stageTitle;
    }

    public Screen getPreScreen() {
        return preScreen;
    }

    public Scene getScene() {
        return levelScene;
    }
}
