package com.nchu.software.model;

import lombok.Data;

/**
 * @Author JayHrn
 * @Date 2023-05-09 15:20
 * @Description 具体方块
 */
@Data
public class ConcreteBlock implements Block {
    private String color;
    private Position position;

    @Override
    public void draw(Position position) {

    }

}
