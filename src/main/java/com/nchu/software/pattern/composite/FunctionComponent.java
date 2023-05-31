package com.nchu.software.pattern.composite;

import com.nchu.software.pattern.command.Command;

// 定义抽象组件类，即所有叶节点和组合节点的基类
public abstract class FunctionComponent {
    public abstract boolean execute(); // 抽象方法，执行组件行为
}
