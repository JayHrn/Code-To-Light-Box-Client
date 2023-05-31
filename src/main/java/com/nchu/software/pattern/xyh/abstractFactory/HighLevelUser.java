package com.nchu.software.pattern.xyh.abstractFactory;

/**
 * ClassName: HighLevelUser
 * Package: a.abstractFactory
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 15:58
 * @Version: v1.0
 */

public class HighLevelUser implements User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String unlockedLevel;

    public HighLevelUser() {
    }

    public HighLevelUser(Long id, String username, String password, String email, String unlockedLevel) {
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
