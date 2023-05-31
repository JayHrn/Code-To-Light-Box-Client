package com.nchu.software.controller;

import com.nchu.software.pattern.singleton.VolumeStage;
import com.nchu.software.view.VolumeScreen;

/**
 * @Description 音量逻辑控制
 */
public class VolumeController {
    private VolumeScreen volumeScreen;

    public VolumeController(VolumeScreen volumeScreen) {
        this.volumeScreen = volumeScreen;
    }

    /**
     * 监听
     */
    public void startEventListen() {
        VolumeStage.getInstance().setOnCloseRequest(e -> {
            // 关闭第一个舞台
            VolumeStage.getInstance().close();
        });

        // 绑定音量滑动条与 MediaPlayer 的音量属性
        volumeScreen.getVolumeSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100.0;
            volumeScreen.getMediaPlayer().setVolume(volume);
        });
    }
}

