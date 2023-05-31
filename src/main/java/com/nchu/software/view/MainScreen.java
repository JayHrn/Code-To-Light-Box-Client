package com.nchu.software.view;

import com.nchu.software.pattern.observer.LanguageButton;
import com.nchu.software.pattern.observer.LanguageSetting;
import com.nchu.software.controller.MainController;
import com.nchu.software.pattern.singleton.LanguageManager;
import com.nchu.software.pattern.singleton.SceneManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import lombok.Data;

/**
 * @Author JayHrn
 * @Date 2023-05-15 19:51
 * @Description 主界面
 */
@Data
public class MainScreen implements Screen {
    private final String stageTitle = "主界面";
    private Scene mainScene; // 场景
    private Screen preScreen; // 上一个场景
    private Button startButton; // 启动游戏按钮
    private Button rankButton; // 排行榜按钮
    private Button volumeButton; // 音量按钮
    private Button logoutButton; //退出登录按钮
    private Button langButton; //语言切换按钮
    private MediaPlayer mediaPlayer;
    private LanguageSetting languageSetting; // 语言设置主题

    /**
     * 初始化场景
     */
    public void initScene() {
        // 启动游戏按钮
        startButton = new Button();
        startButton.getStyleClass().add("startButton");
        // 排行榜按钮
        rankButton = new Button();
        rankButton.getStyleClass().add("rankButton");
        // 音量按钮
        volumeButton = new Button();
        volumeButton.getStyleClass().add("volumeButton");
        // 登出按钮
        logoutButton = new Button();
        logoutButton.getStyleClass().add("logoutButton");

        // 初始化语言设置主题（观察者）
        languageSetting = new LanguageSetting();
        LanguageManager.getInstance().setLanguageSetting(languageSetting);
        // 初始化按钮图片和标签
        Image chineseImage = new Image(getClass().getResourceAsStream("/img/ch.png"));
        Image englishImage = new Image(getClass().getResourceAsStream("/img/en.png"));

        //语言切换按钮
        langButton = new Button();
        langButton.getStyleClass().add("langButton");
        LanguageButton languageButton = new LanguageButton(langButton, chineseImage, englishImage);

        // 注册观察
        languageSetting.registerObserver(languageButton);

        // 设置按钮点击事件，切换语言设置
        langButton.setOnAction(event -> {
            if (languageSetting.getLanguage().equals("Chinese")) {
                languageSetting.setLanguage("English");
//                System.out.println("English");
            } else {
                languageSetting.setLanguage("Chinese");
//                System.out.println("Chinese");
            }
        });


        // 容器面板
        Pane pane = new Pane();
        // 添加组件
        pane.getChildren().addAll(startButton, rankButton, volumeButton, logoutButton,langButton);

        // 初始化场景
        mainScene = new Scene(pane, SceneManager.getInstance().getStage().getWidth(), SceneManager.getInstance().getStage().getHeight());
        // 去除光标聚焦
        mainScene.getRoot().requestFocus();
        // 添加css
        String cssFile = getClass().getResource("/css/main.css").toExternalForm();
        mainScene.getStylesheets().add(cssFile);
        // 控制业务逻辑
        MainController mainController = new MainController(this);
        mainController.startEventListen();
        // 设置当前舞台标题和切换场景
        SceneManager.getInstance().setStageTitle(stageTitle).switchCurrentScene(mainScene);
    }

    @Override
    public String getStageTitle() {
        return stageTitle;
    }

    public Screen getPreScreen() {
        return preScreen;
    }

    public Scene getScene() {
        return mainScene;
    }
}
