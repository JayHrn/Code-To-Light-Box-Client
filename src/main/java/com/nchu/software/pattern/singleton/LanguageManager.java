package com.nchu.software.pattern.singleton;

import com.nchu.software.pattern.observer.LanguageSetting;

public class LanguageManager {//游戏语言单例
    private LanguageSetting languageSetting;
    private static LanguageManager instance;

    private LanguageManager() {
    }

    public static LanguageManager getInstance() {
        if (instance == null) {
            instance = new LanguageManager();
        }
        return instance;
    }

    public LanguageSetting getLanguageSetting() {
        return languageSetting;
    }

    public void setLanguageSetting(LanguageSetting languageSetting) {
        this.languageSetting = languageSetting;
    }
}
