package com.nchu.software.pattern.observer;

import javafx.scene.control.Button;

// 具体观察者：页面标签
public class PageLabel implements LanguageObserver {
    private Button button;
    private String chineseText;
    private String englishText;

    public PageLabel(Button button, String chineseText, String englishText) {
        this.button = button;
        this.chineseText = chineseText;
        this.englishText = englishText;
    }

    @Override
    public void update(String language) {
        if (language.equals("Chinese")) {
            button.setText(chineseText);
        } else {
            button.setText(englishText);
        }
    }
}
