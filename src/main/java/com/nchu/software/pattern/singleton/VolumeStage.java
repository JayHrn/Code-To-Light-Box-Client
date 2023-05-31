package com.nchu.software.pattern.singleton;

import javafx.stage.Stage;

public class VolumeStage extends Stage {//音量调节小舞台
    private static VolumeStage instance;

    private VolumeStage() {
    }

    public static VolumeStage getInstance() {
        if (instance == null) {
            instance = new VolumeStage();
        }
        return instance;
    }
}
