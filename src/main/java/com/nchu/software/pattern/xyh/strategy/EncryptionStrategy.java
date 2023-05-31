package com.nchu.software.pattern.xyh.strategy;

/**
 * ClassName: EncryptionStrategy
 * Package: a.strategy
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 17:34
 * @Version: v1.0
 */
public interface EncryptionStrategy {
    String encrypt(String password);
}