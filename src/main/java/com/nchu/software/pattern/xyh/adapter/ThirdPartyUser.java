package com.nchu.software.pattern.xyh.adapter;

/**
 * ClassName: ThirdPartyUser
 * Package: a.adapter
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 17:04
 * @Version: v1.0
 */
public class ThirdPartyUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public ThirdPartyUser(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Getters and setters omitted for brevity

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

