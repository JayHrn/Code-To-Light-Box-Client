package com.nchu.software.controller;

import com.nchu.software.common.Result;
import com.nchu.software.controller.network.LevelController;
import com.nchu.software.controller.network.UserController;
import com.nchu.software.dto.LevelDto;
import com.nchu.software.dto.UserDto;
import com.nchu.software.pattern.singleton.LanguageManager;
import com.nchu.software.pattern.singleton.SceneManager;
import com.nchu.software.util.UTF8Control;
import com.nchu.software.view.AlertTip;
import com.nchu.software.view.RegisterScreen;
import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import org.springframework.util.DigestUtils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Author JayHrn
 * @Date 2023-05-15 20:55
 * @Description 注册逻辑控制
 */
public class RegisterController {
    private RegisterScreen registerScreen; // 注册界面

    public RegisterController(RegisterScreen registerScreen) {
        this.registerScreen = registerScreen;
    }



    /**
     * 监听
     */
    public void startEventListen() {
        // 返回登陆界面
        registerScreen.getBackButton().setOnAction(event -> {
            SceneManager.getInstance().setStageTitle(registerScreen.getPreScreen().getStageTitle()).switchCurrentScene(registerScreen.getPreScreen().getScene());
        });
        // 设置登录按钮的事件处理程序
        registerScreen.getRegisterButton().setOnAction(event -> {
            String username = registerScreen.getUsernameField().getText();
            String password = registerScreen.getPasswordField().getText();
            String email = registerScreen.getEmailField().getText();

            //语言切换
            String lang = LanguageManager.getInstance().getLanguageSetting().getLanguage();

            ResourceBundle bundle;
            if(lang.equals("Chinese")){
                // 默认加载中文资源束
                bundle= ResourceBundle.getBundle("language/messages", Locale.CHINA,new UTF8Control());
            }
            else{
                // 默认加载英文资源束
                bundle  = ResourceBundle.getBundle("language/messages", Locale.ENGLISH, new UTF8Control());

            }


            // 所有输入合法
            if (validateInput()) {
                UserController userController = new UserController();
                // 全部格式合法
                UserDto user = new UserDto();
                user.setUsername(username);
                // 密码加密
                user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
                user.setEmail(email);
                // 查询第一关对象
                LevelController levelController = new LevelController();
                Result<LevelDto> result = levelController.queryFirstLevel();
                // 设置新用户解锁第一关
                if (result.getCode() == 200) {
                    user.setUnlockedLevel(result.getData().getId() + ";");
                    Result<String> registerResult = userController.register(user);
                    // 成功注册
                    if (registerResult.getCode() == 200) {
                        AlertTip.showAutoAlert(Alert.AlertType.INFORMATION, bundle.getString("regSuc"), bundle.getString("regSucMess"));

                        PauseTransition delay = new PauseTransition(Duration.seconds(5));
                        delay.setOnFinished(e -> {
                            AlertTip.getAlert().close();
                            // 清空输入框
                            registerScreen.getUsernameField().clear();
                            registerScreen.getPasswordField().clear();
                            registerScreen.getConfirmPasswordField().clear();
                            registerScreen.getEmailField().clear();
                            SceneManager.getInstance().setStageTitle(registerScreen.getPreScreen().getStageTitle()).switchCurrentScene(registerScreen.getPreScreen().getScene());
                        });
                        delay.play();

                    } else {
                        AlertTip.showAlert(Alert.AlertType.ERROR, bundle.getString("regFai"), bundle.getString("regFaiMess"));
                    }
                }
            }
        });
    }

    /**
     * 校验所有输入
     *
     * @return boolean
     */
    public boolean validateInput() {

        //语言切换
        String lang = LanguageManager.getInstance().getLanguageSetting().getLanguage();

        ResourceBundle bundle;
        if(lang.equals("Chinese")){
            // 默认加载中文资源束
            bundle= ResourceBundle.getBundle("language/messages", Locale.CHINA,new UTF8Control());
        }
        else{
            // 默认加载英文资源束
            bundle  = ResourceBundle.getBundle("language/messages", Locale.ENGLISH, new UTF8Control());

        }

        String username = registerScreen.getUsernameField().getText();
        // 用户名为空
        if (username.isEmpty()) {
            AlertTip.showAlert(Alert.AlertType.ERROR, bundle.getString("usn"), bundle.getString("usnPro"));
            return false;
        }
        // 两次密码不匹配
        if (!checkPasswordMatch()) {
            AlertTip.showAlert(Alert.AlertType.ERROR,  bundle.getString("pass"), bundle.getString("passPro"));
            return false;
        }
        // 邮箱格式不合法
        if (!validateEmail()) {
            AlertTip.showAlert(Alert.AlertType.ERROR, bundle.getString("email"), bundle.getString("emailPro"));
            return false;
        }
        // 为了减少查询，最后判断用户名已存在的情况
        UserController userController = new UserController();

        Result<UserDto> result = userController.queryUserByUsername(username);
        if (result.getData() != null) {
            AlertTip.showAlert(Alert.AlertType.ERROR, bundle.getString("usn"), bundle.getString("usnExi"));
            return false;
        }
        return true;
    }

    /**
     * 校验两次输入的密码
     *
     * @return boolean
     */
    public boolean checkPasswordMatch() {
        String password = registerScreen.getPasswordField().getText();
        String confirmPassword = registerScreen.getConfirmPasswordField().getText();
        return password.equals(confirmPassword);
    }

    /**
     * 校验邮箱
     *
     * @return boolean
     */
    public boolean validateEmail() {
        String email = registerScreen.getEmailField().getText();
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}
