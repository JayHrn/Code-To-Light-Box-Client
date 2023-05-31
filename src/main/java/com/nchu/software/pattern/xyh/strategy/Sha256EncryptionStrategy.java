package com.nchu.software.pattern.xyh.strategy;

/**
 * ClassName: Sha256EncryptionStrategy
 * Package: a.strategy
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 17:37
 * @Version: v1.0
 */
public class Sha256EncryptionStrategy implements EncryptionStrategy {
    @Override
    public String encrypt(String password) {
        // 使用SHA256算法加密
        return sha256(password);
    }

    private String sha256(String password) {
        // 实现SHA256加密算法
        return null;
    }
}