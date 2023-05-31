package com.nchu.software.pattern.Decorator;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

// VolumeChangeDecorator 类，继承自 SoundDecorator
public class VolumeChangeDecorator extends SoundDecorator {
    private Slider volumeSlider;
    private Button volumeButton;
    private Image volumeOnImage;
    private Image volumeOffImage;

    public VolumeChangeDecorator(Sound decoratedSound, Slider volumeSlider, Button volumeButton, Image volumeOnImage, Image volumeOffImage) {
        super(decoratedSound);
        this.volumeSlider = volumeSlider;
        this.volumeButton = volumeButton;
        this.volumeOnImage = volumeOnImage;
        this.volumeOffImage = volumeOffImage;

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue();
            updateVolumeButtonImage(volume);
        });
    }

    private void updateVolumeButtonImage(double volume) {
        if (volume == 0) {
            volumeButton.setBackground(new Background(new BackgroundImage(volumeOffImage, null, null, null, null)));
        } else {
            volumeButton.setBackground(new Background(new BackgroundImage(volumeOnImage, null, null, null, null)));
        }
    }
}
