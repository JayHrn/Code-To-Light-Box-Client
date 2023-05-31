package com.nchu.software.pattern.Decorator;

// SoundDecorator 抽象类，实现 Sound 接口
abstract class SoundDecorator implements Sound {
    protected Sound decoratedSound;

    public SoundDecorator(Sound decoratedSound) {
        this.decoratedSound = decoratedSound;
    }

    public void play() {
        decoratedSound.play();
    }
}