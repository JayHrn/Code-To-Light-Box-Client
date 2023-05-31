package com.nchu.software.pattern.xyh.abstractFactory;

/**
 * ClassName: UserFactory
 * Package: a.abstractFactory
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 15:55
 * @Version: v1.0
 */
// 定义抽象工厂类
public interface UserFactory {
    public User createUser();
}