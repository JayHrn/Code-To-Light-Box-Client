package com.nchu.software.pattern.singleton;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaPlayerChange {//背景音乐单例
    private static MediaPlayerChange instance;
    private MediaPlayer mediaPlayer;

    private MediaPlayerChange() {
        // 私有构造函数
    }

    static {
        // 静态代码块，在类加载时初始化 mediaPlayer
        String musicFile = "/music/xinghuo.mp3";
        Media media = new Media(MediaPlayerChange.class.getResource(musicFile).toExternalForm());
        instance = new MediaPlayerChange();
        instance.mediaPlayer = new MediaPlayer(media);
        instance.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // 设置循环播放
    }

    public static MediaPlayerChange getInstance() {
        return instance;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
