package com.nchu.software.pattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author JayHrn
 * @Date 2023-05-17 20:55
 * 利用这个类创建主函数，子函数
 * 其次这个主函数或者子函数添加孩子，孩子有命令，可能孩子里面还有孩子
 * @Description 这个类详单与是一个图片，图片对应有一个命令，如果是子函数图片就有很多个孩子，但是孩子还是只有一个命令
 * 主函数 函数里面有命令 命令有策略，机器人根据函数进行执行
 * 每一种命令有一种策略，策略进行改变机器人位置，命令调用策略，机器人执行命令，导致机器人位置改变
 */
public class Composite extends FunctionComponent {
    private List<FunctionComponent> children;

    public Composite() {
        children = new ArrayList<>();
    }

    public void add(FunctionComponent c) {
        children.add(c);
    }

    public void remove(FunctionComponent c) {
        children.remove(c);
    }

    @Override
    public boolean execute() {
        for (FunctionComponent child : children) {
           if(!child.execute()){
               return false;
           }
        }
        return true;
    }

    public List<FunctionComponent> getChildren() {
        return children;
    }

    public void setChildren(List<FunctionComponent> children) {
        this.children = children;
    }
}
