package com.nchu.software.pattern.factory;

import com.nchu.software.model.Block;
import com.nchu.software.model.ConcreteBlock;

import java.util.HashMap;

/**
 * @Author JayHrn
 * @Date 2023-05-09 15:25
 * @Description 方块工厂，用于享元模式
 */
public class BlockFactory {
    private static final HashMap<String, Block> blockMap = new HashMap<>();

    public static Block getBlock(String color) {
        Block block = blockMap.get(color);

        if (block == null) {
            block = new ConcreteBlock();
            blockMap.put(color, block);
        }
        return block;
    }
}
