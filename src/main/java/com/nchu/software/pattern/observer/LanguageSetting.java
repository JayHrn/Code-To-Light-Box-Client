package com.nchu.software.pattern.observer;

import java.util.ArrayList;
import java.util.List;

// 具体主题：语言设置
public class LanguageSetting implements LanguageSubject {
    private List<LanguageObserver> observers = new ArrayList<>(); // 观察者列表
    private String language = "Chinese"; // 当前语言设置

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
        notifyObservers(); // 通知所有观察者
    }

    @Override
    public void registerObserver(LanguageObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(LanguageObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (LanguageObserver observer : observers) {
            observer.update(language); // 通知观察者进行更新
        }
    }
}
