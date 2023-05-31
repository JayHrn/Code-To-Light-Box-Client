package com.nchu.software.pattern.xyh.strategy;

/**
 * ClassName: Md5EncryptionStrategy
 * Package: a.strategy
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 17:35
 * @Version: v1.0
 */
public class Md5EncryptionStrategy implements EncryptionStrategy {
    @Override
    public String encrypt(String password) {
        // 使用MD5算法加密
        return md5(password);
    }

    private String md5(String password) {
        // 实现MD5加密算法
        return null;
    }
}
