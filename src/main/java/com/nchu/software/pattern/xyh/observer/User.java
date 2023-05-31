package com.nchu.software.pattern.xyh.observer;

/**
 * ClassName: User
 * Package: a.observer
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 17:58
 * @Version: v1.0
 */
public class User implements Observer {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void update(String unlockedLevel) {
        System.out.println("Hi " + username + ", the ranking list has been updated. Your unlocked level is now " + unlockedLevel);
    }
}
