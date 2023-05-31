package com.nchu.software.pattern.xyh.abstractFactory;

/**
 * ClassName: HighLevelUserFactory
 * Package: a.abstractFactory
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 15:58
 * @Version: v1.0
 */
public class HighLevelUserFactory implements UserFactory {
    public User createUser() {
        return new HighLevelUser();
    }
}
