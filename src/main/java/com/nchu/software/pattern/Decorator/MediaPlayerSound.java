package com.nchu.software.pattern.Decorator;

import javafx.scene.media.MediaPlayer;

// MediaPlayerSound 类，实现 Sound 接口
public class MediaPlayerSound implements Sound {
    private MediaPlayer mediaPlayer;

    public MediaPlayerSound(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void play() {
        mediaPlayer.play();
    }
}
