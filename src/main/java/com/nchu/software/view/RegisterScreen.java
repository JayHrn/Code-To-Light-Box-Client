package com.nchu.software.view;

import com.nchu.software.controller.RegisterController;
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
 * @Date 2023-05-15 18:03
 * @Description
 */
@Data
public class RegisterScreen implements Screen {
    private final String stageTitle = "注册界面";
    private Scene registerScene; // 场景
    private Screen preScreen; // 上一个场景
    private TextField usernameField; // 用户名
    private PasswordField passwordField; // 密码
    private PasswordField confirmPasswordField; // 重复密码
    private TextField emailField; // 电子右键
    private Button backButton; // 返回按钮
    private Button registerButton; // 提交按钮

    /**
     * 初始化场景
     */
    public void initScene() {
        // 创建用户名和密码输入框
        usernameField = new TextField();
        usernameField.setPromptText("请输入用户名"); // 提示输入字符
        // 密码输入框
        passwordField = new PasswordField();
        passwordField.setPromptText("请输入密码");
        // 重复密码输入框
        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("请再次输入密码");
        // 邮箱密码框
        emailField = new TextField();
        emailField.setPromptText("请输入邮箱");

        // 创建水平布局容器，用于放置用户名标签和输入框
        HBox usernameHBox = new HBox(10);
        usernameHBox.setAlignment(Pos.CENTER);
        usernameHBox.getChildren().addAll(new Label("   用户名:"), usernameField);

        // 创建水平布局容器，用于放置密码标签和输入框
        HBox passwordHBox = new HBox(10);
        passwordHBox.setAlignment(Pos.CENTER);
        passwordHBox.getChildren().addAll(new Label("       密码:"), passwordField);

        // 创建水平布局容器，用于放置确认密码标签和输入框
        HBox confirmPasswordHBox = new HBox(10);
        confirmPasswordHBox.setAlignment(Pos.CENTER);
        confirmPasswordHBox.getChildren().addAll(new Label("确认密码:"), confirmPasswordField);

        // 创建水平布局容器，用于放置邮箱标签和输入框
        HBox emailHBox = new HBox(10);
        emailHBox.setAlignment(Pos.CENTER);
        emailHBox.getChildren().addAll(new Label("       邮箱:"), emailField);

        // 创建登录注册按钮
        backButton = new Button("返回");
        registerButton = new Button("提交");

        // 创建水平布局容器，用于放置按钮
        HBox buttonHBox = new HBox(30);
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.getChildren().addAll(backButton, registerButton);

        // 创建垂直布局容器
        VBox vFormBox = new VBox(25);
        vFormBox.setPadding(new Insets(20));
        vFormBox.setAlignment(Pos.CENTER);
        vFormBox.setTranslateY(60);

        // 将控件添加到垂直布局容器中
        vFormBox.getChildren().addAll(
                usernameHBox,
                passwordHBox,
                confirmPasswordHBox,
                emailHBox,
                buttonHBox
        );

        // scene之外的最大容器
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(vFormBox);
        // 初始化场景
        registerScene = new Scene(vBox, SceneManager.getInstance().getStage().getWidth(), SceneManager.getInstance().getStage().getHeight());
        // 去除光标聚焦
        registerScene.getRoot().requestFocus();
        // 添加css
        String cssFile = getClass().getResource("/css/login.css").toExternalForm();
        registerScene.getStylesheets().add(cssFile);
        // 控制业务逻辑
        RegisterController registerController = new RegisterController(this);
        registerController.startEventListen();
        // 设置当前舞台标题和切换场景
        SceneManager.getInstance().setStageTitle(stageTitle).switchCurrentScene(registerScene);
    }

    @Override
    public String getStageTitle() {
        return stageTitle;
    }

    public Screen getPreScreen() {
        return preScreen;
    }

    public Scene getScene() {
        return registerScene;
    }
}
