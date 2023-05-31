package com.nchu.software.pattern.xyh.strategy;

/**
 * ClassName: User
 * Package: a.strategy
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 17:36
 * @Version: v1.0
 */
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String unlockedLevel;
    private EncryptionStrategy encryptionStrategy;

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

    public EncryptionStrategy getEncryptionStrategy() {
        return encryptionStrategy;
    }

    public void setEncryptionStrategy(EncryptionStrategy encryptionStrategy) {
        this.encryptionStrategy = encryptionStrategy;
    }

    public User(EncryptionStrategy encryptionStrategy) {
        this.encryptionStrategy = encryptionStrategy;
    }

    // 省略其他属性的getter和setter方法

    public void setPassword(String password) {
        this.password = encryptionStrategy.encrypt(password);
    }
}
