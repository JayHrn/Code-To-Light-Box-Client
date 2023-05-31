package com.nchu.software.controller;

import com.nchu.software.common.Result;
import com.nchu.software.controller.network.UserController;
import com.nchu.software.dto.UserDto;
import com.nchu.software.util.LocalGameStorage;
import com.nchu.software.view.AlertTip;
import com.nchu.software.view.LoginScreen;
import com.nchu.software.view.MainScreen;
import com.nchu.software.view.RegisterScreen;
import javafx.scene.control.Alert;
import org.springframework.util.DigestUtils;

/**
 * @Author JayHrn
 * @Date 2023-05-15 19:14
 * @Description 注册逻辑
 */
public class LoginController {
    private LoginScreen loginScreen;

    public LoginController(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;
    }

    /**
     * 监听
     */
    public void startEventListen() {
        // 设置登录按钮的事件处理程序
        loginScreen.getLoginButton().setOnAction(event -> {
            String username = loginScreen.getUsernameField().getText();
            String password = loginScreen.getPasswordField().getText();
            if (validateInput()) {
                // 请求接口进行登录
                UserController userController = new UserController();
                // 密码加密
                password = DigestUtils.md5DigestAsHex(password.getBytes());
                // 获取登陆结果
                Result result = userController.login(username, password);
                // 设置当前用户信息
                LocalGameStorage.getInstance().setCurrentUser((UserDto) result.getData());
                // 登陆成功
                if (result.getCode() == 200) {
                    // 清空输入框
                    loginScreen.getUsernameField().clear();
                    loginScreen.getPasswordField().clear();
                    // 界面跳转
                    MainScreen mainScreen = new MainScreen();
                    mainScreen.setPreScreen(loginScreen);
                    mainScreen.initScene();
                } else { // 失败
                    // 显示提示框
                    AlertTip.showAlert(Alert.AlertType.ERROR, "登陆失败", result.getMsg());
                }
            }
        });
        // 监听注册按钮
        loginScreen.getRegisterButton().setOnAction(event -> {
            // 初始化注册界面
            RegisterScreen registerScreen = new RegisterScreen();
            registerScreen.setPreScreen(loginScreen);
            registerScreen.initScene();
        });
    }

    public boolean validateInput() {
        String username = loginScreen.getUsernameField().getText();
        String password = loginScreen.getPasswordField().getText();
        if (username.isEmpty()) {
            AlertTip.showAlert(Alert.AlertType.ERROR, "用户名有误", "用户名为空，请输入用户名！");
            return false;
        }
        if (password.isEmpty()) {
            AlertTip.showAlert(Alert.AlertType.ERROR, "密码有误", "密码为空，请输入密码！");
            return false;
        }
        return true;
    }
}
