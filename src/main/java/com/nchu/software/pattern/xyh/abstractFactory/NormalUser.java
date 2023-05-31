package com.nchu.software.pattern.xyh.abstractFactory;

/**
 * ClassName: NormalUser
 * Package: a.abstractFactory
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 15:56
 * @Version: v1.0
 */
// 实现具体的用户对象
public class NormalUser implements User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String unlockedLevel;

    public NormalUser() {
    }

    public NormalUser(Long id, String username, String password, String email, String unlockedLevel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.unlockedLevel = unlockedLevel;
    }

    public void login() {
        // 实现登录操作
    }
}