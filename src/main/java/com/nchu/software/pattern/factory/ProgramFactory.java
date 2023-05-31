package com.nchu.software.pattern.factory;

// 工厂类，用于创建不同的程序对象
public class ProgramFactory {
    public Program createProgram() {
        return new ConcreteProgram();
    }
}
