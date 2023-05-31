package com.nchu.software.pattern.factory;

import com.nchu.software.pattern.composite.FunctionComponent;
import com.nchu.software.view.GameScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体程序，里面有主函数和子函数
 */
public class ConcreteProgram implements Program {
    /**
     * 函数
     */
    private List<FunctionComponent> functionComponents = new ArrayList<>();
    /**
     * 主函数矩阵，为了展示图片
     */
    private int[][] mainProgram = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };
    /**
     * 子函数矩阵，为了展示图片
     */
    private int[][] subProgram = {
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };

    // 执行程序
    public void execute() {
        // 执行主functionComponent
        if (functionComponents.size() >= 1) {
            functionComponents.get(0).execute();
        }
    }

    public List<FunctionComponent> getFunctionComponents() {
        return functionComponents;
    }

    public void setFunctionComponents(List<FunctionComponent> functionComponents) {
        this.functionComponents = functionComponents;
    }

    public int[][] getMainProgram() {
        return mainProgram;
    }

    public void setMainProgram(int[][] mainProgram) {
        this.mainProgram = mainProgram;
    }

    public int[][] getSubProgram() {
        return subProgram;
    }

    public void setSubProgram(int[][] subProgram) {
        this.subProgram = subProgram;
    }
}
