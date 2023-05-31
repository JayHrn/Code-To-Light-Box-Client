package com.nchu.software.pattern.observer;

// 主题接口
interface LanguageSubject {
    void registerObserver(LanguageObserver observer);
    void removeObserver(LanguageObserver observer);
    void notifyObservers();
}
