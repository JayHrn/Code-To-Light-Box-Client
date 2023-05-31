package com.nchu.software.pattern.xyh.abstractFactory;

/**
 * ClassName: NormalUserFactory
 * Package: a.abstractFactory
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 15:56
 * @Version: v1.0
 */

// 实现具体的工厂类
public class NormalUserFactory implements UserFactory {
    public User createUser() {
        return new NormalUser();
    }
}