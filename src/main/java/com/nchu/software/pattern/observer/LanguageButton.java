package com.nchu.software.pattern.observer;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// 具体观察者：语言切换按钮
public class LanguageButton implements LanguageObserver {
    private Button button;
    private Image chineseImage;
    private Image englishImage;

    public LanguageButton(Button button, Image chineseImage, Image englishImage) {
        this.button = button;
        this.chineseImage = chineseImage;
        this.englishImage = englishImage;
    }

    @Override
    public void update(String language) {
        if (language.equals("Chinese")) {
            button.setGraphic(new ImageView(chineseImage));
        } else {
            button.setGraphic(new ImageView(englishImage));

        }
    }
}
