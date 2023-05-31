package com.nchu.software.view;

import com.nchu.software.controller.LoginController;
import com.nchu.software.pattern.singleton.SceneManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Data;

/**
 * @Author JayHrn
 * @Date 2023-05-15 15:23
 * @Description 登陆界面
 */
@Data
public class LoginScreen implements Screen {
    private final String stageTitle = "登录界面";
    private Scene loginScene; // 场景
    private Screen preScreen;
    private TextField usernameField; // 用户名
    private PasswordField passwordField; // 密码
    private Button loginButton; // 登录
    private Button registerButton; // 注册

    /**
     * 初始化登录场景
     */
    public void initScene() {
        // 创建用户名和密码输入框
        usernameField = new TextField();
        usernameField.setPromptText("请输入用户名"); // 提示输入字符
        passwordField = new PasswordField();
        passwordField.setPromptText("请输入密码");

        // 创建水平布局容器，用于放置用户名标签和输入框
        HBox usernameHBox = new HBox(10);
        usernameHBox.setAlignment(Pos.CENTER);
        usernameHBox.getChildren().addAll(new Label("用户名:"), usernameField);

        // 创建水平布局容器，用于放置密码标签和输入框
        HBox passwordHBox = new HBox(10);
        passwordHBox.setAlignment(Pos.CENTER);
        passwordHBox.getChildren().addAll(new Label("   密码:"), passwordField);

        // 创建登录注册按钮
        loginButton = new Button("登录");
        registerButton = new Button("注册");

        // 创建水平布局容器，用于放置用户名标签和输入框
        HBox buttonHBox = new HBox(30);
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.getChildren().addAll(loginButton, registerButton);

        // 创建垂直布局容器
        VBox vFormBox = new VBox(25);
        vFormBox.setPadding(new Insets(20));
        vFormBox.setAlignment(Pos.CENTER);
        vFormBox.setTranslateY(60);

        // 将控件添加到垂直布局容器中
        vFormBox.getChildren().addAll(
                usernameHBox,
                passwordHBox,
                buttonHBox
        );

        // scene之外的最大容器
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(vFormBox);
        // 初始化场景
        loginScene = new Scene(vBox, SceneManager.getInstance().getStage().getWidth(), SceneManager.getInstance().getStage().getHeight());
        // 去除光标聚焦
        loginScene.getRoot().requestFocus();
        // 添加css
        String cssFile = getClass().getResource("/css/login.css").toExternalForm();
        loginScene.getStylesheets().add(cssFile);

        // 监听处理业务逻辑
        LoginController loginController = new LoginController(this);
        loginController.startEventListen();
        // 设置当前舞台标题和切换场景
        SceneManager.getInstance().setStageTitle(stageTitle).switchCurrentScene(loginScene);
    }

    @Override
    public String getStageTitle() {
        return stageTitle;
    }

    public Screen getPreScreen() {
        return preScreen;
    }

    public Scene getScene() {
        return loginScene;
    }

}
