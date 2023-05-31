package com.nchu.software.pattern.singleton;

// 调节音量的实体类
public class Volume {//系统音量单例
    private static Volume instance;
    private int volumeLevel;

    private Volume() {
        // 私有构造函数
        volumeLevel = 0; // 默认音量为0
    }

    public static Volume getInstance() {
        if (instance == null) {
            instance = new Volume();
        }
        return instance;
    }

    public int getVolumeLevel() {
        return volumeLevel;
    }

    public void setVolumeLevel(int level) {
        volumeLevel = level;
    }
}
