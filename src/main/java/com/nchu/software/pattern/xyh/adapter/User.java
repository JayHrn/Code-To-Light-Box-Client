package com.nchu.software.pattern.xyh.adapter;

/**
 * ClassName: User
 * Package: a.adapter
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 17:05
 * @Version: v1.0
 */
// 用户类
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String unlockedLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUnlockedLevel() {
        return unlockedLevel;
    }

    public void setUnlockedLevel(String unlockedLevel) {
        this.unlockedLevel = unlockedLevel;
    }
}
